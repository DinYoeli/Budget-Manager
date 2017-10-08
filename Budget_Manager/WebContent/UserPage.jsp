
<%@page import="com.hit.objects.Item"%>
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

 <jsp:useBean id = "user" type = "com.hit.objects.User" scope="session"> 
        
      </jsp:useBean>
      
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
		
<div data-role="popup" id="Delete" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:400px;">

    <div role="main" class="ui-content">
        <h3 class="ui-title">Are you sure you want to delete this page?</h3>
    <p>This action cannot be undone.</p>
        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back">Cancel</a>
        <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" data-rel="back" data-transition="flow">Delete</a>
    </div>
</div>
	
	
	
	<div data-role="header">
		<a href=#Options data-icon="bars" class="ui-btn-left" >Options</a>
		<span class="ui-title" />
	</div>
  <div data-role="main" class="ui-content">

	<h1>Welcome   
	 <jsp:getProperty name = "user" property = "name"/>
	  </h1>
  
		<p>You are shown in the table all your revenues and expenses for the selected period.</p>
		<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
      <thead>
        <tr>        
          <th>Row</th>
          <th data-priority="1">Name</th>
          <th data-priority="2">Price</th>
          <th data-priority="3">Transaction mode</th>
          <th data-priority="4">Type</th>
          <th data-priority="5">Date</th>
          <th data-priority="6">Delete</th>
        </tr>
      </thead>
       <tbody>
    	<%
    		double budgetSum =0;
    		List<Item> itemsList =(List<Item>)request.getAttribute("Items");
    		if(itemsList != null){
    			for(Integer i=0 ;i <itemsList.size() ; i++)
    			{
    				Item item = itemsList.get(i);
    				budgetSum+=item.getPrice();
    				%>
    				<tr>
    				<td>  <% out.print(i+1); %>  </td>
    				<td>  <% out.print(item.getItem()); %>  </td>
    				<td>  <% out.print( item.getPrice()); %>  </td>
    				<td>  <% if(item.getPrice() >0) out.print("inc");else out.print("dec"); %>  </td>
    				<td>  <% out.print( item.getCategory());%>  </td>
    				<td>  <%  out.print(item.getDate());%>  </td>
    				<td><%
    				
    				out.print(" <a href='Router?sendTo=delete&itemId="+item.getId() + "&deleteType=item'  class='ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-delete ui-btn-icon-left ui-btn-b' data-ajax='false'  >Delete </a> " );
    				
    				%> </td>
    				</tr><% 
    			}
    		}
   
    	%>
        </tbody>
         </table>
         <%
      	out.print("<h4> You spent ");
     	out.print(budgetSum);
     	out.print("</h4>");
    	out.print("<h4> Your remaining budget  ");
     	out.print(user.getBudget() - budgetSum);
     	out.print("</h4>");
         %>
         
         
         <%
      		String Message =(String) request.getAttribute("Message");
      		if(Message!=null){
      			out.print("<h4>  ");
      			out.print(Message);
      			out.print("</h4>");
      		}
      			
      	%>
         
         
         
         
	</div>
<div data-role="footer"  data-theme="a">
			<ex:Footer/>
		</div>
	</div>
</div>



</body>

</html>