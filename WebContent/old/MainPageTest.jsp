
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"

    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<%
Cookie cookie = null;
Cookie[] cookies = null;
cookies = request.getCookies();
String username ="Username";

// Checks the name of the customer by cookies
if(cookies != null){
	for (int i = 0; i < cookies.length; i++) {
   	 cookie = cookies[i];
   	 if (cookie.getName().equals("username")) {
    		username = cookie.getValue();
    	}
	}
}

%>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

	<div data-role="page" id="home"   class="ui-body-a">
		<div data-role="header" style="overflow:hidden;">		
    		<div data-role="navbar">
            <ul>
			<li><a href="Router?sendTo=New_User" data-icon="grid" data-transition="slide">New_User</a></li>
			<li><a href="#" data-icon="star" class="ui-btn-active">Login</a></li>
			<li><a href="Router?sendTo=Setup" data-icon="gear" data-transition="slide">Setup</a></li>
			</ul>
        </div>
		</div>
		<div data-role="main">
			<p>     
			<form method="post" action="Router">
				<div>
     		     <h3>Login information</h3>
      		    <label for="usrnm" class="ui-hidden-accessible" >Username:</label>
      		    <input type="text" name="user" id="usrnm" placeholder=<%= username %>>
     	        <label for="pswd" class="ui-hidden-accessible">Password:</label>
       		    <input type="password" name="passw" id="pswd" placeholder="Password">
       		    <input type="hidden" name="page" value ="Login">
		    	<button type="submit" id="submit-6">Log in</button>
        		</div>
      </form></p>
      
			<%
      			String Message =(String) request.getAttribute("Message");
      			if(Message!=null){
      				out.print("<h4>  ");
      				out.print(Message);
      				out.print("</h4>");
      			}
      			
      		%>
		
			<div data-role="collapsible" class="ui-nodisc-icon ui-icon-action" data-mini="true" >
 			 <h3>About</h3>
 				 <p>This is an app that manages your budget <br>
					You can set your budget by day, week, month  <br>
					And track your expenses and manage them.
					</p>
				</div>
			
			</div>
		<div data-role="footer" data-theme="a">
			<ex:Footer/>
		</div>
	</div>

</body>
</html>