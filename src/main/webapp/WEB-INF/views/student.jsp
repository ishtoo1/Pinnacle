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
<title>Student</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<div class="dropdown-list-container">
		<form-title>
		<center>Student</center>
		</form-title>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Manage my Batch</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/student/${userStr}/viewBatchProfile">My
							Batch</a></li>
					<li><a href="/student/${userStr}/book/all">View Books
							issued to me</a></li>
					<li><a href="/student/${userStr}/viewUpcomingTestsForBatch">View
							Upcoming Tests for my Batch</a></li>
					<li><a href="/student/${userStr}/viewSubjectsInBatch">View
							all Subjects in my Batch</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Search a Student</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/student/searchStudentByName">Search a
							Student</a></li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">Search a Teacher</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li><a href="/student/searchTeacherByName">Search a
							Teacher</a></li>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>