<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Observation</title>
<link href="<c:url value='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet"></link>
<script src="<c:url value="https://code.jquery.com/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js" />"></script>
<link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css' />" rel="stylesheet"></link>


<script>
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>
</head>

<body style="font-family: 'Open Sans', sans-serif;">
     <c:url value="/j_spring_security_logout" var="logoutUrl" />
    	<nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a class="navbar-brand" href="#" style="color:yellow;">EHS</a>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
                  <a class="nav-link" href="#">Create Observation<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
        <a class="nav-link" href="/ehsworx/welcome/">Manage Observation<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
                  <a class="nav-link" href="#">Manage Metadata<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
                  <a class="nav-link" href="#">Manage Users<span class="sr-only">(current)</span></a>
          </li>
        </ul>
        <label style="color:gray;font-size:10px;">Welcome ${pageContext.request.userPrincipal.name}</label>
      </div>
</nav>
<br>
    
        <form:form method="post" modelAttribute="observation" enctype="multipart/form-data" action="admin/createobs?${_csrf.parameterName}=${_csrf.token}" style="width: 100%">
           <div class="container">
            <div class="form-group row"> <!-- Date input -->
                    <label class="col-sm-2 control-label" for="date">Date</label>
                    <div class="col-sm-10">
                        <form:input path="date" value="${observation.date}" class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
                    </div>
              </div>
            <div class="form-group row">
                <label for="inputobref" class="col-sm-2 col-form-label">
                    Reference</label>
                <div class="col-sm-10">
                    <form:input path="obsRef" type="text" class="form-control" id="inputobref" placeholder="Enter Ref" />
                </div>
            </div>
            <div class="form-group row">
                <label for="inputst" class="col-sm-2 col-form-label">Location</label>
				<div class="col-sm-10">
					<form:select path="locations" items="${locationList}" multiple="false" class="form-control"/>
				</div>
			</div>
            <div class="form-group row">
                <label for="inputvess" class="col-sm-2 col-form-label">Department</label>
                <div class="col-sm-10">
                    <form:select path="departments" items="${departmentList}" multiple="false" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputwho" class="col-sm-2 col-form-label">Who Observed</label>
                <div class="col-sm-10">
                    <form:select path="whobsvd" items="${obeservertypeList}" multiple="false" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputobeh" class="col-sm-2 col-form-label">Entered On Behalf Of</label>
                <div class="col-sm-10">
                    <form:input path="obsBehf" type="text" class="form-control" id="inputobeh" placeholder="Enter On Behalf Of"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputinfo" class="col-sm-2 col-form-label">Company/Contact Info</label>
                <div class="col-sm-10">
                    <form:input path="obsContctInfo" type="text" class="form-control" id="inputinfo" placeholder="Enter Comp/Contact Info"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputobserv" class="col-sm-2 col-form-label">Your Observation</label>
                <div class="col-sm-10">
                <form:textarea path="obsTxt" class="form-control" id="inputobserv" rows="3"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputcard" class="col-sm-2 col-form-label">Paper
                    SHOC Card</label>
                <div class="col-sm-10">
                    <form:select path="shoc" items="${shocList}" multiple="false" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputclas" class="col-sm-2 col-form-label">
                    Classification</label>
                <div class="col-sm-10">
                   <form:select path="classification" items="${classificationList}" multiple="false" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action 1</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action 2</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction_2" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action 3</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction_3" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action 4</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction_4" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action 5</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction_5" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Responsible Manager</label>
                <div class="col-sm-10">
                    <form:select path="responsibleManager" items="${respManagerList}" multiple="false" class="form-control"/>
                </div>    
            </div>
            <div class="form-group row">
                    <label class="col-sm-2 col-form-label" for="file">Upload Attachements</label>
                    <div class="col-sm-10">
                        <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                    </div>
                </div>
          
            
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            
            <div class="form-group row">
                <div class="col-sm-10">
                <c:choose>
                <c:when test="${observation.operationType} == 'new'">
                		<button type="submit" class="btn btn-primary">Create Observation</button>
                	</c:when>	
                	<c:otherwise>
    					<button type="submit" class="btn btn-primary">Edit Observation</button>
  				</c:otherwise>
                 </c:choose>
                </div>
            </div>
            </div>
       </form:form> 
</body>
</html>