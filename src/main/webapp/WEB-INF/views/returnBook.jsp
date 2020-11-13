<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Return Book</title>
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
	<h3>Return Book</h3>
	<form action="" method="post">
	  <label for="username">Student UserName:</label><br>
	  <input type="text" id="username" name="username" maxlength="20" required="required"><br>
	  <select id="bookId" name="bookId" required="required">
	  	<option value="">Choose a Book</option>
	    <c:forEach var="book" items="${allBooksIssuedToStudent}">
	       <option value="${book.getBookId()}">${book.getBookId()} ${book.getBookName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Submit">
	</form>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	$ (document).ready(function(){
	    function usernameSelectOnChange() {
	        var username = $("#username").val();
	        var data = {};
	        var url = "/admin/findAllBooksIssuedToStudent/" + username;
	        console.log(url);
	        $.ajax({
	            url: url,
	            type: "post",
	            data: data,
	            success: function (data, status, xhr) {
	                $('#bookId').find('option').remove();
	                $('#bookId').append('<option value="">Choose a Book</option>');
	                console.log(data);
	                for (var i = 0; i < data.length; i++) {
	                    $('#bookId').append('<option value = "' + data[i].bookId + '">' + data[i].bookId + ' - ' + data[i].bookName + '</option>');
	                }
	            },
	            
	            error: function (xhr, status, err) {
	                // alert(status);
	                console.log(xhr);
	                console.log(status);
	                console.log(err);
	            }
	        });
	    }
	    $(function () {
	        $("#username").blur(usernameSelectOnChange);
	    });
	})
	</script>
</body>
</html>