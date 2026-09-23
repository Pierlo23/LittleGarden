<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Scheda Albero</title>
	<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<nav id="navbar">
		<a href="${pageContext.request.contextPath}/Home">
			<img src="${pageContext.request.contextPath}/Immagini/logo.png" alt="logo LittleGarden" width="150">
		</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a></li>
			<li>
				<a href="${pageContext.request.contextPath}/Carrello">
					Carrello <c:if test="${not empty sessionScope.carrello}">
						(${sessionScope.carrello.alberi.size()})
					</c:if>
				</a>
			</li>
			<c:choose>
				<c:when test="${empty sessionScope.utenteLoggato}">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/registrazione">Registrati</a></li>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.utenteLoggato.admin}">
                    <li><a href="${pageContext.request.contextPath}/admin/GestioneCatalogo">Area Admin</a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/common/Giardino">Il tuo giardino</a>
                    <li><a href="${pageContext.request.contextPath}/Logout">Esci</a></li>
                </c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<div class="dettaglio">
		<div class="info-albero">
			<h2>${albero.nome}</h2>
		</div>	
	</div>
</body>
</html>