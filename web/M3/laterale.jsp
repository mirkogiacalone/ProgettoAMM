<%-- 
    Document   : laterale
    Created on : 4-ago-2017, 10.27.55
    Author     : Mirko
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <div id="Info">
     
         <div id="find">
                    <h2>Cerca utente</h2>
                       <div id="TextAreaFind">
                       <input type="text" class="cerca_friend" placeholder="ricerca">
                       </div>
                    <div id="BtnTextAreaFind">
                        <button id="ricerca_utente_btn">Cerca</button> 
                    </div>        
         </div>
        
         <div id="Persone">
                    <h2>Persone</h2>
                                        
                           <c:forEach var="utenti_elenco" items="${utenti}">
                              <div class="ImgProfiloSX">
                              <img title="fotoProfiloSX" src="${utenti_elenco.getUrlImgPro()}" alt="Foto Utente"><b><a href="bacheca.html?bacheca_di=${utenti_elenco.getId()}">${utenti_elenco.getNome()}</a></b>
                              </div>
                           </c:forEach>                                         
        </div>
                     
  
        <div id="Gruppi">
                    <h2>Gruppi</h2>
                           
                         <c:forEach var="gruppi_elenco" items="${gruppi}">
                             <div class="ImgProfiloSX">
                             <img title="fotoProfiloSX" src="${gruppi_elenco.getUrlImgPro()}" alt="Foto Utente" ><b><a href="bacheca.html?pagina_gruppo=${gruppi_elenco.getId()}">${gruppi_elenco.getNome()}</a></b>
                             </div>
                         </c:forEach>                                          
        </div>
               
  </div>