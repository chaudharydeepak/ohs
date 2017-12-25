<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Password Reset page</title>
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
<script src="<c:url value="/resources/js/validator.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="alert alert-warning" style="margin: 0 auto;width:850px;text-align:center;">
		<b>****** This software is in beta version. For demo purposes only.*****</b>
	</div>
	<div class="wrapper">
		<form action="/ehsworx/welcome/resetPswd" method="post" class="form-signin" name="form" data-toggle="validator" role="form">
			<p style="text-align:center"><img style="text-align:center;width: auto; height: auto;max-width: 100%;max-height: 100%>" src="<c:url value='/resources/img/logo-cover.png'/>"></img></p>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			
			<div class="alert alert-success">Enter your new password below:</div>
			
			<div class="form-group" style="padding-bottom:10px;">
	  			<input type="password" class="form-control" id="inputPassword" placeholder="Password" data-trimpswd="trimpswd" data-minlength="6" required/>
	  			<div class="help-block" style="font-size:12px;color:gray;">Minimum of 6 characters</div>
	  			<div class="help-block with-errors" style="font-size:12px;"></div>
			</div>
			<div class="form-group" style="padding-bottom:10px;">
	  			<input type="password" class="form-control" name="inputPasswordConfirm" id="inputPasswordConfirm" data-match="#inputPassword" 
				data-match-error="Passwords don't match." placeholder="Confirm" required/>
				<div class="help-block with-errors" style="font-size:12px;"></div>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="btn btn-block" type="submit" style="background-color:green;color:white;">Change Password</button>
		</form>
	</div>
</body>
<script>
$('form[data-toggle="validator"]').validator({
	  custom: {
		  trimpswd: function($el) {
	    		var paswdLen = $el.val().trim().length;
            console.log("i  m invoked : " + paswdLen);
            if ( paswdLen == 0 )
            return "Invalid Password Value."
	      }
	    }
	});
</script>
</html>
