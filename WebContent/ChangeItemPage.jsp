<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@page import="com.hit.Hibernate.User"%>
<%@page import="com.hit.Hibernate.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="5">
<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<style>
DIV.table 
{
    display:table;
    font-family: arial, sans-serif;
    border: 1px solid black;   
    border-collapse: collapse;
    width: 100%;
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
    padding: 8px;
    border: 1px solid black; 
}
SPAN.tbody
{
    display:table-row-group;
}
</style>


</head>
<body>




	<div data-role="page" id="home"   class="ui-body-a">
	
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
  				<h1>Change Item</h1>
	

		<div class="table">
    

      
    	<%
    		double budgetSum =0;
    		List<Item> itemsList =(List<Item>)request.getAttribute("Items");
    		if(itemsList != null){
    			for(Integer i=0 ;i <itemsList.size() ; i++)
    			{
    				Item item = itemsList.get(i);
    				budgetSum+=item.getPrice();
    				%>
    				<form class="tr" method="Post" action="Router"> 
    				 <span class="td"> <% out.print(i+1); %>  </span>
    				 <span class="td"> <% out.print(item.getItem()); %>  </span>
    				 <span class="td"> <input type="text" name="price" id="price" placeholder=<%=item.getPrice() %> >  </span>
    				 <span class="td">  <% if(item.getPrice() >0) out.print("inc");else out.print("dec"); %> </span>
    				 <span class="td">  <% out.print( item.getCategory());%>  </span>
    				 <span class="td">  <%  out.print(item.getDate());%>  </span>
    				 <span class="td">
    				 
    				 <input type="hidden" name="page" value ="changeItem">
    				 <input type="hidden" name="item" value =<%=item.getId()%> >
    				<input type="submit" value="Submit" /> 
    				  </span>
    				 </form>
    				 <% 
    			}
    		}
   
    	%>
      
        </div>




	</div>
<div data-role="footer"  data-theme="a">
			<ex:Footer/>
		</div>
	</div>
</div>



</body>

</html>