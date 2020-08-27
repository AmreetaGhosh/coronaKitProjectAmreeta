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
<title>Corona Kit- Billing Details</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h1>Corona Kit- Your Billing Details</h1>
<form action="user?action=ordersummary" method="post">
<%
response.setHeader("Cache-Control", "no-cache, no-store");
List<KitDetail> kits = (List<KitDetail>)session.getAttribute("ShoppingCart");
UserMaster visitor = (UserMaster)session.getAttribute("visitorDetails");

double totalBillAmount=0;

%>
<div align ="center"> ORDER ID: <%=kits.get(0).getCoronaKitId() %> </div>
<br/>

<br/>
<div align="center">

		
			<% for(KitDetail kit : kits) {
			
			totalBillAmount = totalBillAmount +kit.getAmount();
			session.setAttribute("billingAmount", totalBillAmount);
			session.setAttribute("billingDate", (new Date()).toLocaleString());
			} %>

	</div>
	<div align = "center">
		<div>
			<div><label for="visitorName">Enter Name</label> </div>
			<div><input type="text" id="visitorName" name="visitorName" value=<%=visitor.getVisitorName() %>> </div>
		</div>
		<div>
			<div><label for="visitorEmailid">Enter Email Id</label> </div>
			<div><input type="text" id="visitorEmailid" name="visitorEmailid" value=<%=visitor.getVisitorEmail() %> > </div>
		</div>
		<div>
			<div><label for="visitorContactNumber">Enter Contact Number</label> </div>
			<div><input type="text" id="visitorContactNumber" name="visitorContactNumber" value=<%=visitor.getVisitorContactNumber() %>> </div>
		</div>
		
		<div>
			<div><label for="visitorDeliveryAddress">Enter Delivery Address</label> </div>
			<div><input type="text" id="visitorDeliveryAddress" name="visitorDeliveryAddress" value=<%=visitor.getVisitorDeliveryAddress() %>> </div>
		</div>
		
		<div>
			<div><label for="billingAmount">Billing Amount</label> </div>
			<div><input type="text" id="billingAmount" name="billingAmount" value=<%=totalBillAmount %> readonly="readonly"  /></div>
		</div>
		<div>
			<div><input type="submit" value="Order Summary"> </div>
		</div>
		</div>
	
	</form>	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>