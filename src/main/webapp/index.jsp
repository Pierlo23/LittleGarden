<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Little garden</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
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
					<li></li>
					<li><a href="${pageContext.request.contextPath}/logout">Esci</a>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	
	<header class="hero">
		<h1>Coltiva il tuo giardino virtuale</h1>
		<p>Scegli le tue piante, seguine lo sviluppo e ricevine i frutti direttamente a casa</p>
		<a href="${pageContext.request.contextPath}/Catalogo?action=mostraCatalogo" class="btn-hero">Catalogo</a>
	</header>
		
	<footer>
	
	</footer>

</body>
</html>