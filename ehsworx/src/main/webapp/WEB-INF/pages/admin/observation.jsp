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
<script src="<c:url value="/resources/js/validator.min.js" />"></script>
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

<body style="font-family: 'Open Sans', sans-serif;padding-top: 70px;">
<%@include file="../header.jsp" %>
<br>
    <form:form id="observationForm" method="post" modelAttribute="observation" enctype="multipart/form-data" action="/ehsworx/welcome/admin/createobs?${_csrf.parameterName}=${_csrf.token}" style="width: 100%" data-toggle="validator">
           <div class="container">
             <div class="form-group row"> <!-- Date input -->
                    <label class="col-sm-2 control-label" for="date">Date&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                    <div class="col-sm-10">
                        <form:input path="date" class="form-control" id="date" placeholder="mm/dd/yyyy" type="text"  data-error="Please select a date." required="true"/>
                        <div class="help-block with-errors"></div>
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
                <label for="inputwho" class="col-sm-2 col-form-label">Who Observed&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                    <form:select path="whobsvd" items="${obeservertypeList}" multiple="false" id="whoObserved" class="form-control" data-error="Please select this value." required="true"/>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputobeh" class="col-sm-2 col-form-label">Entered On Behalf Of</label>
                <div class="col-sm-10">
                    <form:input path="obsBehf" type="text" class="form-control" id="inputobeh" placeholder="Enter On Behalf Of"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputobeh" class="col-sm-2 col-form-label">Project</label>
                <div class="col-sm-10">
                    <form:input path="project" type="text" class="form-control" id="projectid" placeholder="Project"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputinfo" class="col-sm-2 col-form-label">Company/Contact Info</label>
                <div class="col-sm-10">
                    <form:input path="obsContctInfo" type="text" class="form-control" id="inputinfo" placeholder="Enter Comp/Contact Info"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputobserv" class="col-sm-2 col-form-label">Your Observation&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                		<form:textarea path="obsTxt" class="form-control" id="inputobserv" name="inputobserv" rows="3" data-error="Please enter your observations." required="true"/>
                    	<div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputcard" class="col-sm-2 col-form-label">Type of Observation&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                    <form:select path="shoc" items="${shocList}" multiple="false" name="paperShoc" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputclas" class="col-sm-2 col-form-label">
                    Classification&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                   <form:select path="classification" items="${classificationList}" multiple="false" name="classifier" class="form-control"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Proposed Action</label>
                <div class="col-sm-10">
                    <form:textarea path="propsdAction" class="form-control" id="inputAct" rows="3"/>
                </div>    
            </div>
            <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Responsible Manager&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                    <form:select path="responsibleManager" items="${respManagerList}" multiple="false" name="manager" class="form-control"/>
                </div>    
            </div>
             <!--  <div class="form-group row">
                <label for="inputAct" class="col-sm-2 col-form-label">Initiated By&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                    <form:select path="initiatedBy" items="${respManagerList}" multiple="false" name="initiater" class="form-control"/>
                </div>    
            </div>-->
            <div class="form-group row">
                <label for="initiater" class="col-sm-2 col-form-label">Initiated By&nbsp;<i class="fa fa-asterisk" style="font-size:10px;color:red"></i></label>
                <div class="col-sm-10">
                	<form:label class="form-control" path="initiatedBy">${initatedByUser}</form:label>
                </div>    
            </div>
            <div class="form-group row">
                    <label class="col-sm-2 col-form-label" for="file">Upload Attachements</label>
                    <div class="col-sm-10">
                        <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                    </div>
                </div>
          
            
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <form:input type="hidden" path="obsID"/>
            
            <div class="form-group row">
                <div class="col-sm-10">
                		<button type="submit" class="btn btn-primary">Create Observation</button>
                  </div>
            </div>
            </div>
       </form:form> 
</body>
<script type="text/javascript">
$( document ).ready(function() {
	$('#co').addClass('active');
});
</script>
</html>