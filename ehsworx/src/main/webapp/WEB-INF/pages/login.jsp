<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Login page</title>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="wrapper">
		
		<form action="j_spring_security_check" method="post" class="form-signin">
			<p style="text-align:center"><img style="text-align:center;" src="<c:url value='/resources/img/logo.gif' />"></img></p>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<input type="text" class="form-control" name="username"
				placeholder="Email Address" /> 
			<input type="password" class="form-control" name="password"
				placeholder="Password" />
			<br> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color:green;">Login</button>
		</form>
	</div>
</body>
</html>