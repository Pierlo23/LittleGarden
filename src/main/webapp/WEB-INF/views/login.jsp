<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<c:if test="${not empty errors}">
	<div class="error">
		<ul>
			<c:forEach var="err" items="${errors}">
				<li>${err}</li>
			</c:forEach>	
		</ul>
	</div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
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

<p>Nuovo utente? 
    <a href="${pageContext.request.contextPath}/registrazione">Crea un account</a>
</p>


</body>
</html>