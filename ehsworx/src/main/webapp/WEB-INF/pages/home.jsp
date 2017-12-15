<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon"/>
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon"/>
<title>EHS Home</title>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>
<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
</head>

<body style="font-family:Arial,Verdana,sans-serif;padding-top: 70px;">	
<%@include file="header.jsp" %>
<table class="table table-bordered table-striped" align="center" style="width:98%;font-family:Arial,Verdana,sans-serif;font-size:13px;">
		<thead>
			<tr>
				<th>Observation</th>
				<th>Reference</th>
				<th>Project</th>
				<th>Location</th>
				<th>Area</th>
				<th>Observation</th>
				<th>Who Observed</th>
				<th>Initiated By</th>
				<th>Proposed Action</th>
				<th>Responsible Manager</th>
				<th>Status</th>
				<th>Closed Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${observationList}" var="obs">
			<c:set var="respMgrParts" value="${fn:split(obs.responsibleManager, '(')}" />
				<tr>
					<td>${obs.obsID}</td>
					<td>${obs.obsRef}</td>
					<td>${obs.project}</td>
					<td>${obs.locations}</td>
					<td>${obs.areas}</td>
					<td>${fn:substring(obs.obsTxt, 0, 10)}...</td>
					<td>${obs.whobsvd}</td>
					<td>${obs.initrFname} ${obs.initrLname}</td>
					<td>${fn:substring(obs.actionsList[0].actionTxt, 0, 10)}...</td>
					<td>${respMgrParts[0]}</td>
					<td>${obs.status}</td>
					<td>
					<c:choose>
					<c:when test="${obs.status == 'Completed'}">
						${obs.last_mdfd_date}
					</c:when>
					<c:otherwise>
						_
					</c:otherwise>
					</c:choose>
					</td>
					<td><a href="<c:url value="/welcome/admin/loadObs?name="/>${obs.obsID}" data-toggle="ed_et" title="Edit">
						<i class="fa fa-edit" style="color:red"></i></a>&nbsp;
						<a id="deleteObs_${obs.obsID}" href="#" data-toggle="ed_dt" title="Delete"><i class="fa fa-remove" style="color:red"></i></a>&nbsp;
						<a href="<c:url value="/welcome/action?name="/>${obs.obsID}" data-toggle="ed_at" title="Action">
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
	$('[data-toggle="ed_et"]').tooltip(); 
	$('[data-toggle="ed_dt"]').tooltip(); 
	$('[data-toggle="ed_at"]').tooltip(); 
});
</script>
</html>