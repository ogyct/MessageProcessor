<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Plain html message loader
	<form action="BaseServlet" method="POST">
		Text:
		<textarea name="text1" cols="40" rows="20"></textarea>
		<input type="submit" value="Submit" />

	</form>
	XML example <br>

	<textarea cols="40" rows="20">
<?xml version="1.0" encoding="UTF-8"?>
<actor xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="../xsd/actor.xsd">
  <actor_id>1</actor_id>
  <first_name>DMITRY</first_name>
  <last_name>AVGUSTIS</last_name>
  <deleteYN>N</deleteYN>
</actor>
	</textarea>
</body>
</html>