<%@page import="com.hit.objects.User"%>
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
  		  <li><a href="Router?sendTo=Main" data-ajax="false">Main</a></li>
  		  <li><a href="Router?sendTo=Transaction" data-ajax="false">Add Transaction</a></li>
   		  <li><a href="Router?sendTo=ChangeBudget" data-ajax="false">Update User Details</a></li>
  		  <li><a href="Router?sendTo=AddType" data-ajax="false">Update Categories</a></li>
  		  <li><a href="Router?sendTo=chart" data-ajax="false">Chart</a></li>
  		  <li><a href="Router?sendTo=chageItem" data-ajax="false">Change Item Details</a></li>
  		  <li><a href="Router?sendTo=LogOut" data-ajax="false">Log Out</a></li>
		</ul>

		</div>
	
	
	
	<div data-role="header">
		<a href=#Options data-icon="bars" class="ui-btn-left" >Options</a>
		<span class="ui-title" />
	</div>
  <div data-role="main" class="ui-content">
  				<h1>User Details Update</h1>
  				<% 
  				User u =(User)request.getAttribute("user");%>
	     		 <p><form method="Post" action="Router">
     			   <div>
     			   <h3>Update Password</h3>
     			   <label for="password" >Password:</label>
     			   <input  type="password" name="Password" id="Password" placeholder="current paswword "   data-mini="true">
      			    <input  type="password" name="Password1" id="Password1" placeholder="new password "   data-mini="true">
      			    <input  type="password" name="Password2" id="Password2" placeholder="retype assword "   data-mini="true">
					<input type="hidden" name="page" value ="ChangeBudget">
					
     			   <h3>Update Frequency</h3>
     			    <label class="radio-inline" for="radio-choice-h-2a">
     			    <%if (u.getFrequency()==1){ %>
    			  	<input type="radio" name="radio" id="radio-choice-h-2a" value="Day" checked="checked">
    							  Day
    				<%}
     			    else{%>
     			    <input type="radio" name="radio" id="radio-choice-h-2a" value="Day">
     			    	Day
     			    	<%} %>
    					</label> 
    			 <label class="radio-inline" for="radio-choice-h-2a">
     			    <%if (u.getFrequency()==7){ %>
    			  	<input type="radio" name="radio" id="radio-choice-h-2a" value="Week" checked="checked">
    							  Week
    				<%}
     			    else{%>
     			    <input type="radio" name="radio" id="radio-choice-h-2a" value="Week">
     			    	Week
     			    	<%} %>
    					</label> 
    					 <label class="radio-inline" for="radio-choice-h-2a">
     			    <%if (u.getFrequency()==30){ %>
    			  	<input type="radio" name="radio" id="radio-choice-h-2a" value="Month" checked="checked">
    							  Month
    				<%}
     			    else{%>
     			    <input type="radio" name="radio" id="radio-choice-h-2a" value="Month">
     			    	Month
     			    	<%} %>
    					</label> 
       				
      			    <h3>Update Budget</h3>
      			    <%String budget = String.valueOf(u.getBudget()); %>
      			    <label for="budget" >Budget:</label>
      			    <input  type="number" name="budget" id="bug" placeholder="<%out.print(budget);%>"   data-mini="true">
					<input type="hidden" name="page" value ="ChangeBudget">
       			<button type="submit" id="submit-6" >Update</button>
				
       			   
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