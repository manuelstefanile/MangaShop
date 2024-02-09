function controlloCategoria(inputNome,descrizione){
	 let nomeVar=inputNome.value.trim();
	 let descrizioneVar=descrizione.value.trim();
	 
	 if(nomeVar.length>0&&descrizioneVar.length>0)
		 return true;
	 else return false;
 }
 
 function controlloAutore(inputNome,inputCognome){
	 let nomeVar=inputNome.value.trim();
	 let cognomeVar=inputCognome.value.trim();

	 if(nomeVar.length>0&&cognomeVar.length>0)
		 return true;
	 else return false;
 }
 function controlloEditore(inputNome){
	 let nomeVar=inputNome.value.trim();
	 
	 if(nomeVar.length>0)
		 return true;
	 else return false;
 }

 $(".nomeCategoria").on("input", function() {
	 
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
//Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  if(inputValue.length>20)
			     $(this).val(inputValue.substring(0, 20)); // Taglia il valore a 20 caratteri
		  if (inputValue.length > 0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});
 $(".nomeCategoria").on("blur", function() {
	 
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
//Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  if(inputValue.length>20)
			     $(this).val(inputValue.substring(0, 20)); // Taglia il valore a 20 caratteri
		  if (inputValue.length > 0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});
 $(".descrizioneCategoria").on("input", function() {
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
//Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  
		  if(inputValue.length>254)
			     $(this).val(inputValue.substring(0, 254));
		  if (inputValue.length > 0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});

 
 $(".nomeEditore").on("input", function() {
	 	
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
//Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  if(inputValue.length>20)
			     $(this).val(inputValue.substring(0, 20)); // Taglia il valore a 20 caratteri
		  if (inputValue.length > 0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});

 $(".nomeAutore").on("input", function() {
	  
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
// Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  if(inputValue.length>20){
		     $(this).val(inputValue.substring(0, 20)); // Taglia il valore a 20 caratteri
		     $(this).css("borderColor", "green"); // Imposta il bordo su verde
			    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		  }
		  if (inputValue.length >0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});
 $(".cognomeAutore").on("input", function() {
	  // Ottieni il valore dell'input specifico che ha scatenato l'evento
// Ottieni l'elemento DOM nativo
		  var inputElement = $(this).get(0);
		  var inputValue = inputElement.value;
		  if(inputValue.length>30)
			     $(this).val(inputValue.substring(0, 30)); // Taglia il valore a 20 caratteri
		  if (inputValue.length > 0) {
		    $(this).css("borderColor", "green"); // Imposta il bordo su verde
		    inputElement.setCustomValidity(""); // Rimuovi la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  } else {
		    $(this).css("borderColor", "red"); // Imposta il bordo su rosso
		    inputElement.setCustomValidity("Inserisci un nome"); // Imposta la validità personalizzata
		    // Esegui altre modifiche necessarie sull'input specifico
		  }
	});

 
 
function salvaModifica(servlet,id,tipo,boleano){
	if(boleano){
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
		form.submit();}
	else{
    	errorDuplicate("Riempi tutti i campi.");
	}
	
}


/**********************funzione inserimento autore,editore,categoria**************/
/*con chiamata ajax per ogni tipo. Se la risposta ricevuta dalla servlet è null
 * vuol dire che è un duplicato, altrimenti salva normalmente*/

function insert() {
	
	//.trim elimina gli spazi iniziali
	  if ($("#nomeAutore").val().trim() != "" && $("#cognomeAutore").val().trim() != "") {
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
                           '<input type="text"  class="nomeAutore" name="nomeAutore' + response+ '" value="' + nomeAutore+ '">' +
                         '</div>' +
                         '<div class="cellGeneral">' +
                           '<input type="text"  class="cognomeAutore" name="cognomeAutore' + response + '" value="' + cognomeAutore + '">' +
                         '</div>' +
                         '<div class="cellGeneral" id="bottoniAzione">' +
                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + cognomeAutore+ '\',\'autore\')">' +
                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
                           '</button>' +
                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'autore\',controlloAutore(document.getElementsByName(\'nomeAutore'+response+'\')[0],document.getElementsByName(\'cognomeAutore'+response+'\')[0]))" role="button">Salva</button>' +
                         '</div>' +
                       '</div>');
		    	 //metti anche gli errori del bordo

		    	 //metti anche gli errori del bordo cognome
		    	 

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
	  else if ($("#nomeCategoria").val().trim() != "" && $("#descrizioneCategoria").val().trim() != "") {
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
	                           '<input type="text"  class="nomeCategoria" name="nomeCategoria' + response+ '" value="' + nomeCategoria+ '">' +
	                         '</div>' +
	                         '<div class="cellGeneral">' +
	                           '<textarea rows="4" type="text" class="descrizioneCategoria" name="descrizioneCategoria'+ response+'">'+descrizioneCategoria+'</textarea>' +
	                         '</div>' +
	                         '<div class="cellGeneral" id="bottoniAzione">' +
	                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + nomeCategoria+ '\',\'categoria\')">' +
	                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
	                           '</button>' +
	                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'categoria\',controlloCategoria(document.getElementsByName(\'nomeCategoria'+response+'\')[0],document.getElementsByName(\'descrizioneCategoria'+response+'\')[0]))"  role="button">Salva</button>' +
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
	                           '<input type="text" class="nomeEditore" name="nomeEditore' + response+ '" value="' + nomeEditore+ '">' +
	                         '</div>' +
	                         '<div class="cellGeneral" id="bottoniAzione">' +
	                           '<button class="button-81 cestino" type="button" role="button" onclick="modalConfermaMostra(\'AdminServlet\',' + response + ',\'' + nomeEditore+ '\',\'editore\')">' +
	                             '<i class="fa-sharp fa-solid fa-trash"></i>' +
	                           '</button>' +
	                           '<button class="button-81" type="button" onclick="salvaModifica(\'AdminServlet\',' + response + ',\'editore\',controlloEditore(document.getElementsByName(\'nomeEditore'+response+'\')[0]))" role="button">Salva</button>' +
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