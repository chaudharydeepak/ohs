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
<script>
function validateEmail(email) 
	{
	console.log(email);
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  	return re.test(email);
	}
</script>
</head>

<body style="font-family: 'Open Sans', sans-serif; padding-top: 70px;">
	<%@include file="../header.jsp"%>
	<br>
<body>
	<div style="left:10%;">
	<span style="width: 40%;position: absolute;left:10%;color:gray;"><i class="fa fa-drivers-license-o" style="font-size:20px;color:green"></i><b>&nbsp;User Maintainence Form</b></span>
	<br>
	<table class="table" align="center" style="width: 80%">
		<thead>
			<tr>
				<th>Select</th>
				<th>User Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Assigned Role</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${userList}" var="user">
				<tr>
					<td><input type="checkbox" class="mycheckboxes" id="checkBox_${user.userName}"></td>
    					<!--  <td><a href="#" id="username_${user.userName}" data-type="text" data-url="/ehsworx/welcome/saveUser?type=uname&${_csrf.parameterName}=${_csrf.token}" data-pk="1" data-title="Enter Username">${user.userName}</a></td> -->
					<td>${user.userName}</td>
					<td><a href="#" id="userFname_${user.userName}"
						data-type="text"
						data-url="/ehsworx/welcome/saveUser?type=fname&${_csrf.parameterName}=${_csrf.token}"
						data-pk="2" data-title="Enter First Name">${user.userfName}</a></td>
					<td><a href="#" id="userLname_${user.userName}"
						data-type="text"
						data-url="/ehsworx/welcome/saveUser?type=lname&${_csrf.parameterName}=${_csrf.token}"
						data-pk="3" data-title="Enter Last Name">${user.userlName}</a></td>
					<td><a href="#" id="userroles_${user.userName}"
						data-type="select"
						data-url="/ehsworx/welcome/saveUser?type=role&${_csrf.parameterName}=${_csrf.token}"
						data-pk="3" data-value="${user.userRole}"></a></td>
					<td><a href="#" id="userPswd_${user.userName}"
						data-type="password"
						data-url="/ehsworx/welcome/saveUser?type=pswd&${_csrf.parameterName}=${_csrf.token}"
						data-pk="3" data-title="Enter Last Name">*********</a></td>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div style="width: 40%;position: absolute;left:10%;" >
			<button id="delete-btn" class="btn btn-primary">Remove Selected User Accounts</button>
	</div>
	<br><br>
	<div style="width: 40%;position: absolute;left:10%;" >
		<div id="msg" class="alert hide"></div>
		<span style="color:gray;"><i class="fa fa-drivers-license-o" style="font-size:20px;color:green"></i><b>&nbsp;New User Creation Form</b></span>
		<table id="user" class="table table-bordered table-striped" align="center">
			<tbody>
				<tr>
					<td width="40%"><i class="fa fa-asterisk" style="font-size:8px;color:red"></i>&nbsp;Username</td>
					<td><a href="#" class="myeditable" id="new_username"
						data-type="text" data-name="username"
						data-original-title="Enter username(email address)"></a></td>
				</tr>
				<tr>
					<td><i class="fa fa-asterisk" style="font-size:8px;color:red"></i>&nbsp;First name</td>
					<td><a href="#" id="firstname" class="myeditable" data-type="text"
						data-name="firstname" data-original-title="Enter First Name"></a></td>
				</tr>
				<tr>
					<td><i class="fa fa-asterisk" style="font-size:8px;color:red"></i>&nbsp;Last name</td>
					<td><a href="#" id="lastname" class="myeditable" data-type="text"
						data-name="lastname" data-original-title="Enter Last Name"></a></td>
				</tr>
				<tr>
					<td><i class="fa fa-asterisk" style="font-size:8px;color:red"></i>&nbsp;Password</td>
					<td><a href="#" id="password" class="myeditable" data-type="password"
						data-name="password" data-original-title="Enter Password"></a></td>
				</tr>
				<tr>         
				    <td><i class="fa fa-asterisk" style="font-size:10px;color:red"></i>&nbsp;Select Role</td>
					<td><a href="#" class="editable editable-click editable-empty" id="userroles_new" data-type="select" data-pk="1" data-value="ROLE_NORMAL" data-title="Select Role"></a></td>
                </tr>
			</tbody>
		</table>
		<div>
			<button id="save-btn" class="btn btn-primary">Create new user!</button>
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
	//$("[id^=username]").editable(); [ lets not make username field editable ]
	$("[id^=userFname]").editable();
	$("[id^=userLname]").editable();
	$("[id^=userPswd_]").editable();
    
	//init editables - user creation 
    $('.myeditable').editable({
        url: '/ehsworx/welcome/newUser' //this url will not be used for creating new user, it is only for update
    });
     
    //make username required
    $('#new_username').editable('option', 'validate', function(v) {
        if(!v) return '<label style="color:red;">Required field</label>';
        console.log( v );
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  	if(!re.test(v)) return 'should be a valid email address.';
    });

   //make firstname required
    $('#firstname').editable('option', 'validate', function(v) {
        if(!v) return '<label style="color:red;">Required field!</label>';
    });
    
    //make lastname required
    $('#lastname').editable('option', 'validate', function(v) {
        if(!v) return '<label style="color:red;">Required field!</label>';
    });
    
    //make password required
    $('#password').editable('option', 'validate', function(v) {
        if(!v) return '<label style="color:red;">Required field!</label>';
    });
     
    //automatically show next editable
    $('.myeditable').on('save.newuser', function(){
        var that = this;
        setTimeout(function() {
            $(that).closest('tr').next().find('.myeditable').editable('show');
        }, 200);
    });
    
     //create new user
       $('#save-btn').click(function() {
       	   userRole=$('#userroles_new').text();
           $('.myeditable').editable('submit', { 
               url: '/ehsworx/welcome/createUser?role=' + userRole + '&${_csrf.parameterName}=${_csrf.token}', 
               ajaxOptions: {
                   type: 'POST',
       	        dataType: 'script' //assuming json response
               },           
               success: function(data, config) {
            	   var msg = 'New user created! Now editables submit individually.';  
            	   window.location.reload();
                   if(data && data.id) {  //record created, response like {"id": 2}
                       //set pk
                       $(this).editable('option', 'pk', data.id);
                       //remove unsaved class
                       $(this).removeClass('editable-unsaved');
                       //show messages
                       var msg = 'New user created! Now editables submit individually.';
                       $('#msg').addClass('alert-success').removeClass('alert-error').html(msg).show();
                       $('#save-btn').hide(); 
                       $(this).off('save.newuser');                   
                   } else if(data && data.errors){ 
                       //server-side validation error, response like {"errors": {"username": "username already exist"} }
                       config.error.call(this, data.errors);
                   }               
               },
               error: function(errors) {
                   var msg = '';
                   if(errors && errors.responseText) { //ajax error, errors = xhr object
                       msg = errors.responseText;
                   } else { //validation error (client-side or server-side)
                       $.each(errors, function(k, v) { msg += k+": "+v+"<br>"; });
                   } 
                   $('#msg').removeClass('alert-success').addClass('alert-error').html(msg).show();
               }
           });
       }); 
       
       //reset
       $('#reset-btn').click(function() {
           $('.myeditable').editable('setValue', null)
                           .editable('option', 'pk', null)
                           .removeClass('editable-unsaved');
                           
           $('#save-btn').show();
           $('#msg').hide();                
   });
   
    //deleteUser
         $('#delete-btn').click(function() {
         var checkBoxes = $('.mycheckboxes:checked').map(function() {
    			return this.id.replace("checkBox_", "");
			}).get();
         console.log( checkBoxes );
          $.ajax({
                method: "POST",
          	url: "/ehsworx/welcome/deleteUser?users=" +checkBoxes.toString()+ "&${_csrf.parameterName}=${_csrf.token}", 
          	success: function(result)
          		{
	         		window.location.reload();;
    		}});
   	});
});
$(function(){
 $("[id^=userroles]").editable({
        source: [
            {value: 'ROLE_NORMAL', text: 'ROLE_NORMAL'},
            {value: 'ROLE_ADMIN', text: 'ROLE_ADMIN'}
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
	$('#mu').addClass('active');
});
</script>
</html>