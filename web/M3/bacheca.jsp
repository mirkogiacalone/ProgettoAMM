<%-- 
    Document   : bacheca
    Created on : 3-mag-2017, 10.04.07
    Author     : Mirko
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
         <title>Bacheca</title>        
                 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, Bacheca, PHP, MySQL, AMM">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
                
        <c:if test="${NoAccesso == true}">
            <h1>Accesso non consentito</h1>
            <p>Impossibile visualizzare la pagina richiesta.</p>
        </c:if>
        
        <c:if test="${NoAccesso == false}">
         <nav>             
             <c:set var="c" value="bacheca" scope="request"></c:set>
             <jsp:include page="barra_nav.jsp"/>
        </nav>
        
      
        
       <div id="BodyDiv">              
        <jsp:include page="laterale.jsp"/>
       
            <div id="AllPosts">       
              
              <c:if test="${AttributoUtn == true }">
               <div class="Post">
                <div class="ImgProfilo">          
                <img title="fotoProfilo" alt="Foto Utente" src="${AttributoAut.getUrlImgPro()}" /><b>${AttributoAut.getNome()}</b> Frase personale:
                </div>
                    <p>${AttributoAut.getFrase()}</p>
               </div>
                            
                <c:if test="${friend == false}">
                               
                  <form action="" method="get">
                    <input type="hidden" name = "bacheca_di" value="${AttributoAut.getId()}" />
                    <div id="BtnAggiungiAmico">
                    <input type ='submit' name="aggiungi" value="Aggiungi Amico">
                    </div>
                  </form>
                
                </c:if>
               </c:if>
                
            <c:if test="${AttributoUtn != true}">
                <div class="Post">
             
                    <div class="ImgProfilo">
                    <img  title="fotoProfilo" alt="Foto Utente" src="${AttributoAut.getUrlImgPro()}"/>
                    </div>
                    <p>${AttributoAut.getArgomento()}</p>
                    
                    
            <!-- -----------Cancellazione gruppo -->
                    <c:if test="${AttributoAut.getCreatore().equals(user) || user.getPotereUtente() == 'SUPERUSER'}">  
                    <form action="" method="get">
                    <input type="hidden" name = "pagina_gruppo" value="${AttributoAut.getId()}" />
                    <div id="BtnEliminaGruppo">
                    <button class = "eliminag" type ='submit' name="eliminag" value=${AttributoAut.getId()}>Elimina Gruppo ${AttributoAut.getId()} </button>
                    </div>
                    </form>
                    </c:if>                     
            <!-- -----------FINE Cancellazione gruppo -->
                
            
                <c:if test="${iscritto == false}">                    
                        <form action="" method="get">
                            <c:if test="${registrati == false}">
                                <h1>Errore</h1>
                                <p>Errore durante l'iscrizione</p>
                            </c:if>
                            <input type="hidden" name = "pagina_gruppo" value="${AttributoAut.getId()}" />
                            <div id="BtnIscriviti">
                            <input class="iscriviti" type ='submit' name="iscriviti" value="Iscriviti al gruppo">
                            </div>
                        </form>                   
                    </c:if>
                </div>            
            </c:if>
          
                <c:if test="${friend == true || iscritto == true }">
              <div class="Post">
                <div id="nuovoPost">
                <form action="bacheca.html?bacheca_di=${AttributoAut.getId()}" method="get">
                <div>
                    <h2>Inserisci post</h2>
                </div>
                <c:if test="${inserisci_post == true}">                   
                    <p><strong>Autore:</strong> ${(n.getAutore()).getNome()}</p>
                    <p><strong>Destinatario:</strong>
                        <c:if test="${AttributoUtn == true}"> 
                            ${(n.getDestinatario()).getNome()}
                        </c:if>
                        <c:if test="${AttributoUtn != true}"> 
                            ${(n.getGruppo()).getNome()}
                        </c:if>
                    </p>
                    <p><strong>Testo:</strong></p> 
                        <c:if test="${attachment == 1}">
                            <p>${n.getContent()}</p><img alt="Post" src="${n.getAllegato()}">
                        </c:if>
                        <c:if test="${attachment == 2}">
                        <a href="${n.getAllegato()}">${n.getAllegato()}</a><p>${n.getContent()}</p>
                        </c:if> 
                        <c:if test="${attachment != 2 && attachment != 1}">
                            ${n.getContent()}
                        </c:if> 
                    
                   
                        <c:if test="${AttributoUtn == true}">
                        <input type="hidden" name ="bacheca_di" value="${AttributoAut.getId()}"/>
                        </c:if>
                        <c:if test="${AttributoUtn != true}">
                        <input type="hidden" name ="pagina_gruppo" value="${AttributoAut.getId()}"/>
                        </c:if>
                   
                        <br><button class = "post" type ='submit' name="conferma_inserimento" value=true>Conferma</button>
                </c:if>
                <c:if test="${conferma_inserimento == true}">
                    <h2>Il post è stato inserito nella bacheca di ${AttributoAut.getNome()}</h2>
                </c:if>
                <c:if test="${inserisci_post != true}">
                <div>
                    <label for="FrasePers">Testo del post:</label><br>
                    <div id="textareaTesto">
                    <textarea name="FrasePers" rows="4" cols="33" id="FrasePers" placeholder="Inserire il testo"></textarea>
                    </div>
                </div>
                <div>
                    <label for="link">Allegato:</label><input type="url" name="link" id="link" placeholder="Inserisci l'allegato">
                </div>
                <div>
                    <div class="DivRadio"><br>
                        <label for="immagine">Img</label><br>
                        <input type="radio" name="tipo" value="immagine" id="immagine"><br>
                        <label for="indirizzo_url">Link</label><br>
                        <input type="radio" name="tipo" value="indirizzo_url" id="indirizzo_url">
                        </div>
                </div>
                <div>
                    <c:if test="${AttributoUtn != true}">
                        <input type="hidden" name = "pagina_gruppo" value="${AttributoAut.getId()}"/>
                    </c:if>
                   <c:if test="${AttributoUtn == true}">
                        <input type="hidden" name = "bacheca_di" value="${AttributoAut.getId()}"/>
                    </c:if>

                </div>
                <button class="post" type="submit" >Invia post</button>
               </form>
                </c:if>
        </div>
       </div>
<br>              
          <c:forEach var="posts_elenco" items="${post}">
            <div class="Post">           
                <div>
                    <c:if test="${AttributoUtn == true}">
                      <div class="ImgProfilo">
                      <img src="${AttributoAut.getUrlImgPro()}" alt="${AttributoAut.getNome()}">
                      </div>
                    </c:if>
                    <c:if test="${AttributoUtn != true}">
                        <div class="ImgProfilo">
                        <img src="${posts_elenco.getAutore().getUrlImgPro()}" alt="${AttributoAut.getNome()}" id="utente">
                        </div>
                    </c:if>
                    <label>
                        <c:if test="${posts_elenco.getDestinatario() != null && AttributoUtn == true  }">
                         ${posts_elenco.getAutore().getNome()} -> ${AttributoAut.getNome()}
                        </c:if>
                         
                        <c:if test="${AttributoUtn == true && posts_elenco.getDestinatario() == null && posts_elenco.getGruppo() == null}">
                         ${AttributoAut.getNome()}
                        </c:if> 

                        <c:if test="${AttributoUtn == true && posts_elenco.getGruppo() != null}">
                         ${AttributoAut.getNome()} -> ${(posts_elenco.getGruppo()).getNome()}
                        </c:if>           
                         
                        <c:if test="${AttributoUtn != true}">
                         ${posts_elenco.getAutore().getNome()} -> ${AttributoAut.getNome()}
                        </c:if>
                    </label>
                </div>
                    
                 <c:if test="${posts_elenco.getTipo() == 'IMAGE'}">                
                    <p>${posts_elenco.getContent()}</p><img  width="214" height="120" alt="Immagine" src="${posts_elenco.getAllegato()}">               
                 </c:if>    
                    
                <c:if test="${posts_elenco.getTipo() == 'TEXT'}">
                <div>
                    <p>${posts_elenco.getContent()}</p>
                </div>
                </c:if>
               
                 <c:if test="${posts_elenco.getTipo() == 'URL'}">              
                    <a alt="URL" href="${posts_elenco.getAllegato()}">${posts_elenco.getAllegato()}</a><p>${posts_elenco.getContent()}</p>                
                 </c:if>
                    
                    <!-- -----------Cancellazione Post -->
                <c:if test="${user.getPotereUtente() == 'SUPERUSER'}">
                    <form action="" method="get"> 
                        <div id="BtnEliminaPost">
                        <button class = "elimina_post" type ='submit' name="elimina_post" value=${posts_elenco.getId()}>Elimina post</button>
                        </div>
                        <c:if test="${AttributoUtn == true}">
                            <input type="hidden" name = "bacheca_di" value="${AttributoAut.getId()}"/>
                        </c:if>
                        <c:if test="${AttributoUtn != true}">
                            <input type="hidden" name = "pagina_gruppo" value="${AttributoAut.getId()}"/>
                        </c:if>                   
                    </form>
                </c:if>
                 </div>              
            </c:forEach>
         </div>
        </c:if>
        </c:if>
        </div>        

    </body>
</html>
