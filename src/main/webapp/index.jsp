<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Reindirizza istantaneamente l'utente alla Servlet principale del sito
    response.sendRedirect(request.getContextPath() + "/Home");
%>