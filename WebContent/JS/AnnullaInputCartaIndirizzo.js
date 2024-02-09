/**************************script per annullare gli input quando si preme su annulla del modale******/
  const resetButton = document.getElementById('annullaButton');
  resetButton.addEventListener('click', function() {

	  
	  document.getElementById("regione").value="";
	  document.getElementById("provincia").value="";
	  document.getElementById("cap").value="";
	  document.getElementById("via").value="";
	  document.getElementById("citta").value="";

	  document.getElementById("codice").value="";
	  document.getElementById("data").value="";
	  document.getElementById("cvc").value="";
	  document.getElementById("nomeTitolare").value="";
	  
	
    
  });
  const resetSaveButton = document.getElementById('salvaButton');
  resetSaveButton.addEventListener('click', function() {

	  
	  document.getElementById("regione").value="";
	  document.getElementById("provincia").value="";
	  document.getElementById("cap").value="";
	  document.getElementById("via").value="";
	  document.getElementById("citta").value="";

	  document.getElementById("codice").value="";
	  document.getElementById("data").value="";
	  document.getElementById("cvc").value="";
	  document.getElementById("nomeTitolare").value="";
	  
	
    
  });
  