<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Owner of Book</title>
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
	<h3>Find owner of Book</h3>
	<form action="" method="post">
	  <select id="bookId" name="bookId" required="required">
	  	<option value="">Choose a Book</option>
	    <c:forEach var="book" items="${allBooks}">
	       <option value="${book.getBookId()}">${book.getBookId()}: ${book.getBookName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Submit">
	</form>
	<p style="color:red;">${error}</p>
	<c:set var = "userStr" value = "${student.getUsername()}"/>
	<c:set var = "len" value = "${fn:length(userStr)}"/>
	<c:if test="${len>0}">
		Student Id: ${student.getStudentId()}<br>
		Student Name: ${student.getStudentName()}<br>
		Student UserName: <a href="/student/profile/${student.getUsername()}">${student.getUsername()}</a><br>
		Student BatchId: <a href="/batch/profile/${student.getBatchId()}">${student.getBatchId()}</a><br>
	</c:if>
</body>
</html>