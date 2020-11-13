<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Lecture from Subject</title>
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
	<h3>Delete Lecture from Subject</h3>
	<form action="" method="post">
	  <select id="batchId" name="batchId" required="required">
	  	<option value="">Choose a Batch</option>
	    <c:forEach var="batch" items="${allBatches}">
	       <option value="${batch.getBatchId()}">${batch.getBatchId()} ${batch.getBatchName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <select id="subjectId" name="subjectId" required="required">
	  	<option value="">Choose a Subject</option>
	    <c:forEach var="subject" items="${allSubjectsInThisBatch}">
	       <option value="${subject.getSubjectId()}">${subject.getSubjectId()} ${subject.getSubjectName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <select id="lectureId" name="lectureId" required="required">
	  	<option value="">Choose a Lecture</option>
	    <c:forEach var="lecture" items="${allLecturesInThisSubject}">
	       <option value="${lecture.getLectureId()}">${lecture.getLectureId()} ${lecture.getLectureName()}</option>   
	   </c:forEach>
	  </select>
	  <br>
	  <input type="submit" value="Submit">
	</form>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	$ (document).ready(function(){
	    function batchSelectOnChange() {
	        var batchId = $("#batchId").val();
	        var data = {};
	        var url = "/admin/changeSubjectInit/" + batchId;
	        console.log(url);
	        $.ajax({
	            url: url,
	            type: "post",
	            data: data,
	            success: function (data, status, xhr) {
	                $('#subjectId').find('option').remove();
	                $('#subjectId').append('<option value="">Choose a Subject</option>');
	                console.log(data);
	                for (var i = 0; i < data.length; i++) {
	                    $('#subjectId').append('<option value = "' + data[i].subjectId + '">' + data[i].subjectId + ' - ' + data[i].subjectName + '</option>');
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
	        $("#batchId").change(batchSelectOnChange);
	    });
	    function subjectSelectOnChange() {
	        var subjectId = $("#subjectId").val();
	        var batchId = $("#batchId").val();
	        var data = {};
	        var url = "/admin/findLecturesInThisSubject/" + batchId + "/" + subjectId;
	        console.log(url);
	        $.ajax({
	            url: url,
	            type: "post",
	            data: data,
	            success: function (data, status, xhr) {
	                $('#lectureId').find('option').remove();
	                $('#lectureId').append('<option value="">Choose a Lecture</option>');
	                console.log(data);
	                for (var i = 0; i < data.length; i++) {
	                    $('#lectureId').append('<option value = "' + data[i].lectureId + '">' + data[i].lectureId + ' - ' + data[i].lectureName + '</option>');
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
	        $("#subjectId").change(subjectSelectOnChange);
	    });
	})
	</script>
</body>
</html>