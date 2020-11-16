package com.ishtoo.pinnacle.controllers;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ishtoo.pinnacle.dao.BatchDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.dao.TransactionDao;
import com.ishtoo.pinnacle.models.LoginAccount;
import com.ishtoo.pinnacle.models.PaytmDetails;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.models.Transaction;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;
import com.paytm.pg.merchant.PaytmChecksum;

@Controller
public class HomeController {

	@Autowired
	StudentDao studentDao;

	@Autowired
	BatchDao batchDao;

	@Autowired
	SecurityService securityService;

	@Autowired
	UserService userService;
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	private PaytmDetails paytmDetails;
	
	@Autowired
	private Environment env;

	@GetMapping("/registerStudent")
	public String registerStudent(Model m) {
		m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
			return "redirect:/welcome";
		}
		
		return "registerStudent";
	}
	
	@PostMapping(value = "/registerStudent")
	public ModelAndView getRedirect(@ModelAttribute("loginAccount") LoginAccount loginAccount, @ModelAttribute("student") Student student) throws Exception {
		ModelAndView modelAndViewTemp = new ModelAndView("registerStudent");
		String originalPassword = loginAccount.getPassword();
		loginAccount.setEnabled(Boolean.TRUE);
		loginAccount.setRole("ROLE_student");
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			modelAndViewTemp.addObject("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.doesLoginAccountExists(loginAccount)) {
			modelAndViewTemp.addObject("error", "Account with same UserName already exists");
			modelAndViewTemp.addObject("allOpenBatches", batchDao.findAllOpenBatches());
			return modelAndViewTemp;
		}
		userService.save(loginAccount);
		studentDao.addStudent(student);
		securityService.autoLogin(loginAccount.getUsername(), originalPassword);
		ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
	    TreeMap<String, String> parameters = new TreeMap<>();
	    paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
	    parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	    parameters.put("EMAIL", env.getProperty("paytm.email"));
	    parameters.put("ORDER_ID", student.getUsername());
	    parameters.put("TXN_AMOUNT", String.valueOf(batchDao.findBatchById(student.getBatchId()).getFees()));
	    parameters.put("CUST_ID", student.getUsername());
	    String checkSum = getCheckSum(parameters);
	    parameters.put("CHECKSUMHASH", checkSum);
	    modelAndView.addAllObjects(parameters);
	    return modelAndView; 
	}
	
	@PostMapping(value = "/pgresponse")
	public String getResponseRedirect(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		Map<String, String[]> mapData = request.getParameterMap();
	    TreeMap<String, String> parameters = new TreeMap<String, String>();
	    String paytmChecksum = "";
	    for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
	        if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
	            paytmChecksum = requestParamsEntry.getValue()[0];
	        } else {
	        	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
	        }
	    }
	    String transactionId=mapData.get("TXNID")[0];
	    String transactionAmount=mapData.get("TXNAMOUNT")[0];
	    String status=mapData.get("STATUS")[0];
	    String customerId=mapData.get("ORDERID")[0];
	    Transaction transaction=new Transaction(transactionId, transactionAmount, status, customerId); 
	    transactionDao.addTransaction(transaction);
	    boolean isValideChecksum = false;
	    try {
	        isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	        if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	            if (parameters.get("RESPCODE").equals("01")) {
	            	return "redirect:/student";
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    securityService.autoLogout();
	    userService.deleteLoginAccount(mapData.get("ORDERID")[0]);
	    redirectAttributes.addFlashAttribute("error", "Payment Failed");
	    parameters.remove("CHECKSUMHASH");
	    return "redirect:/registerStudent";
	}

	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	    return PaytmChecksum.verifySignature(parameters,
	            paytmDetails.getMerchantKey(), paytmChecksum);
	}

	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
	}

	@RequestMapping("/")
	public String home(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "index";
	}

	@RequestMapping("/results")
	public String results(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "resultsStatic";
	}

	@RequestMapping("/competitiveExams")
	public String competitiveExams(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "competitiveExamsStatic";
	}

	@GetMapping("/login")
	public String login(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
			return "redirect:/";
		}
		return "login";
	}

	@RequestMapping("/login-error")
	public String checkLogin(@ModelAttribute LoginAccount loginAccount, Model m,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Bad Credentials!");
		return "redirect:/login";
	}

	@RequestMapping("/welcome")
	public String welcome(Model m) {
		LoginAccount loggedInAccount = userService.findByUsername(securityService.findLoggedInUsername());
		if (loggedInAccount == null) {
			return "redirect:/login";
		}
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (loggedInAccount.getRole().equals("ROLE_admin")) {
			return "redirect:/admin";
		} else if (loggedInAccount.getRole().equals("ROLE_teacher")) {
			return "redirect:/teacher";
		} else {
			return "redirect:/student";
		}
	}

}
