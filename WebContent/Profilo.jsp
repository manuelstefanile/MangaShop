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
<title>Home</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  

  
  <link rel="stylesheet" href="CSS/Registrazione.css">
  <link rel="stylesheet" href="CSS/Modale.css">
<link rel="stylesheet" href="CSS/Profilo.css">  
<link rel="stylesheet" href="CSS/erroreOut.css">  
  
  


<style type="text/css">

 
  


</style>

</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
	if(!(utente instanceof UtenteBean)){
		response.sendRedirect("ErorrAutorizzazione.jsp");
	}
	System.out.print(utente.getEmail());
	UtenteBean utenteBean =(UtenteBean) utente;
	List<CartaCreditoBean> listaCarte =(List<CartaCreditoBean>) session.getAttribute("listaCarte");
	List<IndirizzoBean> listaIndirizzi =(List<IndirizzoBean>) session.getAttribute("listaIndirizzi");
	 
	System.out.println(request.getAttribute("errore") + "errore = a ");

    %>
<body>

<%@include file="Header.jsp" %>


    
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
                <input type="text" class="inputProfilo" id="nome" name="nome" required value="<%=utenteBean.getNome() %>" >
              </div>
              <div class="inputcontenitore">
                <label for="cognome"><i class="fa-sharp fa-solid fa-envelope"></i> Cognome</label>
                <input type="text" class="inputProfilo" id="cognome" name="cognome" required value="<%=utenteBean.getCognome() %>"  >
              </div>
              <div class="inputcontenitore">
                <label for="telefono"><i class="fa-sharp fa-solid fa-envelope"></i> Telefono</label>
                <input type="text" class="inputProfilo" id="telefono" name="telefono" required value="<%=utenteBean.getTelefono() %>"  >
              </div>
              <div class="inputcontenitore">
                <label for="email"><i class="fa-sharp fa-solid fa-envelope"></i> Email</label>
                <input type="text" class="inputProfilo" id="email" name="email" required value="<%=utenteBean.getEmail() %>"  >
              </div>
                <div class="inputcontenitore">
                  <label for="telefono"><i class="fa-sharp fa-solid fa-envelope"></i> Password</label>
                  <input type="text" class="inputProfilo" id="password" name="password" required value="<%=utenteBean.getPassword() %>" >
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
                
                
<%@include file="Modale.html" %>   
<script src="JS/Profilo.js"></script>
<script src="JS/AnnullaInputCartaIndirizzo.js"></script>
 <script>
 function errorDuplicate(testo){
	  // Mostra il messaggio di errore
 var errorMessage = $("<div>").addClass("errore").text(testo);
 $("#erroreDuplicate").append(errorMessage);
 
 // Nascondi il messaggio dopo 3 secondi
 setTimeout(function() {
   errorMessage.fadeOut("slow", function() {
     $(this).remove();
   });
 }, 2400);
	
}
 
 function reloadPage() {
	  location.reload();
	}

 /*controlli*/
 var telefonoInput = document.getElementById("telefono");
	telefonoInput.addEventListener("input", function() {
		// rimuovi eventuali spazi vuoti
        var telefonoValue = telefonoInput.value.trim();
        // rimuovi tutti i caratteri non numerici
        telefonoValue = telefonoValue.replace(/\D/g, "");  
        telefonoInput.value = telefonoValue;
        
        if (telefonoValue.length != 10) {
        	telefonoInput.style.borderColor="red";
            telefonoInput.setCustomValidity("Il numero di telefono deve contenere 10 cifre.");
          } else {
        	  telefonoInput.style.borderColor="green";
            telefonoInput.setCustomValidity("");
          }
});
	
    
	var emailInput = document.getElementById("email");
    emailInput.addEventListener("input", function() {

      var emailValue = emailInput.value;
      // crea una regex per controllare se l'email � valida
      var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      // controlla se l'email � valida
      if (!emailRegex.test(emailValue)) {
    	  emailInput.style.borderColor="red";
        // se l'email non � valida, mostra un messaggio di errore
        emailInput.setCustomValidity("Inserisci un'email valida");
      } else {
    	  emailInput.style.borderColor="green";
        emailInput.setCustomValidity("");
      }
    });
    
    
    

	const passwordInput = document.getElementById("password");
	passwordInput.addEventListener("input", function() {
  	  const passwordValue = passwordInput.value;

  	  if (passwordValue.length < 8|| !/[A-Z]/.test(passwordValue) || !/[0-9]/.test(passwordValue)) {
  		passwordInput.style.borderColor="red";
    	  passwordInput.setCustomValidity("Inserisci una password valida.\nLunghezza almeno 8.\nAlmeno una maiuscola.\nAlmeno un numero");
  	    
  	  } else {
  		passwordInput.style.borderColor="green";
  		  passwordInput.setCustomValidity("");
  	    
  	  }
  	});
	
	
	
 

 
 function loadDoc() {
	  const xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      var risposta = JSON.parse(this.responseText);
	      var indirizzi = risposta.indirizzi;
	      var carte = risposta.carte;
	      
	      
	      for (var i = 0; i < indirizzi.length; i++) {
	    	  
	    	  let option = document.createElement("option");
	        option.innerHTML = indirizzi[i].citta+ " " + indirizzi[i].via;
	        document.getElementById("indirizzi").appendChild(option); 
	      }
	      for (var i = 0; i < carte.length; i++) {
	    	  
	    	  let option = document.createElement("option");
	        option.innerHTML = carte[i].codice;
	        document.getElementById("carte").appendChild(option); 
	      }

	   
	    }
	  };
	  xhttp.open("POST", "ProfiloServlet");
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("RichiestaCarteIndirizzi=1");
	}

 loadDoc();
 
 function insert() {
		
		
 if ($("#regione").val().trim() != "" && $("#provincia").val().trim() != ""&& $("#cap").val().trim() != ""&& $("#citta").val().trim() != ""&& $("#via").val().trim() != "") {
			    var regione = $("#regione").val().trim();
			    var provincia= $("#provincia").val().trim();
			    var cap= $("#cap").val().trim();
			    var citta= $("#citta").val().trim();
			    var via = $("#via").val().trim();
			    
			    var dati = {
			      "regione": regione,
			      "provincia": provincia,
			      "cap": cap,
			      "citta": citta,
			      "via" :via,
			      "tipologia":"Indirizzo"
			    };
			    $.ajax({
			      url: "ProfiloServlet",
			      method: "POST",
			      data: dati,
			      success: function(response) {
			    	  console.log(response);
			    	  if(response===""){
			    		  errorDuplicate("L'indirizzo è già presente.");
				    	
				    } else{  
					    let option = document.createElement("option");
						  option.innerHTML = citta + " "+ via;
					      document.getElementById("indirizzi").appendChild(option); 
				    }},
			      error: function(jqXHR, textStatus, errorThrown) {
			    	    // Gestisci gli errori qui
			    	  }
			    });
			    
			    
			  } 
		  else if ($("#codice").val().trim() != "" && $("#data").val().trim() != ""&& $("#nomeTitolare").val().trim() != ""&& $("#cvc").val().trim() != "") {
			    var codice = $("#codice").val().trim();
			    var data= $("#data").val().trim();
			    var nome= $("#nomeTitolare").val().trim();
			    var cvc= $("#cvc").val().trim();
			    
			    
			    var dati = {
			      "codice": codice,
			      "data": data,
			      "nome": nome,
			      "cvc": cvc,
			      "tipologia":"CartaCredito"
			    };
			    $.ajax({
			      url: "ProfiloServlet",
			      method: "POST",
			      data: dati,
			      success: function(response) {
			    	  console.log(response);
			    	  if(response===""){
			    		  errorDuplicate("La carta è già presente.");
				    	
				    } else{  
					    let option = document.createElement("option");
						  option.innerHTML = codice ;
					      document.getElementById("carte").appendChild(option); 
				    }},
			      error: function(jqXHR, textStatus, errorThrown) {
			    	    // Gestisci gli errori qui
			    	  }
			    });
			    
			    
			  } 		 
			
		}

 
 </script>


</body>
</html>