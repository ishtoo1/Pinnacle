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
<title>Search Teacher by Name</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Search Teacher by Name</center>
			</form-title>
			<form class="form-registration" name="form">
				<label for="teacherName">Teacher Name</label> <input
					class="form-styling" type="text" id="teacherName"
					name="teacherName" maxlength="40" required="required"><br>
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

	<p id="showTeachers"></p>
	<br>
	<br>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							function nameSelectOnChange() {
								var teacherName = $("#teacherName").val();
								var data = {};
								var url = "/student/searchTeacher/"
										+ teacherName;
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
													var i;
													document
															.getElementById("showTeachers").innerHTML = ('<table id="demoTable"> <tr> <th>Teacher Id</th> <th>Teacher Name</th> <th>UserName</th> </tr>');
													for (i = 0; i < data.length; i++) {
														document
																.getElementById("demoTable").innerHTML += '<tr> <td>'
																+ data[i].teacherId
																+ '</td> <td>'
																+ data[i].teacherName
																+ '</td> <td><a href="/teacher/profile/' + data[i].username + '">'
																+ data[i].username
																+ '</a></td> </tr>';
													}
													document
															.getElementById("showTeachers").innerHTML += '</table>';
												} else {
													document
															.getElementById("showTeachers").innerHTML = ('<center> <p style="color:red;">No results</p> </center>');
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
								$("#submit").click(nameSelectOnChange);
							});
						})
	</script>

	<%@ include file="footer.jsp"%>
</body>

</html>