<%-- 
    Document   : login
    Created on : 3-mag-2017, 10.05.11
    Author     : Mirko
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, Login, PHP, MySQL, AMM">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
<body>
        
        <nav>
            <ol>
                <li><a href='bacheca.html'>Bacheca</a></li>
                <li><a href='descrizione.html'>Descrizione</a></li>
                <li><a href="profilo.html">Profilo</a></li>
            </ol>
        </nav>
    

       
   
 <div id="BodyDiv">
     
      <div id="LogoLogin">
        <img  alt="logo" src="immagini/PSN_Logo.png" /> <h1>Login</h1>     
       </div>
       
   
    <div id="FormLogin">          
      <form action="" method="post">               
                <c:if test="${erroreAccesso == true}">
                <div id="Warning">Accesso negato, dati errati!</div>
                </c:if>  
          <div>
                <label>Username</label><br>
                <input type="text" name="user" placeholder="Inserisci il tuo Username"><br>

                <label>Password</label><br>
                <input type="password" name="password" placeholder="Inserisci Password">

                <br><br>
                <div id="btnLogin">
                <input type="submit" value="login">
                </div>
          </div>
     
      </form>
    </div>
     
</div>
   
</body>

</html>
