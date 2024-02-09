function ProseguiAcquisto(){
	window.location.href = "AcquistoServlet";
}
     
function cambiaSubTotale(subtotaleId,prezzo,ogg,mangaId){
	  let regex = /^[0-9]+$/;
	  let valo=parseInt(ogg.value);
	  let max=parseInt(ogg.max);
	  
	  if(regex.test(valo)&&valo>0&&valo<=max){

	    	//aggiorna tutto
	    	//this.value =valore immesso
	    	var sub=document.getElementById(subtotaleId);
	    	var nuovoSub=(prezzo*ogg.value).toFixed(2);
	    	console.log(nuovoSub);
	    	sub.innerHTML =nuovoSub+" &euro;";
	    	
	    	let totale=0;
	    	//prendo gli elementi che iniziano per subtotale
	    	var elements = document.querySelectorAll('[id^="subtotale"]');
	    	for (var i = 0; i < elements.length; i++) {
	    	    var element = elements[i];
	    	    var costo = parseFloat(element.textContent);
	    	    totale+=costo;
	    	}
	    	
	    	var totTesto=document.getElementById('totaleCarrello');
	    	totTesto.innerHTML=totale.toFixed(2)+" &euro;";
	    	CambioValoreQuantita(ogg,mangaId);
	    	  		  
	  }else {
		  
		  ogg.value=1;
		  CambioValoreQuantita(ogg,mangaId);
		  cambiaSubTotale(subtotaleId,prezzo,ogg,mangaId);
	  }
 
			      

	
}
function CambioValoreQuantita(oggInput,idManga){
	   var dati = {
	    		
	    		"idManga": idManga,
	    		"quantita":oggInput.value,
	      		"tipo":"aggiornaQuantita"
	    };
	    $.ajax({
	      url: "CarrelloServlet",
	      method: "POST",
	      data: dati,
	      success: function(response) {
	  	    
		    if(response===""){
		    	//rimozione non avvenuta
		    	
		    } else{
		    	
		    	  
		    }
	      },
	      error: function(jqXHR, textStatus, errorThrown) {
	    	    // Gestisci gli errori qui
	    	  }
	    });

	
}

function rimuoviDalCarrello(idCarrello,idManga){
	
		
		//.trim elimina gli spazi iniziali
		  
		    var dati = {
		    		"idCarrello": idCarrello,
		    		"idManga": idManga,
		      "tipo":"rimuovi"
		    };
		    $.ajax({
		      url: "CarrelloServlet",
		      method: "POST",
		      data: dati,
		      success: function(response) {
		  	    
			    if(response===""){
			    	//rimozione non avvenuta
			    	
			    } else{
			    	let risposta=response.split("-");
			    	let totaleDelCarrello= risposta[0];
			    	let notificheNumero = risposta[1];
			    	console.log("risposta="+typeof response);
			    	console.log("risposta totale carrelllo="+totaleDelCarrello);
			    	console.log("risposta="+ notificheNumero);
			    	let riga;
			    	if(idCarrello!=0){
			    		riga="#rigaCarrello"+idCarrello;	
			    	}else riga="#rigaCarrello"+idManga;
					
					console.log(riga);
					$(riga).remove();
					$('#totaleCarrello').html(totaleDelCarrello);
					
					//da aggiornare la notifica.
			    	var elementoDaEliminare = document.querySelector(".notifica");
			    	if (elementoDaEliminare) {
			    	  elementoDaEliminare.remove();
			    	}
			    	var elementoNotifica = document.createElement("div");
			    	elementoNotifica.innerText =notificheNumero;
			    	elementoNotifica.classList.add("notifica");
			    	var elementoCarro = document.querySelector(".carro");
			    	if (elementoCarro) {
			    	  elementoCarro.appendChild(elementoNotifica);
			    	}
			    	  
			    }
		      },
		      error: function(jqXHR, textStatus, errorThrown) {
		    	    // Gestisci gli errori qui
		    	  }
		    });
 
}	