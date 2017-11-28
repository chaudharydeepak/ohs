<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHS Login</title>
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
</head>
<body>
	<form style="margin: 0px 0px 0px 0px;" name="loginForm"
						action="j_spring_security_check" method="POST">
		<div class="login">
			<c:if test="${not empty error}">
												<div class="error">${error}</div>
											</c:if>
											<c:if test="${not empty msg}">
												<div class="msg">${msg}</div>
											</c:if>
			<h1>Login to Web Application - ${appName}</h1>
			<input type="text" placeholder="Username" id="username" name="username">  
  			<input type="password" placeholder="password" id="password" name="password">
			<input name="submit" type="submit" value="submit" />
		</div>
		<div class="shadow"></div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>