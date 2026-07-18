<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Carrello</title>
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
				<a href="${pageContext.request.contextPath }/Carrello">
					Carrello <c:if test="${not empty sessionScope.carrello }">
					 (<c:out value="${sessionScope.carrello.alberi.size()}"/>)
					</c:if>
				</a>
			</li>
			<c:choose>
				<c:when test="${empty sessionScope.utenteLoggato }">
				<li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
			</c:when>
			<c:otherwise>
                	<c:if test="${sessionScope.utenteLoggato.admin}">
                    	<li><a href="${pageContext.request.contextPath}/admin/GestioneCatalogo">Area Admin</a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/Logout">Esci</a></li>
            </c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<div id="contenutoCarrello">
		<h2>Carrello</h2>
		<c:choose>
			<c:when test="${empty sessionScope.carrello or empty sessionScope.carrello.alberi}">
				<p>Il tuo carrello è vuoto</p>
			    <a href="${pageContext.request.contextPath}/Catalogo" class="btn">Torna la catalogo</a>
			</c:when>
			<c:otherwise> 
				<div class="listacarrello">
					<c:forEach var="albero" items="${sessionScope.carrello.alberi}">
						<div class="prodottocarrello">
						<img src="${pageContext.request.contextPath}/immagini?action=show&id=${albero.idAlbero}" alt="foto <c:out value='${albero.nome}'/>" width="100">
					
					<div class="infocarrello">	
						<h3><c:out value="${albero.nome}"/></h3>
							<p>Prezzo: &euro; <c:out value="${albero.prezzo}"/></p>
					</div>
					<form action="${pageContext.request.contextPath}/Carrello" method="POST">
						<input type="hidden" name="action" value="deleteC">
						<input type="hidden" name="id" value= "<c:out value='${albero.idAlbero}'/>">
						<input type="submit" value="Rimuovi" class="btn-rimuovi">
					</form>
						</div>
					</c:forEach>
				</div>
				<div class="riepilogocarrello">
					<h3>Totale: &euro; <c:out value="${sessionScope.carrello.prezzoTotale}"/></h3>
					<div class="azioniCarrello">
						<a href="${pageContext.request.contextPath}/Catalogo" class="btn">Continua gli acquisti</a>
						<form action="${pageContext.request.contextPath}/Checkout" method="GET">
							<input type="submit" value="Procedi al Checkout" class="btn-checkout">
						</form>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>