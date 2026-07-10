<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Little garden</title>
</head>
<body>
	<header>
		<h1>LITTLE GARDEN</h1>
	</header>
	<nav class="navbar">
		<div class="logo">LittleGarden</div>
		<ul class="nav-links">
			<c:choose>
				<c:when test="${empty sessionScope.utenteLoggato}">
					<li><a href="${pageContext.request.contextPath}/login">Accedi</a></li>
					<li><a href="${pageContext.request.contextPath}/registrazione">Registrati</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/logout">Esci</a>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	
	<header class="hero">
		<h1>Coltiva il tuo giardino virtuale</h1>
		<p>Scegli le tue piante, seguine lo sviluppo e ricevine i frutti direttamente a casa</p>
	</header>
	
	<div class="vetrina">
		<h2>le nostre piante</h2>
		<div class="grid-prodotti">
			<div class="card-prodotto">
				<h3>Pianta 1</h3>
				<p>prezzo 1</p>
			</div>
			
			<div class="card-prodotto">
				<h3>Pianta 2</h3>
				<p>prezzo 2</p>
			</div>
		</div>
	</div>
	
	<footer>
	
	</footer>

</body>
</html>