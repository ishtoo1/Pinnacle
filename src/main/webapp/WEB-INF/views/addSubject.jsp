<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Subject</title>
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
	<h3>Add Subject</h3>
	<form action="" method="post">
	  <label for="subjectId">Subject Id:</label><br>
	  <input type="text" id="subjectId" name="subjectId" maxlength="10" required="required"><br>
	  <label for="subjectName">Subject Name:</label><br>
	  <input type="text" id="subjectName" name="subjectName" maxlength="40" required="required"><br>
	  <select id="batchId" name="batchId" required="required">
	  	<option value="">Choose a Batch</option>
	    <c:forEach var="batch" items="${allBatches}">
	       <option value="${batch.getBatchId()}">${batch.getBatchId()} ${batch.getBatchName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <select id="teacherId" name="teacherId" required="required">
	  	<option value="">Choose a Teacher</option>
	    <c:forEach var="teacher" items="${allTeachers}">
	       <option value="${teacher.getTeacherId()}">${teacher.getUsername()}: ${teacher.getTeacherName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Submit">
	</form>
	<p style="color:red;">${error}</p>
</body>
</html>