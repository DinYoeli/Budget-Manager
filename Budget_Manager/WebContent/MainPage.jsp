
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"

    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=windows-1255">

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
			<h1>Login Form </h1>
			<div>
				<input type="text" name="user" placeholder=<%= username %>  required="" id="username" />
			</div>
			<div>
				<input type="password" name="passw" placeholder="Password" required="" id="password" />
				  <input type="hidden" name="page" value ="Login">
			</div>
			<div>
				<input type="submit" value="Log in" />
				
				<a href="Router?sendTo=New_User" >Register</a>
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
