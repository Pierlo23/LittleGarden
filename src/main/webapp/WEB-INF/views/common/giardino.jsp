<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Il tuo giardino personale</title>
</head>
<body>
	<nav id="navbar">
		<a href="${pageContext.request.contextPath}/Home">
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
		</ul>
	</nav>
	<h2> Ciao, ${utenteLoggato.nome}</h2>
	<c:choose>
		<c:when test="${empty giardino}">
			<div style="text-align: center; margin-top: 50px;">
				<h3>Il tuo terreno è ancora spoglio!</h3>
				<p>Visita il <a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a> per piantare il tuo primo albero.</p>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="griglia-giardino">
				<c:forEach var="item" items="${giardino}">
					<div class="casella-albero">
						<img src="${pageContext.request.contextPath}/Immagini/${albero.pathImmagine}" alt="${albero.nome}">
						<h3>${albero.nome}</h3>
						<p>Ne possiedi: <strong>${albero.quantita}</strong></p>
						
						<form action="${pageContext.request.contextPath}/common/SpedizioneFrutti" method="post">
							<input type="hidden" name="idAlbero" value="${albero.idAlbero}">
							<button type="submit" class="btn-frutti">Richiedi Frutti</button>
						</form>
					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
	
</body>
</html>