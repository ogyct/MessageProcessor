<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	Plain html message loader
	<form action="ProcessMessageServlet" method="POST">
		Text:
		<textarea name="text1" cols="40" rows="20"><c:out
				value="${applicationScope.exampleXML}" escapeXml="false" /></textarea>
		<input type="submit" value="Submit" />
	</form>

	<div>
		<table id="actorTable">
			<tr>
				<th>Name</th>
				<th colspan="2">Last Name</th>
			</tr>
			<c:out value="${returnMessage}" escapeXml="false" />
		</table>
	</div>

</body>
</html>