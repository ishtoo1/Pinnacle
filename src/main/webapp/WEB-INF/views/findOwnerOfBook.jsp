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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Find Owner of Book</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Find owner of Book</center>
			</form-title>
			<form class="form-registration" name="form">
				<label for="bookId">Book</label> <select class="form-styling"
					id="bookId" name="bookId" required="required">
					<option value="">Choose a Book</option>
					<c:forEach var="book" items="${allBooks}">
						<option value="${book.getBookId()}">${book.getBookId()}:
							${book.getBookName()}</option>
					</c:forEach>
				</select> <br>
				<br>
				<div class="btn-animate">
					<div id="submit" class="btn-submit" type="submit">
						<center>Submit</center>
					</div>
				</div>
			</form>
			<br>
			<br>
		</div>
	</div>

	<p id="displayOwner"></p>
	<br>
	<br>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							function bookSelectOnChange() {
								var bookId = $("#bookId").val();
								var data = {};
								var url = "/book/findOwner/" + bookId;
								console.log(url);
								$
										.ajax({
											url : url,
											type : "post",
											data : data,
											success : function(data, status,
													xhr) {
												console.log(data);
												if (data.length >= 1) {
													document
															.getElementById("displayOwner").innerHTML = ('<table> <tr> <th>Student Id</th> <th>Student Name</th> <th>UserName</th> <th>Batch Id</th> </tr> <tr> <td>'
															+ data[0].studentId
															+ '</td> <td>'
															+ data[0].studentName
															+ '</td> <td><a href="/student/profile/' + data[0].username + '">'
															+ data[0].username
															+ '</a></td> <td><a href="/batch/profile/' + data[0].batchId + '">'
															+ data[0].batchId + '</a></td> </tr> </table>');
												} else {
													document
															.getElementById("displayOwner").innerHTML = ('<center> <p style="color:red;">This book is not issued to any Student currently</p> </center>');
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
								$("#submit").click(bookSelectOnChange);
							});
						})
	</script>

	<%@ include file="footer.jsp"%>
</body>

</html>