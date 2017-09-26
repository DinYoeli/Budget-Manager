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
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}
</style>


</head>
<body>




	<div data-role="page" id="bars"   class="ui-body-a">
	
	 <div data-role="panel"  data-display="push" id="Options"> 
			<h1> Options</h1>

		<ul data-role="listview">
  		  <li><a href="Router?sendTo=Main">Main</a></li>
  		  <li><a href="Router?sendTo=Transaction">Add Transaction</a></li>
   		  <li><a href="Router?sendTo=ChangeBudget">Change budget</a></li>
  		  <li><a href="Router?sendTo=AddType">Add Type</a></li>
  		  <li><a href="Router?sendTo=chart">Chart</a></li>
  		  <li><a href="Router?sendTo=LogOut">Log Out</a></li>
		</ul>

		</div>
	
	
	
	<div data-role="header">
		<a href=#Options data-icon="bars" class="ui-btn-left" >Options</a>
		<span class="ui-title" />
	</div>
  <div data-role="main" class="ui-content">
  				<h1>Change Budget</h1>
	     		 <p><form method="Post" action="Router">
     			   <div>
      			    
      			    <label for="budget" >Budget:</label>
      			    <input  type="number" name="budget" id="bug" placeholder="100"   data-mini="true">
					<input type="hidden" name="page" value ="ChangeBudget">
       			<button type="submit" id="submit-6" >Submit</button>
				
       			   
       			 </div>
      		</form></p>
	</div>
<div data-role="footer"  data-theme="a">
			<ex:Footer/>
		</div>
	</div>
</div>



</body>

</html>