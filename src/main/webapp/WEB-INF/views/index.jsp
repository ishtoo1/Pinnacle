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
<title>Home</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<!--about start here-->
	<div class="about" id="about">
		<div class="container">
			<div class="about-main">
				<div class="about-top wow fadeInDown" data-wow-delay="0.3s">
					<h2>About</h2>
				</div>
				<div class="about-bottom">
					<div class="col-md-6 about-left wow fadeInLeft"
						data-wow-delay="0.3s">
						<h4>Why Pinnacle?</h4>
						<div class="about-grid">
							<div class="about-icon">
								<a class="#"> <span class="learn"> </span>
								</a>
							</div>
							<div class="about-text">
								<h5>Study Resources</h5>
								<p>Pinnacle's study resources includes best quality practice
									assignment to give students a complete exposure to concepts.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="about-grid">
							<div class="about-icon">
								<a class="#"> <span class="degr"> </span>
								</a>
							</div>
							<div class="about-text">
								<h5>Personalized Coaching</h5>
								<p>We never increase our batch size beyond a limit to ensure
									proper Student-Faculty interaction. Individual attention is
									given to each student.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="about-grid">
							<div class="about-icon">
								<a class="#"> <span class="ab-badge"> </span>
								</a>
							</div>
							<div class="about-text">
								<h5>Pattern Proof Teaching</h5>
								<p>Pinnacle has been teaching in a comprehensive manner so
									that students do well in IIT-JEE, Boards, NTSE, KVPY, Olympiads
									and other Engineering Entrance exams.</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="col-md-6 about-right wow fadeInRight"
						data-wow-delay="0.3s">
						<img src="images/ab.jpg" class="img-responsive" alt="">
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--about end here-->
	<!--team start here-->
	<div class="team">
		<div class="container">
			<div class="team-main">
				<div class="team-top wow fadeInDown" data-wow-delay="0.3s">
					<h3>Our Team</h3>
				</div>
				<div class="team-bottom wow fadeInRight" data-wow-delay="0.3s">
					<div class="col-md-3 team-grids">
						<!-- normal -->
						<div class="ih-item circle effect5">
							<a href="https://www.linkedin.com/in/vikas-kapoor-6a3852139/">
								<div class="img">
									<img src="images/t1.jpg" alt="img" class="img-responsive">
								</div>
								<div class="info">
									<div class="info-back">
										<h3>Vikas Kapoor</h3>
									</div>
								</div>
							</a>
						</div>
						<div class="team-bottom">
							<p>Founder</p>
						</div>

						<!-- end normal -->
					</div>
					<div class="col-md-3 team-grids">
						<!-- normal -->
						<div class="ih-item circle effect5">
							<a href="https://www.linkedin.com/in/ishtmeetsingh/">
								<div class="img">
									<img src="images/t2.jpeg" alt="img" class="img-responsive">
								</div>
								<div class="info">
									<div class="info-back">
										<h3>Ishtmeet Singh</h3>
									</div>
								</div>
							</a>
						</div>
						<div class="team-bottom">
							<p>Physics Facutly Head</p>
						</div>
						<!-- end normal -->
					</div>
					<div class="col-md-3 team-grids">
						<!-- normal -->
						<div class="ih-item circle effect5">
							<a href="https://www.linkedin.com/in/naman-garg-0637281a7/">
								<div class="img">
									<img src="images/t3.jpg" alt="img" class="img-responsive">
								</div>
								<div class="info">
									<div class="info-back">
										<h3>Naman Garg</h3>
									</div>
								</div>
							</a>
						</div>
						<div class="team-bottom">
							<p>Chemistry Faculty Head</p>
						</div>
						<!-- end normal -->
					</div>
					<div class="col-md-3 team-grids">
						<!-- normal -->
						<div class="ih-item circle effect5">
							<a href="https://www.linkedin.com/in/kushagra-juneja-187763168/">
								<div class="img">
									<img src="images/t4.jpg" alt="img" class="img-responsive">
								</div>
								<div class="info">
									<div class="info-back">
										<h3>Kushagra Juneja</h3>
									</div>
								</div>
							</a>
						</div>
						<div class="team-bottom">
							<p>Maths Faculty Head</p>
						</div>

						<!-- end normal -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<!--team end here-->

	<!--advantages start here-->
	<div class="advantages">
		<div class="container">
			<div class="advantages-main">
				<h4>Founder's message</h4>
				<div class="col-md-6 advantage-left wow fadeInLeft"
					data-wow-delay="0.3s">
					<h3>Vikas Kapoor (AIR 365 in JEE '97)</h3>
					<p>
						Every moment in life is a fresh beginning. That brings with it the
						boundless joy of new possibilities. And the pleasure is compounded
						when you know, you are about to realize a lofty goal. Since the
						time we began in 2006, we have constantly strived for excellence.
						Like for the many young minds we have shaped, it's the winning
						spirit that has been of essence. Even quintessential, if you look
						at it from our perspective.<br>
						<b>Pinnacle</b> has always been innovating to create excellence. I
						am sure the varied services that this web-site offers our existing
						and aspiring students will be a great boon to students.
					</p>
				</div>
				<div class="col-md-6 advantage-rit wow fadeInRight"
					data-wow-delay="0.3s">
					<div class="advant-layer1">
						<div class="adv-layer1-text">
							<h6>Vikas Kapoor</h6>
							<p>Founder</p>
						</div>
						<div class="advater-img">
							<img src="images/s2.png" width="90%" height="90%" alt="">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="advant-layer2">
						<div class="advater-img">
							<img src="images/arrow2.png" alt="">
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--advantages end here-->
	<%@ include file="footer.jsp"%>
</body>
</html>