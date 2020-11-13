package com.ishtoo.pinnacle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ishtoo.pinnacle.dao.BatchDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.models.LoginAccount;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

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
	
	@RequestMapping("/")
	public String home(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "index";
	}
	
	@RequestMapping("/results")
	public String results(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "resultsStatic";
	}
	
	@RequestMapping("/competitiveExams")
	public String competitiveExams(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "competitiveExamsStatic";
	}
	
	@GetMapping("/login")
	public String login(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping("/login-error")
	public String checkLogin(@ModelAttribute LoginAccount loginAccount, Model m, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Bad Credentials!");
		return "redirect:/login";
	}
	
	@GetMapping("/registerStudent")
	public String registerStudent(Model m) {
		m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
			return "redirect:/welcome";
		}
		return "registerStudent";
	}
	
	@PostMapping("/registerStudent")
	public String checkRegisterStudent(@ModelAttribute Student student, @ModelAttribute LoginAccount loginAccount, Model m) {
		String originalPassword = loginAccount.getPassword();
        loginAccount.setEnabled(Boolean.TRUE);
        loginAccount.setRole("ROLE_student");
        String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
        if (userService.doesLoginAccountExists(loginAccount)) {
        	m.addAttribute("error", "Account with same UserName already exists");
        	m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
        	return "registerStudent";
        }
        userService.save(loginAccount);
        studentDao.addStudent(student);
        securityService.autoLogin(loginAccount.getUsername(), originalPassword);
        return "redirect:/student";
	}
	
	@RequestMapping("/welcome") 
	public String welcome(Model m) {
		LoginAccount loggedInAccount=userService.findByUsername(securityService.findLoggedInUsername());
		if (loggedInAccount==null) {
			return "redirect:/login";
		}
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (loggedInAccount.getRole().equals("ROLE_admin")) {
			return "redirect:/admin";
		}
		else if (loggedInAccount.getRole().equals("ROLE_teacher")) {
			return "redirect:/teacher";
		}
		else {
			return "redirect:/student";
		}
	}	
	
}
