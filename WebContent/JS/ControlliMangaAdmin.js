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
var prezzo,quantitavar,titolo;
if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}
$("#descrizione").on("blur",function(){
	if(this.value.length>=255)
	     $(this).val(this.value.substring(0, 255)); 
	if(this.value.length>0){
		this.style.borderColor="green";
		
	}
	else{
		this.style.borderColor="red";
		
	}
})

$("#titolo").on("input",function(){
	if(this.value.length>100)
	     $(this).val(this.value.substring(0, 100)); 
	if(this.value.length>0){
		this.style.borderColor="green";
		titolo=true;
	}
	else{
		this.style.borderColor="red";
		titolo=false
	}
})
$("#quantita").on("input",function(){
    var quantita = this.value.trim();
    // rimuovi tutti i caratteri non numerici
    quantita = quantita.replace(/\D/g, "");  
    this.value = quantita;
    
	if(this.value>0){
		this.style.borderColor="green";
		quantitavar=true;
	}
	else{
		this.style.borderColor="red";
		quantitavar=false;
	}
})
$("#prezzo").on("input",function(){
	var costo = parseFloat($(this).val());
	console.log(costo);
    // rimuovi tutti i caratteri non numerici
    if (isNaN(costo) || costo <= 0) {
    	this.style.borderColor="red";
    	
    } else {
    	this.style.borderColor="green";
    	
    }
    
})
$("#prezzo").on("blur",function(){
	var costo = parseFloat($(this).val());
	console.log(costo+"nono");
	 if (!isNaN(costo) && costo>0 ){
		 console.log("nana!");
		 this.value=costo;
		 prezzo=true
	 }
	 else {
		 this.value=0;
		 prezzo=false; 
	 }
})


 var form = document.getElementById("formcontenitore");
  form.addEventListener("submit", function(event) {
	  console.log("prezzo= " + prezzo +" quantita" +quantitavar + " titolo" + titolo);
    event.preventDefault();
    
    if (prezzo&&quantitavar&&titolo) {
      form.submit();
    } else {
    	errorDuplicate("Inserisci tutti i valori correttamente");
    }
  });
