<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<a href="${pageContext.request.contextPath}/Home">
	<img src="${pageContext.request.contextPath}/immagini/logo.png" alt="logo LittleGarden" width="150">
</a>

<c:if test="${not empty errors}">
	<div class="error">
		<ul>
			<c:forEach var="err" items="${errors}">
				<li>${err}</li>
			</c:forEach>	
		</ul>
	</div>
</c:if>

<form class="login" action="${pageContext.request.contextPath}/login" method="post">
	<fieldset>
		<legend>Login</legend>
		<label for="email">Email</label>
		<input class="textinput" type="text" name="email" placeholder="inserisci l'email">
     	<br>   
     	<label for="password">Password</label>
     	<input class="textinput" type="password" name="password" placeholder="inserisci la password">
     	<br>
     	<div class="pulsanti">
     		<input class="pulsante" type="submit" value="Login"/>
     		<input class="pulsante" type="reset" value="Reset"/>
		</div>
	</fieldset>
</form>

<p id="crea">Nuovo utente? 
    <a href="${pageContext.request.contextPath}/registrazione">Crea un account</a>
</p>


</body>
</html>