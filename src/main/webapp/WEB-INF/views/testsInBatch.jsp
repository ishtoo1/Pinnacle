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
<title>View Tests in Batch</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="form-container">
		<div class="form">
			<form-title>
			<center>View Tests in Batch</center>
			</form-title>
			<form class="form-registration" action="" method="post" name="form">
				<label for="batchId">Batch</label> <select class="form-styling"
					id="batchId" name="batchId" required="required">
					<option value="">Choose a Batch</option>
					<c:forEach var="batch" items="${allBatches}">
						<option value="${batch.getBatchId()}">${batch.getBatchId()}
							${batch.getBatchName()}</option>
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

	<%@ include file="footer.jsp"%>
</body>
</html>