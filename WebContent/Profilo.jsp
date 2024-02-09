<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.General" %>

<%@ page import="Beans.UtenteBean" %>
<%@ page import="Beans.IndirizzoBean" %>
<%@ page import="Beans.CartaCreditoBean" %>


<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Profilo</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  

  
  <link rel="stylesheet" href="CSS/Registrazione.css">
  <link rel="stylesheet" href="CSS/Modale.css">
<link rel="stylesheet" href="CSS/Profilo.css">  
<link rel="stylesheet" href="CSS/erroreOut.css">  
  <script src="JS/Errore.js"></script>
  
  


<style type="text/css">

 
  


</style>

</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
	if(!(utente instanceof UtenteBean) || utente==null){
		response.sendRedirect("ErroreServlet?errore=Profilo");
		return;
	}
	
	UtenteBean utenteBean =(UtenteBean) utente;
	List<CartaCreditoBean> listaCarte =(List<CartaCreditoBean>) session.getAttribute("listaCarte");
	List<IndirizzoBean> listaIndirizzi =(List<IndirizzoBean>) session.getAttribute("listaIndirizzi");
	 
	

    %>
<body>

<%@include file="Header.jsp" %>


    <div id="erroreTipo"></div>
 <div id="erroreDuplicate"></div>


   <!-----------------------------------------------------------PROFILO---------------------------------------->
        
          <div id="contenitore" >
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div id="titolocontenitore"><p>Profilo</p></div>
           
            <form id="formcontenitore" method="post" action="ProfiloServlet">
              <div class="inputcontenitore">
                <label for="nome"><i class="fa-sharp fa-solid fa-envelope"></i> Nome</label>
                <input type="text" placeholder="Luca" title="Massimo 20 caratteri" maxlength="20" class="inputProfilo" id="nome" name="nome" required value="<%=utenteBean.getNome() %>" >
              </div>
              <div class="inputcontenitore">
                <label for="cognome"><i class="fa-sharp fa-solid fa-envelope"></i> Cognome</label>
                <input type="text" maxlength="30" placeholder="Rossi" title="Massimo 30 caratteri" class="inputProfilo" id="cognome" name="cognome" required value="<%=utenteBean.getCognome() %>"  >
              </div>
              <div class="inputcontenitore">
                <label for="telefono"><i class="fa-sharp fa-solid fa-envelope"></i> Telefono</label>
                <input type="text" maxlength="10" placeholder="3245678934"  class="inputProfilo" id="telefono" name="telefono" required value="<%=utenteBean.getTelefono() %>"  >
              </div>
              <div class="inputcontenitore">
                <label for="email"><i class="fa-sharp fa-solid fa-envelope"></i> Email</label>
                <input type="text" class="inputProfilo" placeholder="mariorossi@gmail.com" id="email" name="email" required value="<%=utenteBean.getEmail() %>"  >
              </div>
                <div class="inputcontenitore">
                  <label for="password"><i class="fa-sharp fa-solid fa-envelope"></i> Password</label>
                  <input type="text" class="inputProfilo" placeholder="Mariorossi1234"  id="password" name="password" title="Inserisci almeno 8 caratteri, almeno una maiuscola ed almeno un numero" required value="<%=utenteBean.getPassword() %>" >
                </div>
                
                <div class="inputcontenitore div_select">
                  <label ><i class="fa-sharp fa-solid fa-lock"></i> Indirizzo 
                    <i onclick="modalGenerale('Indirizzo','Indirizzo')" class="aggiungiQualcosa fa-light fa-plus"></i>
                  </label>
                  <select id="indirizzi">
                  </select>
                </div>  
                <div class="inputcontenitore div_select">
                  <label for="fname"><i class="fa-sharp fa-solid fa-lock"></i>Carta di credito</label>
                  <select id="carte">
                  </select>
                </div>  
                <div id="aggiungiCarta" onclick="modalGenerale('CartaCredito','Carta')" >
                  <a >aggiungi carta di credito</a>
                </div>
                <div class="bottoniForm">
                  <button class="button-81 cestino" type="button" onclick="reloadPage()" role="button" id="annullaProfilo" >Annulla</button>
                  <button class="button-81" type="submit" role="button" id="salvaProfilo">Salva</button>
                  <button class="button-81" type="button" role="button" id="modificaProfilo" onclick="abilitaPulsanti()">Modifica</button>
                 </div>
                </form>
                </div>
                
<%@include file="Footer.html" %>   
<%@include file="Modale.html" %>   
<script src="JS/ControlliUtente.js"></script>
<script src="JS/Profilo.js"></script>
<script src="JS/AnnullaInputCartaIndirizzo.js"></script>

<script type="text/javascript">
var  cap,citta,regione,via,provincia;

	var nome="<%=utenteBean.getNome() %>";
	var cognome="<%=utenteBean.getCognome()%>";
</script>



</body>
</html>