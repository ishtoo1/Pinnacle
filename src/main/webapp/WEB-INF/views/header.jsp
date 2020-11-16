<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="header">
	<div class="fixed-header">
		<div class="navbar-wrapper">
			<div class="container">
				<nav class="navbar navbar-inverse navbar-static-top">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<div class="" data-wow-delay="">
							<a class="navbar-brand" href="/"><img
								src="/images/logo-2.png" width="200" height="66.4"></a>
						</div>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<nav class="cl-effect-1">
							<ul class="nav navbar-nav">
								<li><a href="/">Home</a></li>
								<li><a href="/results">Results</a></li>
								<li><a href="/competitiveExams">Competitive Exams</a></li>
								<c:set var="userStr" value="${loggedInAccount.getUsername()}" />
								<c:set var="roleStr" value="${loggedInAccount.getRole()}" />
								<c:set var="len" value="${fn:length(userStr)}" />
								<c:if test="${len>0}">
									<c:if test="${roleStr == 'ROLE_admin'}">
										<li><a href="/welcome">Function</a></li>
										<li><a style="color: #009c9e;"
											href="/admin/profile/${userStr}">${userStr}</a></li>
										<li><a href="/logout">Logout</a></li>
									</c:if>
									<c:if test="${roleStr == 'ROLE_teacher'}">
										<li><a href="/welcome">Function</a></li>
										<li><a style="color: #009c9e;"
											href="/teacher/profile/${userStr}">${userStr}</a></li>
										<li><a href="/logout">Logout</a></li>
									</c:if>
									<c:if test="${roleStr == 'ROLE_student'}">
										<li><a href="/welcome">Function</a></li>
										<li><a style="color: #009c9e;"
											href="/student/profile/${userStr}">${userStr}</a></li>
										<li><a href="/logout">Logout</a></li>
									</c:if>
								</c:if>
								<c:if test="${len==0}">
									<li><a href="/registerStudent">Register Student</a></li>
									<li><a href="/login">Login</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
					<div class="clearfix"></div>
				</nav>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>