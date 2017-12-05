<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<nav class="fixed-top navbar navbar-toggleable-md navbar-inverse bg-inverse">
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        		<span class="navbar-toggler-icon"></span>
      	</button>
      	<img src="<c:url value='/resources/img/OHS-Icons-01.png' />"></img>&nbsp;
      	<a class="navbar-brand" href="#" style="color:#2ECC71;">EHS</a>
    
      	<div class="collapse navbar-collapse" id="navbarSupportedContent">
        		<ul class="navbar-nav mr-auto">
          		<li class="nav-item" id="co" name="co">
                  	<a class="nav-link" href="/ehsworx/welcome/admin">Create Observation<span class="sr-only">(current)</span></a>
          		</li>
          		<li class="nav-item" id="mo" name="mo">
        				<a class="nav-link" href="/ehsworx/welcome">Manage Observation<span class="sr-only">(current)</span></a>
          		</li>
          		<li class="nav-item" id="mm" name="mm">
                  	<a class="nav-link" href="/ehsworx/welcome/admin/managmtdt">Manage Metadata<span class="sr-only">(current)</span></a>
          		</li>
          		<li class="nav-item" id="mu" name="mu">
                  	<a class="nav-link" href="/ehsworx/welcome/admin/manageuser">Manage Users<span class="sr-only">(current)</span></a>
          		</li>
        		</ul>
        		<label style="color:red;font-size:10px;">Welcome ${pageContext.request.userPrincipal.name}</label>
        		<c:url var="logoutUrl" value="/j_spring_security_logout" />
   			<form action="${logoutUrl}" id="logout" method="post">
        			<input type="hidden" name="${_csrf.parameterName}"
            			value="${_csrf.token}" />
    			</form>
    			<a href="#" style="color:gray;font-size:10px;" onclick="document.getElementById('logout').submit();">&nbsp;|&nbsp;Logout</a>
      </div>
</nav>