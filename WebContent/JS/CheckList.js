 	


function spostaElemento() {
    
    let footer= document.getElementById('footer');
    if(footer!=null) {
    var larghezzaSchermo = window.innerWidth;
    if (larghezzaSchermo < 850) { // esempio di soglia di larghezza
    	footer.style.marginTop="135px";
    } else {
    	footer.style.marginTop="35px";
    }
   }
}



// Aggiungere un ascoltatore di eventi resize alla finestra del browser
window.addEventListener('resize', spostaElemento);


$("#name").on("input", function (){
	if(this.value.length>0){
		this.setCustomValidity("");
		this.style.borderColor="green";
	
	}
	else{
		this.setCustomValidity("Inserisci un nome");
		this.style.borderColor="red";
	
	}
});
$("#cognome").on("input", function (){
	if(this.value.length>0){
		this.setCustomValidity("");
		this.style.borderColor="green";
	
	}
	else{
		this.setCustomValidity("Inserisci un cognome");
		this.style.borderColor="red";
	
	}
});


	$("#telefono").on("input", function (){
		var telefonoValue = this.value.trim();
		telefonoValue = telefonoValue.replace(/\D/g, "");  
	    this.value = telefonoValue;
		if(this.value.length==10){
			this.style.borderColor="green";
			this.setCustomValidity("");
			
		}
		else{
			this.setCustomValidity("Inserisci 10 numeri");
			this.style.borderColor="red";
			
		}
	});
	
$("#cap").on("input", function (){
	var capValue = this.value.trim();
    capValue = capValue.replace(/\D/g, "");  
    this.value = capValue;
	if(this.value.length==5){
		this.style.borderColor="green";
		this.setCustomValidity("");
		
	}
	else{
		this.setCustomValidity("Inserisci 5 numeri");
		this.style.borderColor="red";
		
	}
});
$("#provincia").on("input", function (){
	if(this.value.length>0){
		this.setCustomValidity("");
		this.style.borderColor="green";
	
	}
	else{
		this.setCustomValidity("Inserisci la Provincia");
		this.style.borderColor="red";
	
	}
});
$("#citta").on("input", function (){
	if(this.value.length>0){
		this.style.borderColor="green";
		this.setCustomValidity("");
	}
	else{
		this.setCustomValidity("Inserisci la Città");
		this.style.borderColor="red";
	}
});
$("#regione").on("input", function (){
	if(this.value.length>0){
		this.setCustomValidity("");
		this.style.borderColor="green";
	}
	else{
		this.setCustomValidity("Inserisci la Regione");
		this.style.borderColor="red";
	}
});
$("#indirizzo").on("input", function (){
	if(this.value.length>0){
		this.setCustomValidity("");
		this.style.borderColor="green";
	
	}
	else{
		this.setCustomValidity("Inserisci l'indirizzo");
		this.style.borderColor="red";
	
		}
});


$("#numeroCarta").on("input", function (){
	// 
    var codiceValue = this.value.trim();

    codiceValue = codiceValue.replace(/\D/g, "");  
    this.value = codiceValue;
	if(this.value.length==16){
		this.style.borderColor="green";
		
		this.setCustomValidity("");
		
	}
	else{
		this.setCustomValidity("Inserisci 16 numeri");
		this.style.borderColor="red";
		
		}
});
$("#cvc").on("input", function (){
	// 
    var cvcValue = this.value.trim();

    cvcValue = cvcValue.replace(/\D/g, "");  
    this.value = cvcValue;
	if(this.value.length==3){
		this.setCustomValidity("");
		this.style.borderColor="green";
		
	}
	else{
		this.setCustomValidity("Inserisci 3 numeri");
		this.style.borderColor="red";
		
		}
});
$("#nomeCarta").on("input", function (){
	// 
 
 
	if(this.value.length>0){
		this.style.borderColor="green";
		this.setCustomValidity("");
		
	}
	else{
		this.setCustomValidity("Inserisci un nome");
		this.style.borderColor="red";
		
		}
});
$("#meseCarta").on("input", function (){
	// 
	 var inputDate = new Date(this.value).getTime();
     var currentDate = new Date().getTime();
     
     

     // Confronta la data inserita con la data attuale
     if (inputDate > currentDate) {
 		this.setCustomValidity("");
		this.style.borderColor="green";
		
	}
	else{
		this.setCustomValidity("Inserisci una scadenza corretta");
		this.style.borderColor="red";
		
		}
});


function selezionaOpzione(){
	var valoreOpzione = document.getElementById("Indirizzi").value;
	console.log(valoreOpzione);
	var valuesArray = valoreOpzione.split(',');
	
	var regione = document.getElementById("regione");
	var provincia = document.getElementById("provincia");
	var citta = document.getElementById("citta");
	var indirizzo = document.getElementById("indirizzo");
	var cap = document.getElementById("cap");
	
	regione.value=valuesArray[2];
	provincia.value=valuesArray[1];
	citta.value=valuesArray[0];
	indirizzo.value=valuesArray[3];
	cap.value=valuesArray[4];
	console.log("2 "+ valuesArray[2] + " 1 "+ valuesArray[1] + " 0 " + valuesArray[2] + " 3" + valuesArray[3] +" 4" + valuesArray[4] );
    
}

function selezionaOpzioneCarte(){
	var valoreOpzione = document.getElementById("Carte").value;
	
	var valuesArray = valoreOpzione.split(',');
	

	var numero = document.getElementById("numeroCarta");
	var nome = document.getElementById("nomeCarta");
	var cvc = document.getElementById("cvc");
	
	var data= document.getElementById("meseCarta");
	
	console.log(" data = "+data);
	console.log(" data arr = "+ valuesArray[3]);
	
	
	numero.value=valuesArray[0];
	cvc.value=valuesArray[1];
	nome.value=valuesArray[2];
	data.value=formattaData(valuesArray[3]);
	
}
function formattaData(dataPassata){
	console.log(dataPassata);
	var componentiData = dataPassata.split('-'); // Dividi la data in componenti

	
	    var anno = componentiData[0];
	    var mese = componentiData[1];
	    // Crea una data usando l'oggetto Date
	    var dataFormattata = new Date(anno, mese - 1, 1); // Mese è basato su zero
	    // Formatta la data nel formato "anno-mese"
	    var annoFormattato = dataFormattata.getFullYear();
	    var meseFormattato = (dataFormattata.getMonth() + 1).toString().padStart(2, '0'); // Aggiunge uno e completa con zero a sinistra
	    var dataInFormatoAnnoMese = annoFormattato + '-' + meseFormattato;
		console.log(dataInFormatoAnnoMese )
		return dataInFormatoAnnoMese ;
}