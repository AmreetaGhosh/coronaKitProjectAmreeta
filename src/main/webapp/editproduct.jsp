<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<%
		// fetch the shared data
		ProductMaster existingProducts =  (ProductMaster)request.getAttribute("existingProduct");
	%>
<form action="admin?action=updateproduct&id=<%=existingProducts.getId() %>" method="post">


	<div>
		<div><label>Product name</label> <input type="text" name="pname" value=<%=existingProducts.getProductName()%>></div>
		
		<br/>
		<div><label>Cost</label> <input type="text" name="pcost" value=<%=existingProducts.getCost()%>></div>
		<br/>
		<div><label>Product Description</label> <input type="text" name="pdesc" value=<%=existingProducts.getProductDescription()%>></div>
		
		<div> <input type="submit" value="Update"> </div>
		
	</div>
</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>