package com.ishtoo.pinnacle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ishtoo.pinnacle.dao.BookDao;
import com.ishtoo.pinnacle.dao.StudentBookRelationDao;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class BookController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	StudentBookRelationDao studentBookRelationDao;
	
	@GetMapping("book/all")
	public String books(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBooks", bookDao.findAllBooks());
		return "books";
	}
	
	@GetMapping("/book/findOwnerOfBook")
	public String findOwnerOfBook(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBooks", bookDao.findAllBooks());
		return "findOwnerOfBook";
	}
	
	@PostMapping("/book/findOwnerOfBook")
	public String checkFindOwnerOfBook(@RequestParam("bookId") int bookId, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Student student=studentBookRelationDao.findOwnerOfBook(bookId);
		if (student==null) {
			m.addAttribute("error", "This book is not issued to any Student currently");
			m.addAttribute("allBooks", bookDao.findAllBooks());
			return "findOwnerOfBook";
		}
		m.addAttribute("student", student);
		return "findOwnerOfBook";
	}
}
