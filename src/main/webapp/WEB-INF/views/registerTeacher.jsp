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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Teacher Registration</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Teacher Registration</center>
			</form-title>
			<form class="form-registration" action="" method="post">
				<label for="teacherName">Teacher Name</label> <input
					class="form-styling" type="text" id="teacherName"
					name="teacherName" maxlength="40" required="required"><br>
				<label for="username">UserName</label> <input class="form-styling"
					type="text" id="username" name="username" maxlength="20"
					required="required"><br> <label for="password">Password</label>
				<input class="form-styling" type="password" id="password"
					name="password" maxlength="256" required="required"><br>
				<label for="confirm_password">Confirm Password</label> <input
					class="form-styling" type="password" id="confirm_password"
					name="confirm_password" maxlength="256" required="required"><br>
				<label for="dateOfBirth">Date of Birth</label> <input
					class="form-styling" type="date" id="dateOfBirth"
					name="dateOfBirth" required="required"><br> <label
					for="gender">Gender</label> <input type="radio" id="male"
					name="gender" value="male">
				<radio-label for="male">Male</radio-label>
				<br> <input type="radio" id="female" name="gender"
					value="female">
				<radio-label for="female">Female</radio-label>
				<br> <input type="radio" id="other" name="gender" value="other">
				<radio-label for="other">Other</radio-label>
				<br>
				<br> <label for="address">Address</label> <input
					class="form-styling" type="text" id="address" name="address"
					maxlength="100" required="required"><br> <label
					for="contactNumber">Contact Number</label> <input
					class="form-styling" type="number" id="contactNumber"
					name="contactNumber" min="1000000000" max="9999999999"
					required="required"><br> <label for="joiningDate">Joining
					Date</label> <input class="form-styling" type="date" id="joiningDate"
					name="joiningDate" required="required"><br>
				<br>
				<div class="btn-animate">
					<input class="btn-submit" type="submit" value="Submit">
				</div>
			</form>
			<br>
			<br>
			<center>
				<p style="color: red;">${error}</p>
			</center>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		var password = document.getElementById("password"), confirm_password = document
				.getElementById("confirm_password");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password.setCustomValidity("Passwords Don't Match");
			} else {
				confirm_password.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>

	<%@ include file="footer.jsp"%>
</body>
</html>