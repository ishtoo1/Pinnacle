package com.ishtoo.pinnacle.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping(path="book/findOwner/{bookId}", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Student> getOwnerOfThisBook(@PathVariable("bookId") int bookId) {
		Student student1=studentBookRelationDao.findOwnerOfBook(bookId);
		List<Student> student=new ArrayList<Student>();
		if (student1!=null) {
			student.add(student1);
		}
		return student;
	}
	
}
