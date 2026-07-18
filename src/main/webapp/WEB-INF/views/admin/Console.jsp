<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Console Admin</title>
</head>
<body>
	<div id="navbar">
		<a href="${pageContext.request.contextPath}/Catalogo">
			<img src="${pageContext.request.contextPath}/immagini/logo.png" alt="logo LittleGarden" width="150">
		</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a></li>
                    <li><a href="${pageContext.request.contextPath}/Logout">Esci</a></li>
		</ul>
	</div>
	<div class="dashboard">
		<div class="insertimento">
			<h2>Inserisci un nuovo albero</h2>
			<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST" class="form-admin">
				<input type="hidden" name="action" value="insert">
				<label for="nome">Nome albero</label>
				<input type="text" id="nome" name="nome" required>
				<label for="descrizione">Descrizione:</label>
				<textarea id="descrizione" name="descrizione" rows="3" required></textarea>
				<label for="prezzo">Prezzo (&euro;):</label>
				<input type="number" id="prezzo" name="prezzo" step="0.01" min="0" required>
				<label for="quantita">Quantità in magazzino:</label>
				<input type="number" id="quantita" name="quantita" min="0" required>
				<label for="frutto">Produce frutti?</label>
				<input type="checkbox" id="frutto" name="frutto" value="true">
				<input type="submit" value="Salva nuovo albero" class="btn-submit">
			</form>
		</div>
		<hr>
		<div class="gestione alberi">
			<h2>Gestione catalogo</h2>
			<table class="tabellaadmin" border="1">
				<thread>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Prezzo</th>
						<th>Q.tà</th>
						<th>Stato (Soft Delete)</th>
						<th>Upload Immagine (Step 2)</th>
						<th>Azioni</th>
					</tr>
				</thread>
				<tbody>
					<c:forEach var="albero" items="${alberi}">
						<tr>
							<td><c:out value="${albero.idAlbero}"/></td>
							<td><c:out value="${albero.nome}"/></td>
							<td>&euro; <c:out value="${albero.prezzo}"/></td>
							<td><c:out value="${albero.quantita}"/></td>
							
							<td>
								<c:choose>
									<c:when test="${albero.softDelete}">
										<span style="color:red;">Eliminato</span>
									</c:when>
									<c:otherwise>
										<span style="color:green;">Attivo</span>
									</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<form action="${pageContext.request.contextPath}/admin/ImageControl" method="POST" enctype="multipart/form-data">
									<input type="hidden" name="id" value="<c:out value='${albero.idAlbero}'/>">
									<input type="file" name="immagine" accept="image/*" required>
									<input type="submit" value="Carica Foto">
								</form>
							</td>
							
							<td>
								<c:if test="${!albero.softDelete}">
									<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST">
										<input type="hidden" name="action" value="delete">
										<input type="hidden" name="id" value="<c:out value='${albero.idAlbero}'/>">
										<input type="submit" value="Rimuovi dal Catalogo" style="color:red;">
									</form>
								</c:if>
							</td>
						</tr>
					</c:forEach>
			</table>

		</div>
	</div>
	
</body>
</html>