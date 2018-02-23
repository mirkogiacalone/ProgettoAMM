<%-- 
    Document   : profilo
    Created on : 3-mag-2017, 10.05.27
    Author     : Mirko
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${NoAccesso == true}">
        <title>Non puoi accedere a questa pagina</title>
        </c:if>
            
        <c:if test="${NoAccesso == false}">
        <title>Profilo</title>
        </c:if>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, Profilo, PHP, MySQL, AMM">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
           
        <c:if test="${NoAccesso == true}">
            <h1>Attenzione</h1>
            <p>Non puoi accedere a questa pagina</p>
        </c:if>
            
        <c:if test="${datierrati == true}">
                <h2>Errore</h2>
                <p>Le password inserite devono essere uguali</p></div>
        </c:if>
        
            
    <c:if test="${NoAccesso == false}">
        <nav>
          <c:set var="c" value="profilo" scope="request"></c:set>
          <jsp:include page="barra_nav.jsp"/>
        </nav>
        <jsp:include page="laterale.jsp"/>      

    
<div id="BodyDiv">
<c:if test="${datierrati == null}">
   
    <div id="DivProfilo">   
      <h2>Profilo</h2>
      <p>Inserisci i tuoi dati nei campi che seguono</p> 
    <form>       
         <label>Nome</label>
         <input type="text" name="nome" placeholder="Inserisci il tuo Nome"><br>
         <label>Cognome</label>
         <input type="text" name="cognome" placeholder="Inserisci il tuo Cognome"><br>
         <label>URL immagine</label>
         <input type="text" name="ImmagineProfilo" placeholder="Inserisci l'url dell'immagine"><br>
         <label>Frase</label>
         <input type="text" name="FrasePers" placeholder="Inserisci una frase di presentazione"><br>
         <label>Nato/a il</label>
         <input type="text" name="dataDiNascita" placeholder="Inserisci la tua data di nascita"><br>
         <label>Password</label>
         <input type="password" name="password" placeholder="Inserisci la Password"><br>
         <label>Conferma</label>
         <input type="password" name="confermaPsw" placeholder="Reinserisci la Password"><br>
         
         <div id="BtnAggiornaProfilo"<div>            
          <input type="submit" value="Invia">
         </div>
         <div id="BtnCancellaProfilo"<div>
          <input id="cancella" type="submit" name="cancella" value="Cancella profilo">
         </div>
         
         
                
    </form>
    </div> 
    
    </div>
    </c:if>
        
        <c:if test = "${datierrati == false}">           
          <div id="DivProfilo">          
                <h2>Dati inseriti</h2>
                <p>I dati sono stati inseriti</p>     
            <div class="Post">
                <label><b>Nome:</b> ${user.getNome()}</label><br>
                <label><b>Cognome:</b> ${user.getCognome()}</label><br>
                <label><b>Nato/a il:</b> ${user.getData()}</label><br>  
                <label><b>Frase:</b> ${user.getFrase()} </label>    
            </div>
          </div>
            
        </c:if>
        
    </c:if>

    </body>
</html>
