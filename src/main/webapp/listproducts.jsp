<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<p align="left">
	<a href="admin?action=newproduct"><button>Add New Product</button></a>
	</p>
	
	<p align="right">
  	<a href="admin?action=logout"><button>Log Out</button></a>
	</p>

	<%
		// fetch the shared data
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
				<td><a href="admin?action=editproduct&id=<%=product.getId()%>"><button>Edit</button></a>
				<a href="admin?action=deleteproduct&id=<%=product.getId()%>"><button>Delete</button></a></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>