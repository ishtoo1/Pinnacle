<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Teacher Registration</title>
</head>
<body>
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
	<h3>Teacher Registration</h3>
	<form action="" method="post">
	  <label for="teacherName">Teacher Name:</label><br>
	  <input type="text" id="teacherName" name="teacherName" maxlength="40" required="required"><br>
	  <label for="username">UserName:</label><br>
	  <input type="text" id="username" name="username" maxlength="20" required="required"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" id="password" name="password" maxlength="256" required="required"><br>
	  <label for="confirm_password">Confirm Password:</label><br>
	  <input type="password" id="confirm_password" name="confirm_password" maxlength="256" required="required"><br>
	  <label for="dateOfBirth">Date of Birth:</label><br>
	  <input type="date" id="dateOfBirth" name="dateOfBirth" required="required"><br>
	  <label for="gender">Gender:</label><br>
	  <input type="radio" id="male" name="gender" value="male">
	  <label for="male">Male</label><br>
	  <input type="radio" id="female" name="gender" value="female">
	  <label for="female">Female</label><br>
	  <input type="radio" id="other" name="gender" value="other">
	  <label for="other">Other</label><br>
	  <label for="address">Address:</label><br>
	  <input type="text" id="address" name="address" maxlength="100" required="required"><br>
	  <label for="contactNumber">Contact Number:</label><br>
	  <input type="number" id="contactNumber" name="contactNumber" min="1000000000" max="9999999999" required="required"><br>
	  <label for="joiningDate">Joining Date:</label><br>
	  <input type="date" id="joiningDate" name="joiningDate" required="required"><br>
	  <input type="submit" value="Submit">
	</form>
	<p style="color:red;">${error}</p>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		var password = document.getElementById("password")
		  , confirm_password = document.getElementById("confirm_password");
	
		function validatePassword(){
		  if(password.value != confirm_password.value) {
		    confirm_password.setCustomValidity("Passwords Don't Match");
		  } else {
		    confirm_password.setCustomValidity('');
		  }
		}
	
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>
</html>