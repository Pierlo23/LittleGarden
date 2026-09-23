<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione</title>
	<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<a href="${pageContext.request.contextPath}/Home">
		<img src="${pageContext.request.contextPath}/Immagini/logo.png" alt="logo LittleGarden" width="150">
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
	
	
    <form class="login" action="${pageContext.request.contextPath}/registrazione" method="post">
        
        <legend>Registrazione nuovo utente</legend>
        <label>Nome:</label><br>
        <input class="textinput" type="text" name="nome" value="${nome}">
        <br>
        <label>Cognome:</label>
        <input class="textinput" type="text" name="cognome" value="${cognome}">
        <br>
        <label>Email:</label>
        <input class="textinput" type="email" name="email" value="${email}">
        <br>
        <label>Password:</label>
        <input class="textinput" type="password" name="password">
        <br> 
        <label>Indirizzo:</label>
        <input class="textinput" type="text" name="indirizzo" value="${indirizzo}">
        <br>
        <div class="pulsanti">
        	<input class="pulsante" type="submit" value="Registrati">
    	</div>
    </form>
    
    <br>
    <a href="${pageContext.request.contextPath}/login">Torna al login</a>
</body>
</html>