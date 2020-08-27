<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="java.util.List"%>
<%@page import="com.iiht.evaluation.coronokit.model.*"%>
<%@page import="com.iiht.evaluation.coronokit.dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

	
	

	<%
	
		List<ProductMaster> products =  (List<ProductMaster>) request.getAttribute("Products");
	%>
	
	<table border="1" width="100%">
		<thead>
			<th>PRODUCT NAME</th>
			<th>COST</th>
			<th>PRODUCT DESCRIPTION</th>
			<th>ACTIONS</th>
			
		</thead>
		<tbody>
			<% for(ProductMaster product : products) { %>
			<tr>
				<td><%=product.getProductName()%></td>
				<td><%=product.getCost()%></td>
				<td><%=product.getProductDescription()%></td>
				<td><a href="user?action=addnewitem&ProductId=<%=product.getId()%>"><button>Add to Cart</button></a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	
	<%
if(session.getAttribute("ShoppingCart")!=null)
{
	List<KitDetail>  existingKits=(List<KitDetail>)session.getAttribute("ShoppingCart");
	int totalIteminCart=0;
	 for(int k=0;k<existingKits.size();k++)
	  {
		 totalIteminCart+=existingKits.get(k).getQuantity();
	  }
%>	
</br>
	<div>
	<a href="user?action=saveorder">Show Cart</a><b>(<%= totalIteminCart%>)</b>
  </div>
</br> 
 
  <div>
	<a href="user?action=placeorder"><button>Save order</button></b>
  </div>
 
<% } %>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>