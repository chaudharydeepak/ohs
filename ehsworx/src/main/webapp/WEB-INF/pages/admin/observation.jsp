<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Observation</title>
<link href="<c:url value='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css' />" rel="stylesheet"></link>
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
	<div class="container">
		<form style="width: 90%">
			<div class="form-group row"> <!-- Date input -->
        			<label class="col-sm-2 control-label" for="date">Date</label>
        			<div class="col-sm-10">
        				<input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
        			</div>
      		</div>
			<div class="form-group row">
				<label for="inputobref" class="col-sm-2 col-form-label">Observation
					Reference</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputobref"
						placeholder="Enter Ref">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputst" class="col-sm-2 col-form-label">Site(Office)</label>
				<div class="col-sm-10">
					<select class="form-control" id="inputst">
						<option>Default select</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputvess" class="col-sm-2 col-form-label">Vessel</label>
				<div class="col-sm-10">
					<select class="form-control" id="inputvess">
						<option>Default select</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputwho" class="col-sm-2 col-form-label">Who
					Observed</label>
				<div class="col-sm-10">
					<select class="form-control" id="inputwho">
						<option>Default select</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputobeh" class="col-sm-2 col-form-label">Entered
					On Behalf Of</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputobeh"
						placeholder="Enter Behalf">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputinfo" class="col-sm-2 col-form-label">Company/Contact
					Info</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputinfo"
						placeholder="Enter Comp">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputobserv" class="col-sm-2 col-form-label">Your Observation</label>
				<div class="col-sm-10">
				<textarea class="form-control" id="inputobserv" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputcard" class="col-sm-2 col-form-label">Paper
					SHOC Card</label>
				<div class="col-sm-10">
					<select class="form-control" id="inputcard">
						<option>Default select</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputclas" class="col-sm-2 col-form-label">Observation
					Classification</label>
				<div class="col-sm-10">
					<select class="form-control" id="inputclas">
						<option>Default select</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputAct" class="col-sm-2 col-form-label">Your Proposed Action</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="inputAct" rows="3"></textarea>
				</div>	
			</div>
			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Create Observation</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>