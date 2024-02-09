function AggiungiCarrello(id,quantita){
    //$('#loadingSpinner').show(); // Mostra l'animazione di caricamento
	   
	    var dati = {
	    		"id": id,
	    		"quantita": quantita,
	    		"tipo":'aggiungi'
	    };
	    $.ajax({
	      url: "CarrelloServlet",
	      method: "POST",
	      data: dati,
	      success: function(response) {
	  	    console.log(response);
		    if(response===""){
		    	console.log(response+"non va bene");
	           // $('#loadingSpinner').hide(); // Nascondi l'animazione di caricamento anche in caso di errore
		    	
		    } else{
	            //$('#loadingSpinner').hide(); // Nascondi l'animazione di caricamento anche in caso di errore
		    	
		    	var elementoDaEliminare = document.querySelector(".notifica");

		    	// Verifica se l'elemento è stato trovato
		    	if (elementoDaEliminare) {
		    	  // Rimuovi l'elemento dal DOM
		    	  elementoDaEliminare.remove();
		    	}

		    	// Crea un nuovo elemento div per la notifica
		    	var elementoNotifica = document.createElement("div");
		    	
		    	elementoNotifica.innerText =response;
		    	
		    	elementoNotifica.classList.add("notifica");

		    	// Seleziona l'elemento con la classe CSS "carro"
		    	var elementoCarro = document.querySelector(".carro");

		    	// Verifica se l'elemento con la classe "carro" è stato trovato
		    	if (elementoCarro) {
		    	  // Appendi l'elemento notifica sotto l'elemento carro
		    	  elementoCarro.appendChild(elementoNotifica);
		    	}

				
		    }
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
	           // $('#loadingSpinner').hide(); // Nascondi l'animazione di caricamento anche in caso di errore
	    	    // Gestisci gli errori qui
	    	  }
	      
	    });
	  }	

