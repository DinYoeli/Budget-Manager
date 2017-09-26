
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
			<li><a href="#" data-icon="grid">New_User</a></li>
			<li><a href="Router?sendTo=Login" data-icon="star" class="ui-btn-active" data-transition="slide">Login</a></li>
			<li><a href="Router?sendTo=Setup" data-icon="gear" data-transition="slide">Setup</a></li>
			</ul>
        </div>
		</div>
		<div data-role="main">
			<p>     
			 <form method="post" action="Router">
     			   <div>
      			    <h3>New User information</h3>
      			    <label for="usrnm" class="ui-hidden-accessible">Username:</label>
      			    <input type="text" name="user" id="usrnm" placeholder="Username">
       			   <label for="pswd" class="ui-hidden-accessible">Password:</label>
       			   <input type="password" name="passw" id="pswd" placeholder="Password">
					<fieldset data-role="controlgroup" data-type="horizontal">
    			    <legend>Budget per:</legend>
       					 <input type="radio" name="radio" id="radio-choice-h-2a" value="Day" checked="checked">
    				     <label for="radio-choice-h-2a">Day</label>
       					 <input type="radio" name="radio" id="radio-choice-h-2e" value="Week" checked="checked">
    				     <label for="radio-choice-h-2e">Week</label>
    				     <input type="radio" name="radio" id="radio-choice-h-2b" value="Month">
     				     <label for="radio-choice-h-2b">Month</label>

 				   </fieldset>
 					<input type="hidden" name="page" value ="NewUser">
					<legend>Budget:</legend>
    			    <input type="number" name="Budget" id="Budget_int" placeholder="100">
       			<button type="submit" id="submit-6" >Submit</button>
				
       			   
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
      


			
			</div>
		<div data-role="footer" data-theme="a">
			<ex:Footer/>
		</div>
	</div>

</body>
</html>