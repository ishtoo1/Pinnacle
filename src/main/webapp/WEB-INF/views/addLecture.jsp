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
<title>Add Lecture to Subject</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="form-container">
		<div class="form">
			<form-title>
			<center>Add Lecture to Subject</center>
			</form-title>
			<form class="form-registration" action="" method="post" name="form">
				<label for="batchId">Batch</label> <select class="form-styling"
					id="batchId" name="batchId" required="required">
					<option value="">Choose a Batch</option>
					<c:forEach var="batch" items="${allBatches}">
						<option value="${batch.getBatchId()}">${batch.getBatchId()}
							${batch.getBatchName()}</option>
					</c:forEach>
				</select> <br> <label for="subjectId">Subject</label> <select
					class="form-styling" id="subjectId" name="subjectId"
					required="required">
					<option value="">Choose a Subject</option>
					<c:forEach var="subject" items="${allSubjectsInThisBatch}">
						<option value="${subject.getSubjectId()}">${subject.getSubjectId()}
							${subject.getSubjectName()}</option>
					</c:forEach>
				</select> <br> <label for="lectureName">Lecture Name</label> <input
					class="form-styling" type="text" id="lectureName"
					name="lectureName" maxlength="40" required="required"><br>
				<br>
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
							function batchSelectOnChange() {
								var batchId = $("#batchId").val();
								var data = {};
								var url = "/admin/changeSubjectInit/" + batchId;
								console.log(url);
								$
										.ajax({
											url : url,
											type : "post",
											data : data,
											success : function(data, status,
													xhr) {
												$('#subjectId').find('option')
														.remove();
												$('#subjectId')
														.append(
																'<option value="">Choose a Subject</option>');
												console.log(data);
												for (var i = 0; i < data.length; i++) {
													$('#subjectId')
															.append(
																	'<option value = "' + data[i].subjectId + '">'
																			+ data[i].subjectId
																			+ ' - '
																			+ data[i].subjectName
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
								$("#batchId").change(batchSelectOnChange);
							});
						})
	</script>

	<%@ include file="footer.jsp"%>
</body>
</html>