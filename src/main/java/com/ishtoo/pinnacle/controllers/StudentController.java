package com.ishtoo.pinnacle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ishtoo.pinnacle.dao.LoginAccountDao;
import com.ishtoo.pinnacle.dao.StudentBookRelationDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.dao.TeacherDao;
import com.ishtoo.pinnacle.models.LoginAccount;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.models.Teacher;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class StudentController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LoginAccountDao loginAccountDao;
	
	@Autowired
	StudentBookRelationDao studentBookRelationDao;
	
	@Autowired
	TeacherDao teacherDao; 
	
	@GetMapping("student/all")
	public String students(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allStudents", studentDao.findAllStudents());
		return "studentsAll";
	}
	
	@RequestMapping("/student")
	public String studentHome(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (!(userService.findByUsername(loggedInUsername).getRole().equalsIgnoreCase("ROLE_student"))) {
			return "redirect:/welcome";
		}
		return "student";
	}
	
	@RequestMapping("/student/profile/{username}")
	public String studentProfile(@PathVariable String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		LoginAccount loginAccount = userService.findByUsername(username);
		Student student = studentDao.findByUsername(username);
		if (student==null) {
			return "redirect:/welcome";
		}
		m.addAttribute(loginAccount);
		m.addAttribute(student);
		return "studentProfile";
	}
	
	@GetMapping("/student/{username}/book/all")
	public String displayBooksIssuedToStudent(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Student student=studentDao.findByUsername(username);
		if (student==null) {
			return "redirect:/welcome";
		}
		m.addAttribute("student", student);
		m.addAttribute("allBooksIssuedToStudent", studentBookRelationDao.findBooksIssuedToStudent(student.getStudentId()));
		return "displayBooksIssuedToStudent";
	}
	
	@GetMapping("/student/booksIssuedToStudent")
	public String booksIssuedToStudent(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "booksIssuedToStudent";
	}
	
	@PostMapping("/student/booksIssuedToStudent")
	public String checkBooksIssuedToStudent(@RequestParam("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (!loginAccountDao.doesLoginAccountExists(username)) {
			m.addAttribute("error", "This UserName does not exist");
			return "booksIssuedToStudent";
		}
		if (!(userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_student"))) {
			m.addAttribute("error", "Books are only issued to Students. This is not a valid Student UserName");
			return "booksIssuedToStudent";
		}
		return "redirect:/student/" + username + "/book/all";
	}
	
	@GetMapping("student/searchStudentByName")
	public String searchStudentByName(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchStudentByName";
	}
	
	@PostMapping("student/searchStudentByName")
	public String checkSearchStudentByName(@RequestParam("studentName") String studentName, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		List<Student> studentsFound=studentDao.searchStudentByName(studentName);
		if (studentsFound.size()==0) {
			m.addAttribute("error", "No result found!");
		}
		m.addAttribute("studentsFound", studentsFound);
		return "searchStudentByName";
	}
	
	@GetMapping("student/searchTeacherByName")
	public String searchTeacherByName(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchTeacherByName";
	}
	
	@PostMapping("student/searchTeacherByName")
	public String checkSearchTeacherByName(@RequestParam("teacherName") String teacherName, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		List<Teacher> teachersFound=teacherDao.searchTeacherByName(teacherName);
		if (teachersFound.size()==0) {
			m.addAttribute("error", "No result found!");
		}
		m.addAttribute("teachersFound", teachersFound);
		return "searchTeacherByName";
	}
	
	@GetMapping("/student/{username}/viewBatchProfile")
	public String viewBatchProfileOfStudent(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Student student=studentDao.findByUsername(username);
		if (student==null) {
			return "redirect:/welcome";
		}
		return "redirect:/batch/profile/" + student.getBatchId();
	}
	
	@GetMapping("/student/{username}/viewUpcomingTestsForBatch")
	public String viewUpcomingTestsForBatch(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Student student=studentDao.findByUsername(username);
		if (student==null) {
			return "redirect:/welcome";
		}
		return "redirect:/batch/" + student.getBatchId() + "/test/upcoming";
	}
}
