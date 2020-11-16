package com.ishtoo.pinnacle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ishtoo.pinnacle.dao.BatchTestRelationDao;
import com.ishtoo.pinnacle.dao.TestDao;
import com.ishtoo.pinnacle.models.Test;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class TestController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserService userService;

	@Autowired
	TestDao testDao;

	@Autowired
	BatchTestRelationDao batchTestRelationDao;

	@GetMapping("test/all")
	public String tests(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allTests", testDao.findAllTests());
		return "tests";
	}

	@GetMapping("/test/{testId}/batch/all")
	public String displayBatchesInTest(@PathVariable("testId") int testId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Test test = testDao.findById(testId);
		if (test == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("test", testDao.findById(testId));
		m.addAttribute("batchesInThisTest", batchTestRelationDao.findBatchesInTest(testId));
		return "displayBatchesInTest";
	}

	@GetMapping("/test/viewBatchesInTest")
	public String viewBatchesInTest(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allTests", testDao.findAllTests());
		return "batchesInTest";
	}

	@PostMapping("/test/viewBatchesInTest")
	public String checkViewBatchesInTest(@RequestParam("testId") int testId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return ("redirect:/test/" + testId + "/batch/all");
	}
}
