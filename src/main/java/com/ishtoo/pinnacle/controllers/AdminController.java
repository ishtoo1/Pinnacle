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

import com.ishtoo.pinnacle.models.Batch;
import com.ishtoo.pinnacle.models.BatchTestRelation;
import com.ishtoo.pinnacle.models.Book;
import com.ishtoo.pinnacle.models.Lecture;
import com.ishtoo.pinnacle.models.Test;
import com.ishtoo.pinnacle.dao.BatchDao;
import com.ishtoo.pinnacle.dao.BatchTestRelationDao;
import com.ishtoo.pinnacle.dao.BookDao;
import com.ishtoo.pinnacle.dao.LectureDao;
import com.ishtoo.pinnacle.dao.StudentBookRelationDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.dao.SubjectDao;
import com.ishtoo.pinnacle.dao.TeacherDao;
import com.ishtoo.pinnacle.dao.TestDao;
import com.ishtoo.pinnacle.dao.TransactionDao;
import com.ishtoo.pinnacle.models.LoginAccount;
import com.ishtoo.pinnacle.models.Student;
import com.ishtoo.pinnacle.models.Subject;
import com.ishtoo.pinnacle.models.Teacher;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class AdminController {
	@Autowired
	SecurityService securityService;

	@Autowired
	UserService userService;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	BatchDao batchDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	TestDao testDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	BatchTestRelationDao batchTestRelationDao;

	@Autowired
	LectureDao lectureDao;

	@Autowired
	StudentBookRelationDao studentBookRelationDao;
	
	@Autowired
	TransactionDao transactionDao;

	@RequestMapping("/admin")
	public String adminHome(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "admin";
	}

	@RequestMapping("/admin/profile/{username}")
	public String adminProfile(@PathVariable String username, Model m) {
		LoginAccount loginAccount = userService.findByUsername(username);
		if (loginAccount == null) {
			return "redirect:/welcome";
		}
		m.addAttribute(loginAccount);
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "adminProfile";
	}

	@GetMapping("/admin/registerTeacher")
	public String registerTeacher(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "registerTeacher";
	}

	@PostMapping("/admin/registerTeacher")
	public String checkRegisterTeacher(@ModelAttribute Teacher teacher, @ModelAttribute LoginAccount loginAccount,
			Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		loginAccount.setEnabled(Boolean.TRUE);
		loginAccount.setRole("ROLE_teacher");
		if (userService.doesLoginAccountExists(loginAccount)) {
			m.addAttribute("error", "Account with same UserName already exists");
			return "registerTeacher";
		}
		userService.save(loginAccount);
		teacherDao.addTeacher(teacher);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("/admin/registerStudent")
	public String registerStudent(Model m) {
		m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "registerStudent";
	}

	@PostMapping("/admin/registerStudent")
	public String checkRegisterStudent(@ModelAttribute Student student, @ModelAttribute LoginAccount loginAccount,
			Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
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
		return "redirect:/admin";
	}

	@GetMapping("admin/addBatch")
	public String addBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "addBatch";
	}

	@PostMapping("admin/addBatch")
	public String checkAddBatch(@ModelAttribute Batch batch, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (batchDao.checkIfBatchAlreadyExists(batch)) {
			m.addAttribute("error", "Batch with identical Batch Id already exists");
			return "addBatch";
		}
		batchDao.addBatch(batch);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/addTest")
	public String addTest(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "addTest";
	}

	@PostMapping("admin/addTest")
	public String checkAddTest(@ModelAttribute Test test, Model m, RedirectAttributes redirectAttributes) {
		testDao.addTest(test);
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/addBook")
	public String addBook(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "addBook";
	}

	@PostMapping("admin/addBook")
	public String checkAddBook(@ModelAttribute Book book, Model m, RedirectAttributes redirectAttributes) {
		bookDao.addBook(book);
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@PostMapping(path = "admin/findUpcomingTestsNotInThisBatch/{batchId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Test> findUpcomingTestsNotInThisBatch(@PathVariable("batchId") String batchId) {
		List<Test> allUpcomingTestsNotInThisBatch = batchTestRelationDao.findUpcomingTestsNotInThisBatch(batchId);
		return allUpcomingTestsNotInThisBatch;
	}

	@GetMapping("admin/addTestToBatch")
	public String addTestToBatch(Model m) {
		m.addAttribute("allBatches", batchDao.findAllBatches());
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "addTestToBatch";
	}

	@PostMapping("admin/addTestToBatch")
	public String checkAddTestToBatch(@ModelAttribute BatchTestRelation batchTestRelation, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		batchTestRelationDao.addTestToBatch(batchTestRelation);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/openAdmissionsForBatch")
	public String openAdmissionsForBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allClosedBatches", batchDao.findAllClosedBatches());
		return "openAdmissionsForBatch";
	}

	@PostMapping("admin/openAdmissionsForBatch")
	public String checkOpenAdmissionsForBatch(@ModelAttribute Batch batch, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		batchDao.openAdmissionsForBatch(batch);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/closeAdmissionsForBatch")
	public String closeAdmissionsForBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allOpenBatches", batchDao.findAllOpenBatches());
		return "closeAdmissionsForBatch";
	}

	@PostMapping("admin/closeAdmissionsForBatch")
	public String checkCloseAdmissionsForBatch(@ModelAttribute Batch batch, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		batchDao.closeAdmissionsForBatch(batch);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/issueBook")
	public String issueBook(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allAvailableBooks", bookDao.findAllAvailableBooks());
		return "issueBook";
	}

	@PostMapping("admin/issueBook")
	public String checkIssueBook(@ModelAttribute Book book, @RequestParam("username") String username, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username) == null) {
			m.addAttribute("error", "Invalid Username");
			m.addAttribute("allAvailableBooks", bookDao.findAllAvailableBooks());
			return "issueBook";
		}
		if (!userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_student")) {
			m.addAttribute("error", "Books can only be issued to students!");
			m.addAttribute("allAvailableBooks", bookDao.findAllAvailableBooks());
			return "issueBook";
		}
		studentBookRelationDao.issueBook(book, studentDao.findByUsername(username).getStudentId());
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/searchStudentByName")
	public String searchStudentByName(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchStudentByName";
	}

	@GetMapping("admin/searchTeacherByName")
	public String searchTeacherByName(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "searchTeacherByName";
	}

	@GetMapping("admin/addSubject")
	public String addSubject(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		m.addAttribute("allTeachers", teacherDao.findAllTeachers());
		return "addSubject";
	}

	@PostMapping("admin/addSubject")
	public String checkAddSubject(@ModelAttribute Subject subject, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (subjectDao.checkIfSubjectExistsInBatch(subject.getSubjectId(), subject.getBatchId())) {
			m.addAttribute("error", "Subject with this Id already exists in this Batch");
			m.addAttribute("allBatches", batchDao.findAllBatches());
			m.addAttribute("allTeachers", teacherDao.findAllTeachers());
			return "addSubject";
		}
		subjectDao.addSubject(subject);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@PostMapping(path = "admin/changeSubjectInit/{batchId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Subject> getAllSubjectsInThisBatch(@PathVariable("batchId") String batchId) {
		List<Subject> allSubjectsInThisBatch = subjectDao.findSubjectsInThisBatch(batchId);
		return allSubjectsInThisBatch;
	}

	@GetMapping("admin/changeSubjectTeacher")
	public String changeSubjectTeacher(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		m.addAttribute("allTeachers", teacherDao.findAllTeachers());
		return "changeSubjectTeacher";
	}

	@PostMapping("admin/changeSubjectTeacher")
	public String checkChangeSubjectTeacher(Subject subject, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		subjectDao.changeSubjectTeacher(subject);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/addLecture")
	public String addLecture(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "addLecture";
	}

	@PostMapping("admin/addLecture")
	public String checkAddLecture(Lecture lecture, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (lectureDao.checkIfLectureExistsWithSameIdInThisSubject(lecture)) {
			m.addAttribute("error", "Lecture with this Id already exists in this Subject");
			m.addAttribute("allBatches", batchDao.findAllBatches());
			return "addLecture";
		}
		lectureDao.addLecture(lecture);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteBatch")
	public String deleteBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "deleteBatch";
	}

	@PostMapping("admin/deleteBatch")
	public String checkDeleteBatch(@RequestParam("batchId") String batchId, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		batchDao.deleteBatch(batchId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteSubject")
	public String deleteSubject(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "deleteSubject";
	}

	@PostMapping("admin/deleteSubject")
	public String checkDeleteSubject(@RequestParam("batchId") String batchId,
			@RequestParam("subjectId") String subjectId, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		subjectDao.deleteSubject(batchId, subjectId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@PostMapping(path = "/admin/findTestsInThisBatch/{batchId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Test> findTestsInThisBatch(@PathVariable("batchId") String batchId) {
		List<Test> allTestsInThisBatch = batchTestRelationDao.findTestsInThisBatch(batchId);
		return allTestsInThisBatch;
	}

	@GetMapping("admin/deleteTestFromBatch")
	public String deleteTestFromBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "deleteTestFromBatch";
	}

	@PostMapping("admin/deleteTestFromBatch")
	public String checkDeleteTestFromBatch(@RequestParam("batchId") String batchId,
			@RequestParam("testId") String testId, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		batchTestRelationDao.deleteTestFromBatch(batchId, testId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteBook")
	public String deleteBook(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBooks", bookDao.findAllBooks());
		return "deleteBook";
	}

	@PostMapping("admin/deleteBook")
	public String checkDeleteBook(@RequestParam("bookId") String bookId, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		bookDao.deleteBook(bookId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@PostMapping(path = "/admin/findAllBooksIssuedToStudent/{username}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Book> findAllBooksIssuedToStudent(@PathVariable("username") String username) {
		if (userService.findByUsername(username) == null) {
			return null;
		}
		if (!userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_student")) {
			return null;
		}
		Student student = studentDao.findByUsername(username);
		List<Book> allBooksIssuedToStudent = bookDao.findAllBooksIssuedToStudent(student.getStudentId());
		return allBooksIssuedToStudent;
	}

	@GetMapping("admin/returnBook")
	public String returnBook(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "returnBook";
	}

	@PostMapping("admin/returnBook")
	public String checkReturnBook(@RequestParam("bookId") String bookId, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		studentBookRelationDao.returnBook(bookId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@PostMapping(path = "admin/findLecturesInThisSubject/{batchId}/{subjectId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Lecture> getAllLecturesInThisSubject(@PathVariable("batchId") String batchId,
			@PathVariable("subjectId") String subjectId) {
		List<Lecture> allLecturesInThisSubject = lectureDao.findLecturesInThisSubject(batchId, subjectId);
		return allLecturesInThisSubject;
	}

	@GetMapping("admin/deleteLecture")
	public String deleteLecture(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "deleteLecture";
	}

	@PostMapping("admin/deleteLecture")
	public String checkDeleteLecture(Lecture lecture, Model m, RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		lectureDao.deleteLecture(lecture);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteStudent")
	public String deleteStudent(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "deleteStudent";
	}

	@PostMapping("admin/deleteStudent")
	public String checkDeleteStudent(@RequestParam("username") String username, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username) == null) {
			m.addAttribute("error", "Invalid Username");
			return "deleteStudent";
		}
		if (!userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_student")) {
			m.addAttribute("error", "This Username does not correspond to a Student Account");
			return "deleteStudent";
		}
		userService.deleteLoginAccount(username);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteTeacher")
	public String deleteTeacher(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return "deleteTeacher";
	}

	@PostMapping("admin/deleteTeacher")
	public String checkDeleteTeacher(@RequestParam("username") String username, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		if (userService.findByUsername(username) == null) {
			m.addAttribute("error", "Invalid Username");
			return "deleteTeacher";
		}
		if (!userService.findByUsername(username).getRole().equalsIgnoreCase("ROLE_teacher")) {
			m.addAttribute("error", "This Username does not correspond to a Teacher Account");
			return "deleteTeacher";
		}
		userService.deleteLoginAccount(username);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}

	@GetMapping("admin/deleteTest")
	public String deleteTest(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allTests", testDao.findAllTests());
		return "deleteTest";
	}

	@PostMapping("admin/deleteTest")
	public String checkDeleteTest(@RequestParam("testId") String testId, Model m,
			RedirectAttributes redirectAttributes) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		testDao.deleteTest(testId);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/admin";
	}
	
	@GetMapping("admin/getAllTransactions")
	public String getAllTransactions(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allTransactions", transactionDao.getAllTransactions());
		return "transactions";
	}

}
