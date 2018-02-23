<%-- 
    Document   : barra_nav
    Created on : 4-ago-2017, 10.11.28
    Author     : Mirko
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${c=='bacheca' || c=='profilo'}">
    <ol>
                <div class="ImgProfilo">
                    <img title="fotoProfilo" alt="Foto Utente" src="immagini/PSN_Logo.png" />
                </div>
                <c:if test="${c=='bacheca'}">
                <li><a href='bacheca.html'>Bacheca</a></li>
                </c:if>
                <li><a href='login.html'>Login</a></li>
                <li><a href='descrizione.html'>Descrizione</a></li>
                <li><a href="login.html?logout=esci" class="logout">Logout</a></li>
    </ol> 
</c:if>