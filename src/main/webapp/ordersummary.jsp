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
<h1>Billing Successful</h1>
<h1>Corona Kit- Your Order Summary after Billing</h1>
<%
OrderSummary ordersummary = (OrderSummary)request.getAttribute("OrderSummary");
double totalBillAmount=0;

%>
<div align ="center"> ORDER ID: <%=ordersummary.getKitDetails().get(0).getCoronaKitId() %> </div>
<br/>

<br/>
	<div align="center">

		
			<% for(KitDetail kit : ordersummary.getKitDetails()) { %>
			
			<% 
			totalBillAmount = totalBillAmount +kit.getAmount();
			
			} %>

	</div>
	<div align = "center">
		<div>
			<div><label for="visitorName">Buyer Name</label> </div>
			<div><input type="text" id="visitorName" name="visitorName" value=<%=ordersummary.getCoronaKit().getPersonName() %> disabled="disabled"> </div>
		</div>
		<div>
			<div><label for="visitorEmailid">Email Id</label> </div>
			<div><input type="text" id="visitorEmailid" name="visitorEmailid" value=<%=ordersummary.getCoronaKit().getEmail() %> disabled="disabled" > </div>
		</div>
		<div>
			<div><label for="visitorContactNumber">Contact Number</label> </div>
			<div><input type="text" id="visitorContactNumber" name="visitorContactNumber" value=<%=ordersummary.getCoronaKit().getContactNumber() %> disabled="disabled"> </div>
		</div>
		
		<div>
			<div><label for="visitorDeliveryAddress">Enter Delivery Address</label> </div>
			<div><input type="text" id="visitorDeliveryAddress" name="visitorDeliveryAddress" value=<%=ordersummary.getCoronaKit().getDeliveryAddress() %> disabled="disabled"> </div>
		</div>
		<div>
			<div><label for="billingDate">Billing Date</label> </div>
			<div><input type="text" id="billingDate" name="billingDate" value=<%=(new Date()).toLocaleString() %> disabled="disabled"    /></div>
		</div>
		<div>
			<div><label for="billingAmount">Billing Amount</label> </div>
			<div><input type="text" id="billingAmount" name="billingAmount" value=<%=totalBillAmount %> disabled="disabled"   /></div>
		</div>
		
		</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>