<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title> Spring MVC Exception </title>
</head>
	<body>
	<h1>Error :  ${exc.message}</h1>
	<c:forEach items="${exc.stackTrace}" var="st">
	    ${st} 
        </c:forEach>
	</body>
</html>