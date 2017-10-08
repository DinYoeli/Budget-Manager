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
<script>     


</script>
<style>
DIV.table 
{
    display:table;
    font-family: arial, sans-serif;
    border: 1px solid black;   
    border-collapse: collapse;
    width: 50%;
}
FORM.tr, DIV.tr
{
    display:table-row;
    background-color: #dddddd;
    border: 1px solid black; 
}
SPAN.td
{
    display:table-cell;
    border: 1px solid #dddddd;
    text-align: left;
    padding: 4px;
    border: 1px solid black; 
}
SPAN.tbody
{
    display:table-row-group;
}
</style>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



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
  				<h1>Change Categories</h1>
	

		<div class="table">
    

      
    	<%
    		
    		List<Category> categoriesList =(List<Category>)request.getAttribute("categories");
    		if(categoriesList != null){
    			for(Integer i=0 ;i <categoriesList.size() ; i++)
    			{
    				Category category = categoriesList.get(i);
    		
    				%>
    				<form class="tr" method="Post" action="Router"> 
    				 <span class="td"> <input type="text" name="Type" id="Type" value=<%=category.getCategoryName() %> >  </span>
    				 <span class="td">
    				 <input type="hidden" name="page" value ="AddType">
    				 <input type="hidden" name="Update" value =<%=category.getId()%> >
    				 <input type="submit" value="Update" /> 
    				  </span>

    				 </form>
    				 <% 
    			}
    		}
   
    	%>
    	<h1>Add Category</h1>
      <form class="tr" method="Post" action="Router"> 
      			    <input  type="text" name="Type" id="category" placeholder=" "   data-mini="true">
      			    <input type="hidden" name="page" value ="AddType">
       			<button type="submit" id="submit-6" >Submit</button>
       			</form>
        </div>




	</div>
<div data-role="footer"  data-theme="a">
			<ex:Footer/>
		</div>
	</div>
</div>



</body>

</html>