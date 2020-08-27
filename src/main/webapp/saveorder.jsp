<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.*"%>
<%@page import="java.util.List"%>
<%@page import="com.iiht.evaluation.coronokit.model.*"%>
<%@page import="com.iiht.evaluation.coronokit.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit- Order Summary</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h1>Corona Kit- Your Order Summary</h1>
<form action="user?action=placeorder" method="post">
<%
response.setHeader("Cache-Control", "no-cache, no-store");
List<KitDetail> kits = (List<KitDetail>)session.getAttribute("ShoppingCart");
UserMaster visitor = (UserMaster)session.getAttribute("visitorDetails");
double totalBillAmount=0;
%>
<div align ="center"> ORDER ID: <%=kits.get(0).getCoronaKitId() %> </div>
<br/>
<div align="center">
<table border="1" width="100%">
		<thead>
			<th>PRODUCT NAME</th>
			<th>QUANITY</th>
			<th>AMOUNT</th>
			
		</thead>
		<tbody>
			<% for(KitDetail kit : kits) { %>
			<tr>
				<td><%=kit.getProductName()%></td>
				<td><%=kit.getQuantity()%></td>
				<td><%=kit.getAmount()%></td>
			</tr>
			<% 
			totalBillAmount = totalBillAmount +kit.getAmount();
			} %>
		</tbody>
	</table>
	<br/>
	<div align="center"> Total Bill Amount : <%=totalBillAmount %></div>
	</div>
	<br/>
	<br/>
	
<div align = "center">
<a href="user?action=placeorder"><button>Add Address and Place Order</button></a></div>
</form>	

<div align = "left">
<a href="user?action=showproducts"><button>Continue Shopping</button></a></div>
</form>
	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>