
function salvaModifica(servlet,id,tipo){
	var inputid = document.createElement("input");
	inputid.name = "inputAggiorna";
	inputid.value = id;
	inputid.type="hidden";
	
	var inputtipo = document.createElement("input");
	inputtipo.name = "inputTipo";
	inputtipo.value = tipo;
	inputtipo.type="hidden";

	// Ottenere il riferimento al form
	var form = document.getElementById("tableACE");

	// Aggiunta dell'input al form
	form.appendChild(inputid);
	form.appendChild(inputtipo);
	form.submit();
	
}




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

function Successo(testo){
	  // Mostra il messaggio di errore
var Message = $("<div>").addClass("errore").text(testo);
Message.css("background-color", "green");
$("#erroreDuplicate").append(Message );

// Nascondi il messaggio dopo 3 secondi
setTimeout(function() {
	Message.fadeOut("slow", function() {
    $(this).remove();
  });
}, 2400);
	
}

/**********************funzione inserimento autore,editore,categoria**************/
/*con chiamata ajax per ogni tipo. Se la risposta ricevuta dalla servlet è null
 * vuol dire che è un duplicato, altrimenti salva normalmente*/

function insert() {
	
	//.trim elimina gli spazi iniziali
	  if ($("#nomeAutore").val().trim() != "" || $("#cognomeAutore").val().trim() != "") {
	    var nomeAutore = $("#nomeAutore").val().trim();
	    var cognomeAutore = $("#cognomeAutore").val().trim();
	    var dati = {
	      "nome": nomeAutore,
	      "cognome": cognomeAutore,
	      "tipologia":"Autore"
	    };
	    $.ajax({
	      url: "AdminServlet",
	      method: "POST",
	      data: dati,
	      success: function(response) {
	  	    console.log(response);
		    if(response===""){
		    	errorDuplicate("L' autore è già presente.");
		    	
		    } else{
				console.log(response);
				$('#rigaVuota').remove();
		    	 var nuovoAutore = $('<div class="rowGeneral" id="bottoniAzione">' +
                         '<div class="cellGeneral">' +
                           '<input name="nomeAutore' + response+ '" value="' + nomeAutore+ '">' +
                         '</div>' +
                         '<div class="cellGeneral">' +
                           '<input name="cognomeAutore' + response + '" value="' + cognomeAutore + '">' +
                         '</div>' +
                         '<div class="cellGeneral" id="bottoniAzione">' +
                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + cognomeAutore+ '\',\'autore\')">' +
                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
                           '</button>' +
                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'autore\')" role="button">Salva</button>' +
                         '</div>' +
                       '</div>');

   			// Inserisci l'elemento dopo l'elemento con ID "headerTable"
   			nuovoAutore.insertAfter("#headerTable");
   			Successo("Inserimento è avvenuto con successo");
		    }
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
	    	    // Gestisci gli errori qui
	    	  }
	    });

	    document.getElementById("nomeAutore").value="";
	    document.getElementById("cognomeAutore").value="";
	    console.log( document.getElementById("nomeAutore").value);
	  }	
	  else if ($("#nomeCategoria").val().trim() != "" || $("#descrizioneCategoria").val().trim() != "") {
		    var nomeCategoria = $("#nomeCategoria").val().trim();
		    var descrizioneCategoria = $("#descrizioneCategoria").val().trim();
		    var dati = {
		      "nome": nomeCategoria,
		      "descrizione": descrizioneCategoria,
		      "tipologia":"Categoria"
		    };
		    $.ajax({
		      url: "AdminServlet",
		      method: "POST",
		      data: dati,
		      success: function(response) {
		    	  console.log(response);
		    	  if(response===""){
		    		  errorDuplicate("La categoria è già presente.");
			    	
			    } else{  
			    	$('#rigaVuota').remove();
			    	 var nuovoAutore = $('<div class="rowGeneral" id="bottoniAzione">' +
	                         '<div class="cellGeneral">' +
	                           '<input name="nomeCategoria' + response+ '" value="' + nomeCategoria+ '">' +
	                         '</div>' +
	                         '<div class="cellGeneral">' +
	                           '<input name="descrizioneCategoria' + response + '" value="' + descrizioneCategoria + '">' +
	                         '</div>' +
	                         '<div class="cellGeneral" id="bottoniAzione">' +
	                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + nomeCategoria+ '\',\'categoria\')">' +
	                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
	                           '</button>' +
	                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'categoria\')" role="button">Salva</button>' +
	                         '</div>' +
	                       '</div>');

	   			// Inserisci l'elemento dopo l'elemento con ID "headerTable"
	   			nuovoAutore.insertAfter("#headerTable");
	   			Successo("Inserimento è avvenuto con successo");
			    }},
		      error: function(jqXHR, textStatus, errorThrown) {
		    	    // Gestisci gli errori qui
		    	  }
		    });
		    document.getElementById("nomeCategoria").value="";
		    document.getElementById("descrizioneCategoria").value="";
		  } 
	  else if ($("#nomeEditore").val().trim() != "" ) {
		    var nomeEditore = $("#nomeEditore").val().trim();
		    
		    var dati = {
		      "nome": nomeEditore,
		      "tipologia":"Editore"
		    };
		    $.ajax({
		      url: "AdminServlet",
		      method: "POST",
		      data: dati,
		      success: function(response) {
		    	  console.log(response);
		    	  if(response===""){
		    		  errorDuplicate("L'editore è già presente.");
			    	
			    } else{  
			    	$('#rigaVuota').remove();
			    	 var nuovoAutore = $('<div class="rowGeneral" id="bottoniAzione">' +
	                         '<div class="cellGeneral">' +
	                           '<input name="nomeEditore' + response+ '" value="' + nomeEditore+ '">' +
	                         '</div>' +
	                         '<div class="cellGeneral" id="bottoniAzione">' +
	                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + nomeEditore+ '\',\'editore\')">' +
	                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
	                           '</button>' +
	                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'editore\')" role="button">Salva</button>' +
	                         '</div>' +
	                       '</div>');

	   			// Inserisci l'elemento dopo l'elemento con ID "headerTable"
	   			nuovoAutore.insertAfter("#headerTable");
	   			Successo("Inserimento è avvenuto con successo");
			    }},
		      error: function(jqXHR, textStatus, errorThrown)  {

		        }
		    });
		    document.getElementById("nomeEditore").value="";
		  }
	 
		
	}