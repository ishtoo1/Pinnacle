<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration</title>
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
	<h3>Student Registration</h3>
	<form action="" method="post">
	  <label for="studentName">Student Name:</label><br>
	  <input type="text" id="studentName" name="studentName" maxlength="40" required="required"><br>
	  <label for="username">UserName:</label><br>
	  <input type="text" id="username" name="username" maxlength="20" required="required"><br>
	  <label for="password">Password:</label><br>
	  <input type="password" id="password" name="password" maxlength="256" required="required"><br>
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
	  <select id="batchId" name="batchId" required="required">
	  	<option value="">Choose a Batch</option>
	    <c:forEach var="batch" items="${allOpenBatches}">
	       <option value="${batch.getBatchId()}">${batch.getBatchId()} ${batch.getBatchName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Submit">
	</form>
	<p style="color:red;">${error}</p>
</body>
</html>