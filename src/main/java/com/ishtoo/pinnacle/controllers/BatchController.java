package com.ishtoo.pinnacle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ishtoo.pinnacle.dao.BatchDao;
import com.ishtoo.pinnacle.dao.BatchTestRelationDao;
import com.ishtoo.pinnacle.dao.LectureDao;
import com.ishtoo.pinnacle.dao.StudentDao;
import com.ishtoo.pinnacle.dao.SubjectDao;
import com.ishtoo.pinnacle.dao.TeacherDao;
import com.ishtoo.pinnacle.models.Batch;
import com.ishtoo.pinnacle.models.Subject;
import com.ishtoo.pinnacle.service.SecurityService;
import com.ishtoo.pinnacle.service.UserService;

@Controller
public class BatchController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserService userService;

	@Autowired
	BatchDao batchDao;

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	LectureDao lectureDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	BatchTestRelationDao batchTestRelationDao;

	@GetMapping("batch/all")
	public String batches(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "batches";
	}

	@GetMapping("batch/profile/{batchId}")
	public String viewBatchProfile(@PathVariable("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Batch batch = batchDao.findBatchById(batchId);
		if (batch == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("batch", batchDao.findBatchById(batchId));
		m.addAttribute("subjectsInThisBatch", subjectDao.findSubjectsInThisBatch(batchId));
		m.addAttribute("studentsInThisBatch", studentDao.findStudentsInThisBatch(batchId));
		m.addAttribute("testsInThisBatch", batchTestRelationDao.findUpcomingTestsInThisBatch(batchId));
		return "batchProfile";
	}

	@GetMapping("/batch/{batchId}/subject/profile/{subjectId}")
	public String viewSubjectProfile(@PathVariable("batchId") String batchId,
			@PathVariable("subjectId") String subjectId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Subject subject = subjectDao.findSubjectById(batchId, subjectId);
		if (subject == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("teacher", teacherDao.findById(subject.getTeacherId()));
		m.addAttribute("subject", subject);
		m.addAttribute("lecturesInThisSubject", lectureDao.findLecturesInThisSubject(batchId, subjectId));
		return "subjectProfile";
	}

	@GetMapping("/batch/{batchId}/subject/all")
	public String displaySubjectsInBatch(@PathVariable("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Batch batch = batchDao.findBatchById(batchId);
		if (batch == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("batch", batchDao.findBatchById(batchId));
		m.addAttribute("subjectsInThisBatch", subjectDao.findSubjectsInThisBatch(batchId));
		return "displaySubjectsInBatch";
	}

	@GetMapping("/batch/viewSubjectsInBatch")
	public String viewSubjectsInBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "subjectsInBatch";
	}

	@PostMapping("/batch/viewSubjectsInBatch")
	public String checkViewSubjectsInBatch(@RequestParam("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return ("redirect:/batch/" + batchId + "/subject/all");
	}

	@PostMapping(path = "batch/changeSubjectInit/{batchId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Subject> getAllSubjectsInThisBatch(@PathVariable("batchId") String batchId) {
		List<Subject> allSubjectsInThisBatch = subjectDao.findSubjectsInThisBatch(batchId);
		return allSubjectsInThisBatch;
	}

	@GetMapping("/batch/{batchId}/subject/{subjectId}/lecture/all")
	public String displayLecturesInSubject(@PathVariable("batchId") String batchId,
			@PathVariable("subjectId") String subjectId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Subject subject = subjectDao.findSubjectById(batchId, subjectId);
		if (subject == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("teacher", teacherDao.findById(subject.getTeacherId()));
		m.addAttribute("subject", subject);
		m.addAttribute("lecturesInThisSubject", lectureDao.findLecturesInThisSubject(batchId, subjectId));
		return "displayLecturesInSubject";
	}

	@GetMapping("/batch/viewLecturesInSubject")
	public String viewLecturesInSubject(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "lecturesInSubject";
	}

	@PostMapping("/batch/viewLecturesInSubject")
	public String checkViewLecturesInSubject(@RequestParam("batchId") String batchId,
			@RequestParam("subjectId") String subjectId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return ("redirect:/batch/" + batchId + "/subject/" + subjectId + "/lecture/all");
	}

	@GetMapping("/batch/{batchId}/student/all")
	public String displayStudentsInBatch(@PathVariable("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Batch batch = batchDao.findBatchById(batchId);
		if (batch == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("batch", batchDao.findBatchById(batchId));
		m.addAttribute("studentsInThisBatch", studentDao.findStudentsInThisBatch(batchId));
		return "displayStudentsInBatch";
	}

	@GetMapping("/batch/viewStudentsInBatch")
	public String viewStudentsInBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "studentsInBatch";
	}

	@PostMapping("/batch/viewStudentsInBatch")
	public String checkViewStudentsInBatch(@RequestParam("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return ("redirect:/batch/" + batchId + "/student/all");
	}

	@GetMapping("/batch/{batchId}/test/upcoming")
	public String displayUpcomingTestsInBatch(@PathVariable("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Batch batch = batchDao.findBatchById(batchId);
		if (batch == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("batch", batchDao.findBatchById(batchId));
		m.addAttribute("testsInThisBatch", batchTestRelationDao.findUpcomingTestsInThisBatch(batchId));
		return "displayTestsInBatch";
	}

	@GetMapping("/batch/{batchId}/test/all")
	public String displayTestsInBatch(@PathVariable("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		Batch batch = batchDao.findBatchById(batchId);
		if (batch == null) {
			return "redirect:/welcome";
		}
		m.addAttribute("batch", batchDao.findBatchById(batchId));
		m.addAttribute("testsInThisBatch", batchTestRelationDao.findTestsInThisBatch(batchId));
		return "displayTestsInBatch";
	}

	@GetMapping("/batch/viewTestsInBatch")
	public String viewTestsInBatch(Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		m.addAttribute("allBatches", batchDao.findAllBatches());
		return "testsInBatch";
	}

	@PostMapping("/batch/viewTestsInBatch")
	public String checkViewTestsInBatch(@RequestParam("batchId") String batchId, Model m) {
		String loggedInUsername = securityService.findLoggedInUsername();
		if (loggedInUsername != null) {
			m.addAttribute("loggedInAccount", userService.findByUsername(loggedInUsername));
		}
		return ("redirect:/batch/" + batchId + "/test/all");
	}
}
