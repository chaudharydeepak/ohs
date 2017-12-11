<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS Home</title>
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
<link
	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' />"
	rel="stylesheet"></link>
<script
	src="<c:url value="http://code.jquery.com/jquery-2.0.3.min.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap.min.css' />"
	rel="stylesheet"></link>
<script
	src="<c:url value='http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js' />"></script>
<link href="<c:url value='/resources/css/bootstrap-editable.css' />"
	rel="stylesheet"></link>
<script src="<c:url value='/resources/js/bootstrap-editable.js' />"></script>
</head>

<body style="font-family: 'Open Sans', sans-serif; padding-top: 70px;">
	<%@include file="../header.jsp"%>
	<br>
<body>
	<div style="left:10%;">
	<span style="width: 40%;position: absolute;left:10%;color:gray;"><i class="fa fa-drivers-license-o" style="font-size:20px;color:green"></i><b>&nbsp;MetaData Maintainence Form</b></span>
	<br>
	<table class="table" align="center" style="width: 80%">
		<thead>
			<tr>
				<th>Select</th>
				<th>Meta Data Type</th>
				<th>Value</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${metaData}" var="mdtaobj">
				<tr>
					<td><input type="checkbox" class="mycheckboxes" id="checkBox~${mdtaobj.id}~${mdtaobj.type}"></td>
    					<td>${mdtaobj.type}</td>
					<td><a href="#" id="metas~${mdtaobj.id}~${mdtaobj.type}"
						data-type="text"
						data-url="/ehsworx/welcome/saveMetaData?${_csrf.parameterName}=${_csrf.token}"
						data-pk="2">${mdtaobj.value}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div style="width: 40%;position: absolute;left:10%;" >
			<button id="delete-btn" class="btn btn-primary">Remove Selected Line Items</button>
	</div>
	<br><br>
	<div style="width: 40%;position: absolute;left:10%;" >
		<div id="msg" class="alert hide"></div>
		<span style="color:gray;"><i class="fa fa-drivers-license-o" style="font-size:20px;color:green"></i><b>&nbsp;MetaData Creation Form</b></span>
		<table id="user" class="table table-bordered table-striped" align="center">
			<tbody>

				<tr>
					<td width="40%"><i class="fa fa-asterisk" style="font-size:8px;color:red"></i>&nbsp;New Value</td>
					<td><a href="#" class="myeditable" id="new_value"
						data-type="text" data-name="new_value"
						data-original-title="Enter Value"></a></td>
				</tr>
				<tr>
				    <td><i class="fa fa-asterisk" style="font-size:10px;color:red"></i>&nbsp;Select MetaData Type</td>
					  <td><a href="#" class="editable editable-click editable-empty" id="metadata" data-type="select" data-pk="1" data-value="location" data-title="Select Metadata Type"></a></td>
        </tr>
			</tbody>
		</table>
		<div>
			<button id="save-btn" class="btn btn-primary">Create new value!</button>
			<button id="reset-btn" class="btn pull-right">Reset</button>
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</body>
<script type="text/javascript">
$( document ).ready(function() {
	$.fn.editable.defaults.mode = 'inline';
	$.extend($.fn.editable.defaults, {
	    ajaxOptions: {
	        type: 'PUT',
	        dataType: 'script'
	    }
	});
	$.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="fa fa-fw fa-check"></i></button>' + '<button type="button" class="btn editable-cancel"><i class="fa fa-fw fa-remove"></i></button>' ;
	$("[id^=metas]").editable();

    //make value required
    $('#new_value').editable('option', 'validate', function(v) {
        if(!v) return '<label style="color:red;">Required field</label>';
        console.log( v );
    });

    //automatically show next editable
    $('.myeditable').on('save.newuser', function(){
        var that = this;
        setTimeout(function() {
            $(that).closest('tr').next().find('.myeditable').editable('show');
        }, 200);
    });

     //create new itemline
       $('#save-btn').click(function() {
				 $.ajax({
							 method: "POST",
					 url: "/ehsworx/welcome/createMetaData?value=" +$('#new_value').text() + "&type=" + $('#metadata').text() + "&${_csrf.parameterName}=${_csrf.token}",
					 success: function(result)
						 {
						 window.location.reload();;
			 }});
       });

       //reset
       $('#reset-btn').click(function() {
           $('.myeditable').editable('setValue', null)
                           .editable('option', 'pk', null)
                           .removeClass('editable-unsaved');

           $('#save-btn').show();
           $('#msg').hide();
   });

    //delete item line
         $('#delete-btn').click(function() {
         var checkBoxes = $('.mycheckboxes:checked').map(function() {
    			return this.id.replace("checkBox~", "");
			}).get();
         console.log( checkBoxes );
          $.ajax({
            method: "POST",
          	url: "/ehsworx/welcome/deleteMetaData?metadata=" +checkBoxes.toString()+ "&${_csrf.parameterName}=${_csrf.token}",
          	success: function(result)
          		{
	         		window.location.reload();;
    		}});
   	});
});
$(function(){
 $("[id^=metadata]").editable({
        source: [
            {value: 'location', text: 'location'},
            {value: 'department', text: 'department'},
            {value: 'who_observed', text: 'who_observed'},
            {value: 'type_of_observation', text: 'type_of_observation'},
            {value: 'classification', text: 'classification'},
            {value: 'areas', text: 'areas'}
        ],
        display: function(value, sourceData) {
             var colors = {"": "gray", 1: "green", 2: "blue"},
                 elem = $.grep(sourceData, function(o){return o.value == value;});

             if(elem.length) {
                 $(this).text(elem[0].text).css("color", colors[value]);
             } else {
                 $(this).empty();
             }
        }
    });
});
</script>
<script type="text/javascript">
$( document ).ready(function() {
	$('#mm').addClass('active');
});
</script>
</html>