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
<title>Teacher</title>
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
		<center>Teacher</center>
		</form-title>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage my Subjects</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/teacher/${userStr}/subject/all">List of my
							Subjects</a></li>
					<li><a href="/teacher/${userStr}/addLecture">Add Lecture
							to Subjects I'm taking</a></li>
					<li><a href="/teacher/${userStr}/deleteLecture">Remove a
							Lecture from Subjects I'm taking</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage Students</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/student/all">View all Students</a></li>
					<li><a href="/teacher/registerStudent">Add a Student</a></li>
					<li><a href="/teacher/deleteStudent">Remove a Student</a></li>
					<li><a href="/teacher/searchStudentByName">Search a
							Student</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">View Teachers</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/teacher/all">View all Teachers</a></li>
					<li><a href="/teacher/searchTeacherByName">Search a
							Teacher</a></li>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>