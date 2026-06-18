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
	<nav>
		<ul>
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
	<footer>
	
	</footer>

</body>
</html>