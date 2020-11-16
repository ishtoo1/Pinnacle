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
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="ISO-8859-1">
<title>Student Profile</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="page">
		<form-title>
		<center>Student Profile</center>
		</form-title>
		<div class="page-contents">
			<div class="info display-inline">
				<div class="page-label">Student Id:</div>
				<div class="label-result">${student.getStudentId()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Student Name:</div>
				<div class="label-result">${student.getStudentName()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">UserName:</div>
				<div class="label-result">
					<a href="/student/profile/${loginAccount.getUsername()}">${loginAccount.getUsername()}</a>
				</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Role:</div>
				<div class="label-result">Student</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Date of Birth:</div>
				<div class="label-result">${student.getDateOfBirth()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Gender:</div>
				<div class="label-result">${student.getGender()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Address:</div>
				<div class="label-result">${student.getAddress()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Contact Number:</div>
				<div class="label-result">${student.getContactNumber()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Batch Id:</div>
				<div class="label-result">
					<a href="/batch/profile/${student.getBatchId()}">${student.getBatchId()}</a>
				</div>
			</div>
			<br>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>

</html>