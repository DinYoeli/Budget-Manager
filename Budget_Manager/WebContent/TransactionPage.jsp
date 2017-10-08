<%@page import="com.hit.objects.Category"%>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>

<%@page import="java.util.List"%>
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




	<div data-role="page" id="home"   class="ui-body-a">
	
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
  		<h1>Transaction</h1>
	 <p><form method="Post" action="Router">
     			   <div>
      			    
      			    <label for="price" >Price:</label>
      			    <input  type="number" name="price" id="price" placeholder="100"   data-mini="true">
  					 <label for="item" >Item:</label>
      			    <input  type="text" name="item" id="item" placeholder=""   data-mini="true">
					<fieldset data-role="controlgroup" data-type="horizontal">
    			    <legend>Transaction Type:</legend>
       					 <input type="radio" name="radio" id="radio-choice-h-2a" value="on" checked="checked">
    				     <label for="radio-choice-h-2a">inc</label>
    				     <input type="radio" name="radio" id="radio-choice-h-2b" value="off">
     				     <label for="radio-choice-h-2b">dec</label>								    
 				   </fieldset>
					<label for="categories" class="select">categories</label>
						<select name="categories" id="select-choice-mini" data-mini="true" data-inline="true">
   						
	  					<%
	  						List<Category> categories =(List<Category>) request.getAttribute("categories");
	  						for(int i =0 ;i<categories.size();i++){
	  							out.print(" <option value='"+categories.get(i).getCategoryName()+"'> "+categories.get(i).getCategoryName()+ "</option>" );
	  						}
	  					%>
						</select>

					 <input type="hidden" name="page" value ="Transaction">
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