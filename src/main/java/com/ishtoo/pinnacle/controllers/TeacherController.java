package com.ishtoo.pinnacle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ishtoo.pinnacle.dao.BatchDao;
import com.ishtoo.pinnacle.dao.LectureDao;
import com.ishtoo.pinnacle.dao.LoginAccountDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.dao.SubjectDao;
import com.ishtoo.pinnacle.dao.TeacherDao;
import com.ishtoo.pinnacle.models.Lecture;
import com.ishtoo.pinnacle.models.LoginAccount;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.models.Subject;
import com.ishtoo.pinnacle.models.Teacher;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class TeacherController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	BatchDao batchDao;
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LectureDao lectureDao;
	
	@Autowired
	LoginAccountDao loginAccountDao;
	
	@Autowired
	SubjectDao subjectDao;
	
	@GetMapping("teacher/all")
	public String teachers(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allTeachers", teacherDao.findAllTeachers());
		return "teachersAll";
	}
	
	@RequestMapping("/teacher")
	public String teacherHome(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (!(userService.findByUsername(loggedInUsername).getRole().equalsIgnoreCase("ROLE_teacher"))) {
			return "redirect:/welcome";
		}
		return "teacher";
	}
	
	@RequestMapping("/teacher/profile/{username}")
	public String teacherProfile(@PathVariable String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		LoginAccount loginAccount = userService.findByUsername(username);
		Teacher teacher = teacherDao.findByUsername(username);
		if (teacher==null) {
			return "redirect:/welcome";
		}
		m.addAttribute(loginAccount);
		m.addAttribute(teacher);
		return "teacherProfile";
	}
	
	@GetMapping("/teacher/registerStudent")
	public String registerStudent(Model m) {
		m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "registerStudent";
	}
	
	@PostMapping("/teacher/registerStudent")
	public String checkRegisterStudent(@ModelAttribute Student student, @ModelAttribute LoginAccount loginAccount, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
        loginAccount.setEnabled(Boolean.TRUE);
        loginAccount.setRole("ROLE_student");
        if (userService.doesLoginAccountExists(loginAccount)) {
        	m.addAttribute("error", "Account with same UserName already exists");
        	m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
        	return "registerStudent";
        }
        userService.save(loginAccount);
        studentDao.addStudent(student);
        redirectAttributes.addFlashAttribute("success", "Success!");
        return "redirect:/teacher";
	}
	
	@GetMapping("teacher/searchStudentByName")
	public String searchStudentByName(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchStudentByName";
	}
	
	@GetMapping("teacher/searchTeacherByName")
	public String searchTeacherByName(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchTeacherByName";
	}
	
	@GetMapping("teacher/deleteStudent")
	public String deleteStudent(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "deleteStudent";
	}
	
	@PostMapping("teacher/deleteStudent")
	public String checkDeleteStudent(@RequestParam("username") String username, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username)==null) {
			m.addAttribute("error", "Invalid Username");
			return "deleteStudent";
		}
		if (!userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_student")) {
			m.addAttribute("error", "This Username does not correspond to a Student Account");
			return "deleteStudent";
		}
		userService.deleteLoginAccount(username);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/teacher";
	}
	
	@GetMapping("/teacher/{username}/subject/all")
	public String displaySubjectsTaughtByTeacher(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Teacher teacher=teacherDao.findByUsername(username);
		if (teacher==null) {
			return "redirect:/welcome";
		}
		m.addAttribute("teacher", teacher);
		m.addAttribute("subjectsTaughtByTeacher", subjectDao.findSubjectsTaughtByTeacher(teacher.getTeacherId()));
		return "displaySubjectsTaughtByTeacher";
	}
	
	@GetMapping("/teacher/viewSubjectsTaughtByTeacher")
	public String viewSubjectsTaughtByTeacher(Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "subjectsTaughtByTeacher";
	}
	
	@PostMapping("/teacher/viewSubjectsTaughtByTeacher")
	public String checkViewSubjectsTaughtByTeacher(@RequestParam("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (!loginAccountDao.doesLoginAccountExists(username)) {
			m.addAttribute("error", "This UserName does not exist");
			return "subjectsTaughtByTeacher";
		}
		if (!(userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_teacher"))) {
			m.addAttribute("error", "This UserName does not belong to a teacher");
			return "subjectsTaughtByTeacher";
		}
		return "redirect:/teacher/" + username + "/subject/all";
	}
	
	@PostMapping(path="teacher/changeSubjectInit/{batchId}", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Subject> getAllSubjectsInThisBatchTaughtByTeacher(@PathVariable("batchId") String batchId) {
		String loggedInUsername=securityService.findLoggedInUsername();
		Teacher teacher=teacherDao.findByUsername(loggedInUsername);
		List<Subject> allSubjectsInThisBatch=subjectDao.findSubjectsInThisBatchTaughtByTeacher(batchId, teacher.getTeacherId());
		return allSubjectsInThisBatch;
	}
	
	@GetMapping("teacher/{username}/addLecture")
	public String addLecture(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_admin")) {
			return "redirect:/admin/addLecture";
		}
		if (!loggedInUsername.equalsIgnoreCase(username)) {
			return "redirect:/teacher/" + loggedInUsername + "/addLecture";
		}
		Teacher teacher=teacherDao.findByUsername(username);
		m.addAttribute("allBatches", batchDao.findAllBatchesHavingTeacher(teacher.getTeacherId()));
		return "addLectureByTeacher";
	}
	
	@PostMapping("teacher/{username}/addLecture")
	public String checkAddLecture(Lecture lecture, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		lectureDao.addLecture(lecture);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/teacher";
	}
	
	@PostMapping(path="teacher/findLecturesInThisSubject/{batchId}/{subjectId}", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Lecture> getAllLecturesInThisSubject(@PathVariable("batchId") String batchId, @PathVariable("subjectId") String subjectId) {
		List<Lecture> allLecturesInThisSubject=lectureDao.findLecturesInThisSubject(batchId, subjectId);
		return allLecturesInThisSubject;
	}
	
	@GetMapping("teacher/{username}/deleteLecture")
	public String deleteLecture(@PathVariable("username") String username, Model m) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_admin")) {
			return "redirect:/admin/deleteLecture";
		}
		if (!loggedInUsername.equalsIgnoreCase(username)) {
			return "redirect:/teacher/" + loggedInUsername + "/deleteLecture";
		}
		Teacher teacher=teacherDao.findByUsername(username);
		m.addAttribute("allBatches", batchDao.findAllBatchesHavingTeacher(teacher.getTeacherId()));
		return "deleteLectureByTeacher";
	}
	
	@PostMapping("teacher/{username}/deleteLecture")
	public String checkDeleteLecture(Lecture lecture, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername=securityService.findLoggedInUsername();
		if (loggedInUsername!=null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		lectureDao.deleteLecture(lecture);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/teacher";
	}
	
}
