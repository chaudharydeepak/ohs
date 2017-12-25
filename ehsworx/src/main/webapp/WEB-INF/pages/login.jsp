<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Login page</title>
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="alert alert-warning" style="margin: 0 auto;width:850px;text-align:center;">
		<b>****** This software is in beta version. For demo purposes only.*****</b>
	</div>
	<div class="wrapper">
		<form action="j_spring_security_check" method="post" class="form-signin">
			<p style="text-align:center"><img style="text-align:center;width: auto; height: auto;max-width: 100%;max-height: 100%>" src="<c:url value='/resources/img/logo-cover.png'/>"></img></p>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<div class="input-group margin-bottom-sm">
	  		<span class="input-group-addon"><i class="fa fa-envelope-o fa-fw" style="color:green"></i></span>
				<input type="text" class="form-control" name="username" placeholder="Email Address" />
			</div>
			<div class="input-group" style="padding-bottom:15px;">
	  		<span class="input-group-addon"><i class="fa fa-key fa-fw" style="color:green"></i></span>
				<input type="password" class="form-control" name="password" placeholder="Password" />
			</div>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="btn btn-block" type="submit" style="background-color:green;color:white;">Login</button>
			<br>
			<a href="/ehsworx/welcome/resetPasswordLink" style="font-size:12px;color:green">Forgot Password?<i class="fa fa-key fa-fw" style="color:green"></i></a>
		</form>
	</div>
</body>
</html>
