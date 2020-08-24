<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Error</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h1>ERROR: Something went Wrong. Product Not Deleted</h1>
<hr/>
	<div>
		<h3>Something went wrong! Product We regret the inconvenience!</h3>
		<p>Error Message : <%=exception.getMessage()%> </p>
		<li><a href="admin?action=list"><button>PRODUCT LIST</button></a></li>
	</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>

