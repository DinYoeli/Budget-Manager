<%@page import="com.hit.objects.User"%>
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

  		  <li><a href="Router?sendTo=LogOut">Log Out</a></li>
		</ul>

		</div>
		

	
	
	
	<div data-role="header">
		<a href=#Options data-icon="bars" class="ui-btn-left" >Options</a>
		<span class="ui-title" />
	</div>
  <div data-role="main" class="ui-content">

	<h1>   
	Adminstrator page
	  </h1>
  
		<p>You are shown in the table all your revenues and expenses for the selected period.</p>
		<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
      <thead>
        <tr>        
          <th>Row</th>
          <th data-priority="2">name</th>
          <th data-priority="3">budget</th>
          <th data-priority="4">frequency</th>
          <th data-priority="5">password</th>
          <th data-priority="6">Delete</th>
        </tr>
      </thead>
       <tbody>
    	<%
    		double budgetSum =0;
    		List<User> userList =(List<User>)request.getAttribute("Users");
    		if(userList != null){
    			for(Integer i=0 ;i <userList.size() ; i++)
    			{
    				User user= userList.get(i);
    				
    				%>
    				<tr>
    				<td>  <% out.print(i+1); %>  </td>
    				<td>  <% out.print( user.getName()); %>  </td>
    				<td>  <%  out.print(user.getBudget()); %>  </td>
    				<td>  <% 
    				if(user.getFrequency() ==7)
    					out.print("Week");
    				else if(user.getFrequency() ==1)
    					out.print("Day");
    				else 
    					out.print("Month");
					%>  </td>
    				<td>  <%  out.print(user.getPassword());%>  </td>
    				<td><%
    				
    				out.print(" <a href='Router?sendTo=delete&userName="+user.getName() + "&deleteType=user'  class='ui-btn ui-corner-all ui-shadow ui-btn-inline ui-icon-delete ui-btn-icon-left ui-btn-b'   >Delete </a> " );
    				
    				%> </td>
    				</tr><% 
    			}
    		}
   
    	%>
        </tbody>
         </table>

         
         
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