<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
	function toggleBox(x) {
		x.classList.toggle('opened');
	}
</script>
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<c:set var="messagePopup" value="${success}" />
	<c:set var="lenMessage" value="${fn:length(messagePopup)}" />
	<c:if test="${lenMessage>0}">
		<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#successMessage').delay(1000).fadeOut();
			});
		</script>
	</c:if>
	<div id="successMessage" style="color: #009c9e;">
		<center>${success}</center>
	</div>
	<div class="dropdown-list-container">
		<form-title>
		<center>Admin</center>
		</form-title>


		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Students</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/student/booksIssuedToStudent">View all Books
							issued to a Student</a></li>
					<li><a href="/student/all">View all Students</a></li>
					<li><a href="/admin/registerStudent">Add a Student</a></li>
					<li><a href="/admin/deleteStudent">Remove a Student</a></li>
					<li><a href="/admin/searchStudentByName">Search a Student</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Teachers</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/teacher/all">View all Teachers</a></li>
					<li><a href="/teacher/viewSubjectsTaughtByTeacher">View
							all Subjects taught by a Teacher</a></li>
					<li><a href="/admin/registerTeacher">Add a Teacher</a></li>
					<li><a href="/admin/deleteTeacher">Remove a Teacher</a></li>
					<li><a href="/admin/searchTeacherByName">Search a Teacher</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Batches</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/batch/all">View all Batches</a></li>
					<li><a href="/batch/viewStudentsInBatch">View all Students
							of a Batch</a></li>
					<li><a href="/batch/viewTestsInBatch">View all Tests
							scheduled for a Batch</a></li>
					<li><a href="/batch/viewSubjectsInBatch">View all Subjects
							of a Batch</a></li>
					<li><a href="/admin/addBatch">Add a Batch</a></li>
					<li><a href="/admin/deleteBatch">Delete a Batch</a></li>
					<li><a href="/admin/openAdmissionsForBatch">Open
							Admissions for a Batch</a></li>
					<li><a href="/admin/closeAdmissionsForBatch">Close
							Admissions for a Batch</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Tests</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/test/all">View all Tests</a></li>
					<li><a href="/test/viewBatchesInTest">View Batches that
							have a particular Test scheduled</a></li>
					<li><a href="/admin/addTest">Add a Test</a></li>
					<li><a href="/admin/deleteTest">Delete a Test</a></li>
					<li><a href="/admin/addTestToBatch">Schedule a Test for a
							Batch</a></li>
					<li><a href="/admin/deleteTestFromBatch">Remove a Test
							from the Schedule of a Batch</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Subjects</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/admin/addSubject">Add a Subject</a></li>
					<li><a href="/admin/deleteSubject">Remove a Subject</a></li>
					<li><a href="/admin/changeSubjectTeacher">Change the
							teacher of a Subject</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Lectures</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/batch/viewLecturesInSubject">View all
							Lectures of a Subject</a></li>
					<li><a href="/admin/addLecture">Add Lecture to a Subject</a></li>
					<li><a href="/admin/deleteLecture">Remove a Lecture from a
							Subject</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Books</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/book/findOwnerOfBook">View the current
							Student owner of a Book</a></li>
					<li><a href="/book/all">View all Books</a></li>
					<li><a href="/admin/addBook">Add a Book</a></li>
					<li><a href="/admin/deleteBook">Delete a Book</a></li>
					<li><a href="/admin/issueBook">Issue a Book to a Student</a></li>
					<li><a href="/admin/returnBook">Return a Book from a
							Student</a></li>
				</ul>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>