<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>
<form action="login" method="post">
	<fieldset>
		<legend>login</legend>
		<label for="email">email</label>
		<input id="email" type="text" name="email" placeholder="inserisci l'email">
     	<br>   
     	<label for="password">Password</label>
     	<input id="password" type="password" name="password" placeholder="inserisci la password">
     	<br>
     	<input type="submit" value="Login"/>
     	<input type="reset" value="Reset"/>
	</fieldset>
</form>

</body>
</html>