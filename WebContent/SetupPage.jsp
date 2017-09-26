<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
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
			<li><a href="Router?sendTo=New_User" data-icon="grid"  data-transition="slide">New_User</a></li>
			<li><a href="Router?sendTo=Login" data-icon="star" class="ui-btn-active" data-transition="slide">Login</a></li>
			<li><a href="#" data-icon="gear">Setup</a></li>
			</ul>
        </div>
		</div>
		<div data-role="main">
			<p>     
			<form method="Post" action="Router">		 		   
     		      <label for="slider-fill">Volume:</label>
					<input type="range" name="slider-fill" id="slider-fill" value="60" min="0" max="100" step="10" data-highlight="true">
					<label for="slider2">Music:</label>
				<select name="slider2" id="slider2" data-role="slider">
					 <option value="on">On</option>
   					 <option value="off">Off</option>	
				</select>
				<input type="hidden" name="page" value ="Setup">
 				 <input type='submit' value='"submit"' />      	
   			</form>
      		</p>
      


			
			</div>
		<div data-role="footer" data-theme="a">
			<ex:Footer/>
		</div>
	</div>

</body>
</html>