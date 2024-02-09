
function errorDuplicate(testo){
	window.history.replaceState({}, document.title, window.location.pathname);
	  // Mostra il messaggio di errore
  var errorMessage = $("<div>").addClass("errore").text(testo);
  $("#erroreTipo").append(errorMessage);
  
  // Nascondi il messaggio dopo 3 secondi
  setTimeout(function() {
    errorMessage.fadeOut("slow", function() {
      $(this).remove();
    });
  }, 2400);
	
}

function Successo(testo){
	window.history.replaceState({}, document.title, window.location.pathname);
	  // Mostra il messaggio di errore
var Message = $("<div>").addClass("errore").text(testo);
Message.css("background-color", "green");
$("#erroreTipo").append(Message );

// Nascondi il messaggio dopo 3 secondi
setTimeout(function() {
	Message.fadeOut("slow", function() {
    $(this).remove();
  });
}, 2400);
	
}
