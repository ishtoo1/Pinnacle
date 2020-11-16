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
<title>Tests</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="page">
		<form-title>
		<center>Tests</center>
		</form-title>
		<div class="page-contents">
			<div class="info">
				<div class="page-label">List of Tests:</div>
				<div class="table-container">
					<table border="1">
						<tr>
							<th>Test Id</th>
							<th>Test Name</th>
							<th>Test Date</th>
							<th>Total Marks</th>
						</tr>
						<c:forEach items="${allTests}" var="test">
							<tr>
								<td>${test.getTestId()}</td>
								<td>${test.getTestName()}</td>
								<td>${test.getTestDate()}</td>
								<td>${test.getTotalMarks()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<br>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>

</html>