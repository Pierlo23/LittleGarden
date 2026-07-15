<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalogo</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
	<div id="navbar">
		<a href="${pageContext.request.contextPath}/Catalogo">
			<img src="${pageContext.request.contextPath}/immagini/logo.png" alt="logo LittleGarden" width="150">
		</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a></li>
			<li>
				<a href="${pageContext.request.contextPath}/Catalogo?action=mostraCarrello">
					Carrello <c:if test="${not empty carrello}">
						(${carrello.alberi.size()})
					</c:if>
				</a>
			</li>
			<c:choose>
				<c:when test="${empty sessionScope.utenteLoggato}">
                    <li><a href="${pageContext.request.contextPath}/Login">Login / Registrati</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/Admin">Area Admin</a></li>
                    <li><a href="${pageContext.request.contextPath}/Logout">Esci</a></li>
                </c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	
	<div id="catalogo">
		<h2>Catalogo</h2>
		<div class="prodotti">
			<c:forEach var="albero" items="${alberi}">
				<div class="prodotto">
					<img src="${pageContext.request.contextPath}/Immagini?action=show&id=${albero.idAlbero}" alt="Foto ${albero.nome}" width="200">
					<h2><c:out value="${albero.nome}"/></h2>
                    <p>Descrizione: <c:out value="${albero.descrizione}"/></p>
                    <p>Prezzo: &euro; <c:out value="${albero.prezzo}"/></p>
                    <form action="${pageContext.request.contextPath}/Catalogo" method="POST">
                    	<input type="hidden" name="action" value="addC">
                    	<input type="hidden" name="id" value="${albero.idAlbero}">
                    	<input type="submit" value="Aggiungi al Carrello">
                    </form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>