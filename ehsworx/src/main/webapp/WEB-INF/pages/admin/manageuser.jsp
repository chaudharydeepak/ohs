<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Manage User</title>
<link
	href="<c:url value='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet"></link>
<body>
	<table class="table table-stripedß" align="center">
		<thead>
			<tr>
				<th>UserName</th>
				<th>userfName</th>
				<th>userlName</th>
				<th>userRole</th>
				<th>userEmail</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.userName}</td>
					<td>${user.userfName}</td>
					<td>${user.userlName}</td>
					<td>${user.userRole}</td>
					<td>${user.userEmail}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>