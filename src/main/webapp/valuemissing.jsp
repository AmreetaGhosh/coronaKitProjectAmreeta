<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>Value Missing</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<div>
		<h1>Name, Email ID and Phone Number is Mandatory.</h1>
		
		<div>
	<a href="user?action=newuser"><button>Navigate to Login</button></a>
</div>
</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>