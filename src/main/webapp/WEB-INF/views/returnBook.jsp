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
<title>Return Book</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Return Book</center>
			</form-title>
			<form class="form-registration" action="" method="post" name="form">
				<label for="username">Student UserName</label> <input
					class="form-styling" type="text" id="username" name="username"
					maxlength="20" required="required"><br> <label
					for="bookId">Book</label> <select class="form-styling" id="bookId"
					name="bookId" required="required">
					<option value="">Choose a Book</option>
					<c:forEach var="book" items="${allBooksIssuedToStudent}">
						<option value="${book.getBookId()}">${book.getBookId()}
							${book.getBookName()}</option>
					</c:forEach>
				</select> <br> <br>
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							function usernameSelectOnChange() {
								var username = $("#username").val();
								var data = {};
								var url = "/admin/findAllBooksIssuedToStudent/"
										+ username;
								console.log(url);
								$
										.ajax({
											url : url,
											type : "post",
											data : data,
											success : function(data, status,
													xhr) {
												$('#bookId').find('option')
														.remove();
												$('#bookId')
														.append(
																'<option value="">Choose a Book</option>');
												console.log(data);
												for (var i = 0; i < data.length; i++) {
													$('#bookId')
															.append(
																	'<option value = "' + data[i].bookId + '">'
																			+ data[i].bookId
																			+ ' - '
																			+ data[i].bookName
																			+ '</option>');
												}
											},

											error : function(xhr, status, err) {
												// alert(status);
												console.log(xhr);
												console.log(status);
												console.log(err);
											}
										});
							}
							$(function() {
								$("#username").blur(usernameSelectOnChange);
							});
						})
	</script>

	<%@ include file="footer.jsp"%>
</body>
</html>