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
<title>Batch Addition</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Batch Addition</center>
			</form-title>
			<form class="form-registration" action="" method="post" name="form">
				<label for="batchId">Batch Id</label> <input class="form-styling"
					type="text" id="batchId" name="batchId" maxlength="10"
					required="required"><br> <label for="batchName">Batch
					Name</label> <input class="form-styling" type="text" id="batchName"
					name="batchName" maxlength="40" required="required"><br>
				<label for="startingDate">Batch Starting Date</label> <input
					class="form-styling" type="date" id="startingDate"
					name="startingDate" required="required"><br> <label
					for="endDate">Batch Ending Date</label> <input class="form-styling"
					type="date" id="endDate" name="endDate" required="required"><br>
				<label for="fees">Fees</label> <input class="form-styling"
					type="number" id="fees" name="fees" min="0" max="200000"
					required="required"><br><br>
				<div class="btn-animate">
					<input class="btn-submit" type="submit" value="Submit">
				</div>
			</form>
			<br> <br>
			<center>
				<p style="color: red;">${error}</p>
			</center>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>