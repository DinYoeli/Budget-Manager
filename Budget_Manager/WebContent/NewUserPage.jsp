
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"

    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login form using HTML5 and CSS3</title>
  
  
  
      <link rel="stylesheet" href="css/style.css">
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
  
</head>

<body>
  <body>
<div class="container">
	<section id="content">
		<form method="post" action="Router">
			<h1>Login Form</h1>
			<div>
				<input type="text" name="user" placeholder="Username" required="" id="username" />
			</div>
			<div>
				<input type="password" name="passw" placeholder="Password" required="" id="password" />
				  <input type="hidden" name="page" value ="NewUser">
			</div>
				<div>
				<input type="number" name="Budget" placeholder="Budget" required="" id="Budget_int" />
				
			</div>
<!-- Multiple Radios (inline) -->
			<div class="form-group">
  				<label class="col-md-4 control-label" for="radio-budget">Budget per:</label>
 					<div class="col-md-4"> 
   							 <label class="radio-inline" for="radio-choice-h-2a">
    			  <input type="radio" name="radio" id="radio-choice-h-2a" value="Day" checked="checked">
    							  Day
    					</label> 
    			<label class="radio-inline" for="radio-choice-h-2e">
    				  <input type="radio" name="radio" id="radio-choice-h-2e" value="Week">
     					 Week
   			 </label>
   			  <label class="radio-inline" for="radio-choice-h-2b">
    				  <input type="radio" name="radio" id="radio-choice-h-2b" value="Month">
     					 Month
   			 </label>
 				 </div>
			</div>

			<div>
				<input type="submit" value="Log in" />
				
				<a href="Router?sendTo=Login" >Log in</a>
			</div>
			<%
      			String Message =(String) request.getAttribute("Message");
      			if(Message!=null){
      				out.print("<h4>  ");
      				out.print(Message);
      				out.print("</h4>");
      			}
      			
      		%>
		</form><!-- form -->
		<div class="button">
				<ex:Footer/>
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->
</body>
  
    <script  src="js/index.js"></script>

</body>
</html>
