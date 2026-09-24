<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalogo</title>
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
	
	
	<div id="catalogo">
		<h2>Catalogo</h2>
		<div class="prodotti">
			<c:forEach var="albero" items="${alberi}">
				<c:if test="${!albero.softDelete }">
				<a href="${pageContext.request.contextPath}/dettaglioAlbero?id=${albero.idAlbero}" class="link-dettaglios">
					<div class="prodotto">
						<img src="${pageContext.request.contextPath}/Immagini?action=show&id=${albero.idAlbero}" alt= "Foto <c:out value='${albero.nome}'/>" width="200">
						<h3><c:out value="${albero.nome}"/></h3>
                    	<p>Descrizione: <c:out value="${albero.descrizione}"/></p>
                    	<p>Prezzo: &euro; <c:out value="${albero.prezzo}"/></p>
                    	<form action="${pageContext.request.contextPath}/Carrello" method="POST">
                    		<input type="hidden" name="action" value="addC">
                    		<input type="hidden" name="id" value="${albero.idAlbero}">
                    		<input type="submit" value="Aggiungi al Carrello">
                    	</form>
					</div>
				</a>	
				</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>