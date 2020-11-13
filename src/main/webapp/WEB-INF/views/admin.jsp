<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<p style="color:blue;">${success}</p>
	<h1>Pinnacle Competitive Classes</h1>
	<a href="/">Home</a><br>
	<a href="/results">Results</a><br>
	<a href="/competitiveExams">Competitive Exams</a><br>
	<c:set var = "userStr" value = "${loggedInAccount.getUsername()}"/>
	<c:set var = "roleStr" value = "${loggedInAccount.getRole()}"/>
	<c:set var = "len" value = "${fn:length(userStr)}"/>
	<c:if test="${len>0}">
		<c:if test="${roleStr == 'ROLE_admin'}">
			<a href="/admin/profile/${userStr}">${userStr}</a><br>
			<a href="/logout">Logout</a><br>
		</c:if>
		<c:if test="${roleStr == 'ROLE_teacher'}">
			<a href="/teacher/profile/${userStr}">${userStr}</a><br>
			<a href="/logout">Logout</a><br>
		</c:if>
		<c:if test="${roleStr == 'ROLE_student'}">
			<a href="/student/profile/${userStr}">${userStr}</a><br>
			<a href="/logout">Logout</a><br>
		</c:if>
		<a href="/welcome">Function</a><br>
	</c:if>
	<c:if test="${len==0}">
	<a href="/login">Login</a><br>
	<a href="/registerStudent">Register Student</a><br>
	</c:if>
	<h3>Admin</h3>
	<a href="/batch/all">View all Batches</a><br>
	<a href="/batch/viewStudentsInBatch">View all Students of a Batch</a><br>
	<a href="/batch/viewTestsInBatch">View all Tests scheduled for a Batch</a><br>
	<a href="/admin/addBatch">Add a Batch</a><br>
	<a href="/admin/deleteBatch">Delete a Batch</a><br>
	<a href="/admin/openAdmissionsForBatch">Open Admissions for a Batch</a><br>
	<a href="/admin/closeAdmissionsForBatch">Close Admissions for a Batch</a><br>
	<br>
	<a href="/batch/viewSubjectsInBatch">View all Subjects of a Batch</a><br>
	<a href="/admin/addSubject">Add a Subject</a><br>
	<a href="/admin/deleteSubject">Remove a Subject</a><br>
	<a href="/admin/changeSubjectTeacher">Change the teacher of a Subject</a><br>
	<br>
	<a href="/test/viewBatchesInTest">View Batches that have a particular Test scheduled</a><br>
	<a href="/admin/addTestToBatch">Schedule a Test for a Batch</a><br>
	<a href="/admin/deleteTestFromBatch">Remove a Test from the Schedule of a Batch</a><br>
	<br>
	<a href="/book/findOwnerOfBook">View the current Student owner of a Book</a><br>
	<a href="/book/all">View all Books</a><br>
	<a href="/admin/addBook">Add a Book</a><br>
	<a href="/admin/deleteBook">Delete a Book</a><br>
	<a href="/admin/issueBook">Issue a Book to a Student</a><br>
	<a href="/admin/returnBook">Return a Book from a Student</a><br>
	<br>
	<a href="/batch/viewLecturesInSubject">View all Lectures of a Subject</a><br>
	<a href="/admin/addLecture">Add Lecture to a Subject</a><br>
	<a href="/admin/deleteLecture">Remove a Lecture from a Subject</a><br>
	<br>
	<a href="/student/booksIssuedToStudent">View all Books issued to a Student</a><br>
	<a href="/student/all">View all Students</a><br>
	<a href="/admin/registerStudent">Add a Student</a><br>
	<a href="/admin/deleteStudent">Remove a Student</a><br>
	<a href="/admin/searchStudentByName">Search a Student</a><br>
	<br>
	<a href="/teacher/all">View all Teachers</a><br>
	<a href="/teacher/viewSubjectsTaughtByTeacher">View all Subjects taught by a Teacher</a><br>
	<a href="/admin/registerTeacher">Add a Teacher</a><br>
	<a href="/admin/deleteTeacher">Remove a Teacher</a><br>
	<a href="/admin/searchTeacherByName">Search a Teacher</a><br>
	<br>
	<a href="/test/all">View all Tests</a><br>
	<a href="/admin/addTest">Add a Test</a><br>
	<a href="/admin/deleteTest">Delete a Test</a><br>
</body>
</html>