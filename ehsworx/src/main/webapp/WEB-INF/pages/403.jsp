<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon"/>
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon"/>
<title>EHS Home</title>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' />" rel="stylesheet"></link>
<script src="<c:url value="https://code.jquery.com/jquery-1.11.3.min.js" />"></script>
</head>

<body style="font-family: 'Open Sans', sans-serif;padding-top: 70px;">	
<%@include file="header.jsp" %>
${msg}
</body>