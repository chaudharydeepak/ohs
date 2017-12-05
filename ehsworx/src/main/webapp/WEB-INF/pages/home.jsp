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
<table class="table table-bordered table-striped" align="center" style="width:90%">
		<thead>
			<tr>
				<th>Observation ID</th>
				<th>Type of Observation</th>
				<th>Initiated By</th>
				<th>Responsible Manager</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${observationList}" var="obs">
				<tr>
					<td>${obs.obsID}</td>
					<td>${obs.shoc}</td>
					<td>${obs.initrFname} ${obs.initrLname}</td>
					<td>${obs.responsibleManager}</td>
					<td>${obs.status}</td>
					<td><a href="<c:url value="/welcome/admin/loadObs?name="/>${obs.obsID}">
						<i class="fa fa-edit" style="color:red"></i></a>
						<a id="deleteObs_${obs.obsID}" href="#"><i class="fa fa-remove" style="color:red"></i></a>
						<a href="<c:url value="/welcome/action?name="/>${obs.obsID}">
						<i class="fa fa-cogs" style="color:red"></i></a>
						</td> 
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script type="text/javascript">
$( document ).ready(function() {
	$('#mo').addClass('active');
	$("[id^=deleteObs_]").click(function(){
	 $.ajax({
		method: "POST",
		url: "/ehsworx/welcome/deleteObs?obsId=" + $(this).attr("id") + "&${_csrf.parameterName}=${_csrf.token}",
		success: function(result)
		{
			window.location.reload();;
		},
		failure: function(result)
		{
			alert(result);
		}
		});
	});
});
</script>
</html>