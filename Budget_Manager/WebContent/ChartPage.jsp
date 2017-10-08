<%@page import="com.hit.objects.*"%>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>


<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



<%!
//This function fits me between 
//my information proudly and returns it to me 
//in a string so I can insert it into the code in javascript
    // --- String Join Function
    public String join(Map<String, Double> map)
    {
        StringBuilder output = new StringBuilder();
        int i=0;
        for (Map.Entry<String, Double> entry : map.entrySet())
        {
        	if(entry.getValue().doubleValue()>0){
        	if (i > 0) output.append(',');
        	output.append('[');
        	output.append("'");
        	output.append(entry.getKey());
        	output.append("'");
        	output.append(',');
        	output.append((int)entry.getValue().doubleValue());
        	output.append(']');
        	i++;
        	}
        }
        

        return output.toString();
    }
%>

    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <%
  User user =(User) request.getAttribute("user");
  List<Item> itemsList =(List<Item>)request.getAttribute("Items");
  List<Category> categories =(List<Category>) request.getAttribute("categories");
  Map <String,Double> sumCategories =new HashMap();  
  for(int i = 0 ;i<categories.size();i++){
	
	  sumCategories.put(categories.get(i).getCategoryName(), 0.0);
  }
  for(int i = 0 ;i<itemsList.size();i++){
	  double temp = sumCategories.get(itemsList.get(i).getCategory());
	  sumCategories.put(itemsList.get(i).getCategory(),temp + itemsList.get(i).getPrice() );
  }
  %>
 			<script type="text/javascript">
				google.charts.load("current", {
					packages : [ "corechart" ]
				});
				google.charts.setOnLoadCallback(drawChart);
				function drawChart() {

					var data = google.visualization.arrayToDataTable([
							[ 'Category', 'Amount of money you spent ' ],
			<%=join(sumCategories)%>
				]);

					var options = {
						title : 'Amount of money you spent',
						pieHole : 0.4,
					};

					var chart = new google.visualization.PieChart(document
							.getElementById('donutchart'));
					chart.draw(data, options);
				}
			</script> 
    
  	<script type="text/javascript">
				google.charts.load('current', {
					'packages' : [ 'bar' ]
				});
				google.charts.setOnLoadCallback(drawStuff);

				function drawStuff() {
					var data = new google.visualization.arrayToDataTable([
							[ 'Category', 'Amount of money you spent ' ],
			<%=join(sumCategories)%>
				]);

					var options = {
						width : 600,
						legend : {
							position : 'none'
						},
						chart : {
							title : 'Amount of money you spent',
							subtitle : 'Amount of money you spent'
						},
						axes : {
							x : {
								0 : {
									side : 'top',
									label : ''
								}
							// Top x-axis.
							}
						},
						bar : {
							groupWidth : "30%"
						}
					};

					var chart = new google.charts.Bar(document
							.getElementById('top_x_div'));
					// Convert the Classic options to Material options.
					chart.draw(data, google.charts.Bar.convertOptions(options));
				};
			</script>
 
   


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
  
  				<h1>Chart</h1>
  				
  			 
  				
  				<div data-role="collapsible" data-content-theme="false">
					<h4>Chart 1</h4>
					<p>
						 <div id="donutchart" style="width: 600px; height: 400px;"></div>
					</p>
				</div>
				
				
  				 <div data-role="collapsible" data-content-theme="false">
					<h4>Chart 2</h4>
					<p>
  						<div id="top_x_div" style="width: 400px; height: 200px;"></div>
  					</p>
				</div>
  				 
  				 
				
	</div>
<div data-role="footer"  data-theme="a">
			<ex:Footer/>
		</div>
	</div>
</div>



</body>

</html>