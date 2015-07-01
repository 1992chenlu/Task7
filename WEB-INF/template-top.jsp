<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700'
	rel='stylesheet' type='text/css'>

<link href="<%=basePath%>style/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>style/font-awesome.css">
<link rel="stylesheet" href="<%=basePath%>style/flexslider.css">
<link rel="stylesheet" href="<%=basePath%>style/prettyPhoto.css">
<link href="<%=basePath%>style/style.css" rel="stylesheet">
<link href="<%=basePath%>style/bootstrap-responsive.css"
	rel="stylesheet">

<link rel="shortcut icon" href="<%=basePath%>/img/favicon/favicon.png">
<title>Mutual Fund</title>

</head>
 <style type="text/css">
 *{margin:0px;padding:0px;}
 .footer{position:fixed;left:0px;bottom:0px;height:50px;width:100%;background:#2d2d2d;}
 </style>
<body>
	<!-- Navbar starts -->

	<!-- Navigation bar is one table cell down the left side -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<c:choose>
							<c:when test="${ (empty title) }">
								<li><a>Welcome to Carnegie Financial Service, </a></li>
								<c:choose>
									<c:when test="${not empty employee}">
										<li><a>${employee.firstname } ${employee.lastname }</a></li>
									</c:when>
									<c:when test="${not empty customer}">
										<li><a>${customer.firstname } ${customer.lastname }</a>
									</li>
							</c:when>
						</c:choose>
						</c:when>
						<c:otherwise>
							<li><a><%=request.getAttribute("title")%></a></li>
						</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Navbar ends -->


	<div class="content">

		<div class="sidebar">
			<!-- Logo -->
			<div class="logo">
				<a href="#"><img src="logo.png" alt="" /></a>
			</div>
			<div class="sidebar-dropdown"></div>

			<!--- Sidebar navigation -->
			<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->

			<!-- Colors: Add the class "br-red" or "br-blue" or "br-green" or "br-orange" or "br-purple" or "br-yellow" to anchor tags to create colorful left border -->
			<div class="s-content">

				<ul id="nav">
					<c:choose>

						<c:when test="${not empty employee}">
							<!-- Main menu with font awesome icon -->
							<li><a href="changePasswordEmployee.do" class="open br-red">Change
									Password</a></li>
							<li><a href="createEmployeeAccount.do" class="br-blue">Create
									Employee Account</a></li>
							<li><a href="createCustomerAccount.do" class="br-green">Create
									Customer Account </a></li>
							<li><a href="viewCustomerAccount.do" class="br-orange">View Customers</a></li>		
							<li><a href="transition.do" class="br-yellow">Transition Day </a></li>
							<li><a href="createFund.do" class="br-purple">Create Fund</a></li>
							<li><a href="showTemporary.do" class="br-purple">View Pending Transactions</a></li>
							<li><a href="logoutEmployee.do" class="br-red">Logout
							</a></li>

						</c:when>
						<c:when test="${not empty customer}">

							<li><a href="changePasswordCustomer.do" class="open br-red"></i>Change
									Password</a></li>
							<li><a href="viewAccount.do" class="br-blue"></i> View
									Account</a></li>
							<li><a href="buy.do" class="br-green"></i> Buy Fund </a></li>
							<li><a href="sellFundsList.do" class="br-orange"></i> Sell Fund </a></li>
							<li><a href="requestCheck.do" class="br-yellow"></i> Request
									Check </a></li>
							<li><a href="transactionHistory.do" class="br-purple"></i> Transaction
									History </a></li>
							<li><a href="researchFundCust.do" class="br-red"></i> Research Fund </a></li>
							<li><a href="logoutCustomer.do" class="br-blue"></i> Logout
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="login.do" class="br-purple"></i> Login </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

		</div>
		<!-- Sidebar ends -->
		<div class="mainbar">
			<td valign="top">
				<!-- JS --> <script src="<%=basePath%>js/jquery.js"></script> <script
					src="<%=basePath%>js/bootstrap.js"></script> <!-- Bootstrap --> <script
					src="<%=basePath%>js/filter.js"></script> <!-- Filter JS --> <script
					src="<%=basePath%>js/jquery.carouFredSel-6.1.0-packed.js"></script>
				<!-- CarouFredSel --> <script
					src="<%=basePath%>js/jquery.flexslider-min.js"></script> <!-- Flexslider -->
				<script src="<%=basePath%>js/jquery.isotope.js"></script> <!-- Isotope -->
				<script src="<%=basePath%>js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->
				<script src="<%=basePath%>js/jquery.tweet.js"></script> <!-- Tweet -->
				<script src="<%=basePath%>js/custom.js"></script> <!-- Main js file -->