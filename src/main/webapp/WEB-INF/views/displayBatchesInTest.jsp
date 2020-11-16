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
<title>Batches in Test</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="page">
		<form-title>
		<center>Batches appearing in the Test</center>
		</form-title>
		<div class="page-contents">
			<div class="info display-inline">
				<div class="page-label">Test Id:</div>
				<div class="label-result">${test.getTestId()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Test Name:</div>
				<div class="label-result">${test.getTestName()}</div>
			</div>
			<br>
			<div class="info display-inline">
				<div class="page-label">Test Date:</div>
				<div class="label-result">${test.getTestDate()}</div>
			</div>
			<br>
			<div class="info">
				<div class="page-label">List of Batches appearing for this
					Test:</div>
				<div class="table-container">
					<table border="1">
						<tr>
							<th>Batch Id</th>
							<th>Batch Name</th>
						</tr>
						<c:forEach items="${batchesInThisTest}" var="batch">
							<tr>
								<td><a href="/batch/profile/${batch.getBatchId()}">${batch.getBatchId()}</a></td>
								<td>${batch.getBatchName()}</td>
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