

var form = document.getElementById("formcontenitore");
  form.addEventListener("submit", function(event) {
	  
    event.preventDefault();
    console.log(nome);
    console.log(cognome);
    if (nome&&cognome) {
      form.submit();
    } else {
    	errorDuplicate("Inserisci tutti i valori correttamente");
    }
  });
  

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

 //appena apri la pagina , avvia localDoc per inserire gli indirizzi e le carte dell utente
 loadDoc();
 function inputIndirizzoValid(){
	 console.log(cap);
	 console.log(citta);
	 console.log(regione);
	 console.log(via);
	 console.log(provincia);
		return (cap&&citta&&regione&&via&&provincia);
	}
 function inputCartaValid(){
		return (codice&&data&&nomeTitolare&&cvc);
	}
 //inserisci un nuovo indirizzo, o una nuova carta di credito ajax
 function insert() {
	 		
		
 if (inputIndirizzoValid()) {
	 
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
					      Successo("Indirizzo inserito correttamente.");
				    }},
			      error: function(jqXHR, textStatus, errorThrown) {
			    	    // Gestisci gli errori qui
			    	  }
			    });
			    
			    
			  } 
		  else if (inputCartaValid()) {
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
					      Successo("Carta inserita correttamente.");
					      
				    }},
			      error: function(jqXHR, textStatus, errorThrown) {
			    	    // Gestisci gli errori qui
			    	  }
			    });
			    
			    
			  } 		 
			
		}

function abilitaPulsanti(){
    const inputs = document.querySelectorAll('.inputProfilo');
    const select = document.gete
    for(let i = 0; i < inputs.length; i++){
    	if(inputs[i].name!=="email"){
      inputs[i].style.pointerEvents = 'auto';
      inputs[i].style.border = '2px solid ';
    	}
    }
    document.querySelectorAll(".div_select").forEach(function(element) {
    element.classList.remove("div_select");
  
    });
  document.querySelectorAll("select").forEach(function(element) {
  element.style.display = "block";
  });
  document.getElementById("annullaProfilo").style.setProperty("display","inline");
  document.getElementById("salvaProfilo").style.setProperty("display","inline");
  document.getElementById("modificaProfilo").style.setProperty("display","none");
  var button = document.getElementById("salvaProfilo");
  button.addEventListener("mouseover", function() {
    this.style.backgroundColor = "green";
  });
  button.addEventListener("mouseout", function() {
    this.style.backgroundColor = "";
  });  
  }
