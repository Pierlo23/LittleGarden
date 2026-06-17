<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> <!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione</title>
</head>
<body>
	<h2>REGISTRAZIONE NUOVO UTENTE</h2>
	<% 
		List<String> errors = (List<String>) request.getAttribute("errors");
		
		String nome = (String) request.getAttribute("nome");
        if (nome == null) { nome = ""; }
        
        String cognome = (String) request.getAttribute("cognome");
        if (cognome == null) { cognome = ""; }
        
        String email = (String) request.getAttribute("email");
        if (email == null) { email = ""; }
        
        String indirizzo = (String) request.getAttribute("indirizzo");
        if (indirizzo == null) { indirizzo = ""; }
    %>

    <% if (errors != null && !errors.isEmpty()) { %>
        <div class="error">
            <ul>
                <% for (String err : errors) { %>
                    <li><%= err %></li>
                <% } %>
            </ul>
        </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/Registrazione" method="post">
        
        <label>Nome:</label><br>
        <input type="text" name="nome" value="<%= nome %>"><br><br>

        <label>Cognome:</label><br>
        <input type="text" name="cognome" value="<%= cognome %>"><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="<%= email %>"><br><br>

        <label>Password:</label><br>
        <input type="password" name="password"><br><br> 

        <label>Indirizzo:</label><br>
        <input type="text" name="indirizzo" value="<%= indirizzo %>"><br><br>

        <input type="submit" value="Invia Registrazione">
    </form>
    
    <br>
    <a href="${pageContext.request.contextPath}/login">Torna al login</a>
</body>
</html>