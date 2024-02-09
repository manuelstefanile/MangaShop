var form = document.getElementById("formcontenitore");
form.addEventListener("submit", function(event) {
  event.preventDefault();
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
 