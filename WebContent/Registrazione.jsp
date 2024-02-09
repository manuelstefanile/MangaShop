<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">
<title>Registrazione</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  
  
  <link rel="stylesheet" href="CSS/Registrazione.css">
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/erroreOut.css">

<style type="text/css">

/* Mostra la password quando l'icona viene cliccata */
.input-password input[type="password"].show-password {
  -webkit-text-security: none;
  text-security: none;
}

 
input:invalid + span::before {
  content: "⚠️ ";
}

input:invalid + span {
  display: block;
  color: red;
  margin-top: 5px;
  font-size: 14px;
}




</style>
  
</head>
<body>

<%@include file="Header.jsp" %>
<div id="erroreDuplicate"></div>

<%   
	if(u!=null){
		response.sendRedirect("ErroreServlet?errore=Registrazione");
		return;
	}
	%>


	       <!-----------------------------------------------------------REGISTRAZIONE---------------------------------------->
	       <%if (request.getParameter("errore")!= null) {%>
        <div class="errore">
        	<p>Errore email. Email già esistente!</p>
        </div>
        
        <%} %>
        <%request.setAttribute("errore", null);
 %>
        
          <div id="contenitore" >
            <div id="titolocontenitore"><p>Registrazione</p></div>
            <div class="effetto"><img src="Immagini/animazioni/aotFace.png"></div>
           <form action="UtenteServlet" method="post" id="formcontenitore">
              <div class="inputcontenitore">
                <label for="email"><i class="fa-sharp fa-solid fa-envelope"></i> Email</label>
                <input type="email" required="required" placeholder="ex. giacomo@rossi.it" id="email" name="email" >
              </div>
              <div class="inputcontenitore input-password">
                <label for="password"><i class="fa-sharp fa-solid fa-envelope"></i> Password</label>
                <input type="password" minlength="8" required id="password" title="Inserisci almeno 8 caratteri, almeno una maiuscola ed almeno un numero"  name="password" >
                 <span class="eye-icon" >
    			<i class="fa fa-eye-slash" onclick="togglePasswordVisibility()"></i>
    			
              </div>
              <div class="inputcontenitore">
                <label for="nome"><i class="fa-sharp fa-solid fa-envelope"></i> Nome</label>
                <input type="text" required="required" maxlength="20" placeholder="max-20 lettere" id="nome" name="nome" >
              </div>
              <div class="inputcontenitore">
                <label for="cognome"><i class="fa-sharp fa-solid fa-envelope"></i> Cognome</label>
                <input type="text" required="required" maxlength="30" placeholder="max-30 lettere" id="cognome" name="cognome" >
              </div>
                <div class="inputcontenitore">
                  <label for="telefono"><i class="fa-sharp fa-solid fa-envelope"></i> Telefono</label>
                  <input type="text" required="Inserisci un numero di telefono" maxlength="10"  placeholder="ex. 3273243462" id="telefono" name="telefono" >
                </div>
                
                <div class="contentBottone">
                    <button class="button-acquista" type="submit" role="button"><span class="text">Registrati</span><span>Registrati</span></button>
                  
                 </div>
                </form>
                </div>
<%@include file="Footer.html" %>   
 <script src="JS/ControlliUtente.js"> </script>
 <script src="JS/Registrazione.js"> </script>
<script type="text/javascript">

if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}
</script>

</body>
</html>