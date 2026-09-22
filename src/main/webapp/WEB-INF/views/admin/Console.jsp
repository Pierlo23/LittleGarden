<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Console Admin</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
</head>
<body>
	<nav id="navbar">
		<a href="${pageContext.request.contextPath}/Catalogo">
			<img src="${pageContext.request.contextPath}/immagini/logo.png" alt="logo LittleGarden" width="150">
		</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/Catalogo">Catalogo</a></li>
                    <li><a href="${pageContext.request.contextPath}/Logout">Esci</a></li>
		</ul>
	</nav>
	
	<div class="dashboard">
		<div class="inserimento">
			<h2>Inserisci un nuovo albero</h2>
			<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST" class="form-admin">
				<input type="hidden" name="action" value="insert">
				<fieldset>
					<legend>Dati albero</legend>
					<p>
						<label for="nome">Nome albero</label>
						<input type="text" id="nome" name="nome" required>
					</p>	
					<p>	
						<label for="descrizione">Descrizione:</label>
						<textarea id="descrizione" name="descrizione" rows="3" required></textarea>
					</p>
					<p>
						<label for="prezzo">Prezzo (&euro;):</label>
						<input type="number" id="prezzo" name="prezzo" step="0.01" min="0" required>
					</p>
					<p>
						<label for="quantita">Quantità in magazzino:</label>
						<input type="number" id="quantita" name="quantita" min="0" placeholder="0" required>
					</p>
					<p>
						<label for="frutto">Produce frutti?</label>
						<input type="checkbox" id="frutto" name="frutto" value="true">
					</p>
					<p>	
						<button type="submit" class="btn-submit">Salva nuovo albero</button>
					</p>
				</fieldset>
			</form>
		</div>
		
		<hr>
		
		<div class="gestione-alberi">
			<h2>Gestione catalogo</h2>
			<table class="tabellaadmin" border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Prezzo</th>
						<th>Q.tà</th>
						<th>Stato (Soft Delete)</th>
						<th>Upload Immagine</th>
						<th>Azioni</th>
					</tr>
				</thead>
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
										<span class="stato-eliminato">Eliminato</span>
									</c:when>
									<c:otherwise>
										<span class="stato-attivo">Attivo</span>
									</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<form action="${pageContext.request.contextPath}/Immagini" method="POST" enctype="multipart/form-data">
									<input type="hidden" name="action" value="upload">
									<label for="immagine-${albero.idAlbero}">Immagine</label>
									<input type="file" name="immagine" accept="image/*" required>
									<input type="hidden" name="idAlbero" value="<c:out value='${albero.idAlbero}'/>">
									<button type="submit">Carica foto</button>
								</form>
							</td>
							
							<td class="azioni-cella">
								<c:choose>	
									<c:when test="${!albero.softDelete}">
										<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST" class="riga-form">
											<input type="hidden" name="action" value="update">
											<input type="hidden" name="id" value="<c:out value='${albero.idAlbero}'/>">
											<label for="prezzo-${albero.idAlbero}">Nuovo prezzo (&euro;)</label>
											<input type="number" id="prezzo-${albero.idAlbero}" name="prezzo" step="0.01" min="0" value="<c:out value='${albero.prezzo}'/>">
											<label for="quantita-${albero.idAlbero}">Nuova quantita</label>
											<input type="number" id="quantita-${albero.idAlbero}" name="quantita" min="0" value="<c:out value='${albero.quantita}'/>">
											<button type="submit" class="btn-update">Aggiorna</button>
										</form>
										
										<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST" class="riga-form">
											<input type="hidden" name="action" value="restore">
											<input type="hidden" name="id" value="<c:out value='${albero.idAlbero}'/>">
											<button type="submit" class="btn-delete">Rimuovi dal catalogo</button>
										</form>
									</c:when>
									<c:otherwise>
										<form action="${pageContext.request.contextPath}/admin/GestioneCatalogo" method="POST" class="riga-form">
											<input type="hidden" name="action" value="restore">
											<input type="hidden" name="id" value="<c:out value='${albero.idAlbero}'/>">
											<button type="submit" class="btn-restore">Ripristina</button>
										</form>
									</c:otherwise>	
								</c:choose>		
							</td>
						</tr>
					</c:forEach>
					</tbody>
			</table>

		</div>
	</div>
	
</body>
</html>