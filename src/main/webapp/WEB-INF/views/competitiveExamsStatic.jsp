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
<title>Competitive Exams</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="dropdown-list-container">
		<form-title>
		<center>Competitive Exams</center>
		</form-title>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">JEE Advanced</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li>JEE Advanced</li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">JEE Main</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li>JEE Main</li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">CBSE</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li>CBSE</li>
				</ul>
			</div>
		</div>

		<div class="dropdown-list-element">
			<div class="dropdown-title-container not-border"
				onclick="toggleBox(this)">
				<div class="arrow"></div>
				<div class="title-text">And Other Exams</div>
			</div>
			<div class="content">
				<ul class="dropdown-list-items">
					<li>KVPY, Olympiads</li>
				</ul>
			</div>
		</div>

	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>