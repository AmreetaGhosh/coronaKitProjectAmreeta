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
<title>Corona Kit-My List</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>



<% 
	List<KitDetail> kits = (List<KitDetail>)session.getAttribute("ShoppingCart");
	UserMaster visitor = (UserMaster)session.getAttribute("visitorDetails");
	double totalBillAmount=0;
%>

<table border="1" width="100%">
		<thead>
			<th>CR ID</th>
			<th>PRODUCT NAME</th>
			<th>PRODUCT QUANITY</th>
			<th>AMOUNT</th>
			<th>ACTION</th>
			
		</thead>
		<tbody>
			<% for(KitDetail kit : kits) { %>
			<tr>
				<td><%=kit.getCoronaKitId()%></td>
				<td><%=kit.getProductName()%></td>
				<td><%=kit.getQuantity()%></td>
				<td><%=kit.getAmount()%></td>
				<td><a href="user?action=deleteitem&ProductId=<%=kit.getId()%>"><button>Remove from Cart</button></a></td>
			</tr>
			<%
			totalBillAmount = totalBillAmount +kit.getAmount();
			} %>
		</tbody>
	</table>
	<br/>
	<div align="center"> Total Bill Amount : <%=totalBillAmount %></div>
<div>
<br/>
<br/>
<a href = "user?action=showproducts">Continue Shopping</a>
</div>
<br/>
<br/>
<div>
<br/>
<div align = "center">
<a href="user?action=saveorder"><button>Save Order</button></a></div>


<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>