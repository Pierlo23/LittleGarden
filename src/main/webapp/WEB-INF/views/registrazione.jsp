<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/Home">
		<img src="${pageContext.request.contextPath}/immagini/logo.png" alt="logo LittleGarden" width="150">
	</a>
	
	<h2>REGISTRAZIONE NUOVO UTENTE</h2>
	
	<c:if test="${not empty errors}">
		<div class="error">
			<ul>
				<c:forEach var="err" items="${errors}">
					<li>${err}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	
	
    <form action="${pageContext.request.contextPath}/registrazione" method="post">
        
        <label>Nome:</label><br>
        <input type="text" name="nome" value="${nome}"><br><br>

        <label>Cognome:</label><br>
        <input type="text" name="cognome" value="${cognome}"><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="${email}"><br><br>

        <label>Password:</label><br>
        <input type="password" name="password"><br><br> 

        <label>Indirizzo:</label><br>
        <input type="text" name="indirizzo" value="${indirizzo}"><br><br>

        <input type="submit" value="Invia Registrazione">
    </form>
    
    <br>
    <a href="${pageContext.request.contextPath}/login">Torna al login</a>
</body>
</html>