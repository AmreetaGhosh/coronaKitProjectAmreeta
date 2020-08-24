<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<h2>Visitor Details</h2>
	<form action="user?action=insertuser" method="post">
	<div align = "center">
		<div>
			<div><label for="visitorName">Enter Name</label> </div>
			<div><input type="text" id="visitorName" name="visitorName"> </div>
		</div>
		<div>
			<div><label for="visitorEmailid">Enter Email Id</label> </div>
			<div><input type="text" id="visitorEmailid" name="visitorEmailid"> </div>
		</div>
		<div>
			<div><label for="visitorContactNumber">Enter Contact Number</label> </div>
			<div><input type="text" id="visitorContactNumber" name="visitorContactNumber"> </div>
		</div>
		
		<div>
			<div><label for="visitorDeliveryAddress">Enter Delivery Address</label> </div>
			<div><input type="text" id="visitorDeliveryAddress" name="visitorDeliveryAddress"> </div>
		</div>
		<div>
			<div><input type="submit" value="Start Shopping"> </div>
		</div>
		</div>
	</form>
</div>
<hr/>
	<jsp:include page="footer.jsp"/>
</body>
</html>