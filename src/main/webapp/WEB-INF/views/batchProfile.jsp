<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Batch Profile</title>
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
	<h3>Batch Profile</h3>
	Batch Id: ${batch.getBatchId()}<br>
	Batch Name: ${batch.getBatchName()}<br>
	Starting Date: ${batch.getStartingDate()}<br>
	End Date: ${batch.getEndDate()}<br>
	List of Subjects in this Batch:
	<table border="1">
	<tr>
	    <th>Subject Id</th>
	    <th>Subject Name</th>
	    <th>Teacher Id</th>
	</tr>
	<c:forEach items="${subjectsInThisBatch}" var="subject">
    <tr>      
        <td><a href="/batch/${batch.getBatchId()}/subject/profile/${subject.getSubjectId()}">${subject.getSubjectId()}</a></td>
        <td>${subject.getSubjectName()}</td>
        <td>${subject.getTeacherId()}</td>
    </tr>
	</c:forEach>
	</table>
	<br>
	List of Students in this Batch:
	<table border="1">
	<tr>
	    <th>UserName</th>
	    <th>Student Name</th>
	</tr>
	<c:forEach items="${studentsInThisBatch}" var="student">
    <tr>      
        <td><a href="/student/profile/${student.getUsername()}">${student.getUsername()}</a></td>
        <td>${student.getStudentName()}</td>
    </tr>
	</c:forEach>
	</table>
</body>
</html>