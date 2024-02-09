/**************************script per annullare gli input quando si preme su annulla del modale******/
  /*serve per annullare gli input quando si preme su annulla per le immagini*/
  const resetButton = document.getElementById('annullaButton');
  const input1 = document.getElementById('immaginePersonaggio');
  const input2 = document.getElementById('immagineCover');
  const input3 = document.getElementById('immagineTitolo');

  resetButton.addEventListener('click', function() {
	  document.getElementById("nomeEditore").value="";
	  document.getElementById("nomeAutore").value="";
	  document.getElementById("cognomeAutore").value="";
	  document.getElementById("nomeCategoria").value="";
	  
	  document.getElementById("regione").value="";
	  document.getElementById("provincia").value="";
	  document.getElementById("cap").value="";
	  document.getElementById("via").value="";
	  document.getElementById("citta").value="";
	  
    input1.value = '';
    input2.value = '';
    input3.value = '';
    document.getElementById("immagineCoverInput").value='';
    document.getElementById("immaginePersonaggioInput").value='';
    document.getElementById("immagineTitoloInput").value='';
    document.getElementById("immagineCoverimg").setAttribute("src", "");
    document.getElementById("immagineTitoloimg").setAttribute("src", "");
    document.getElementById("immaginePersonaggioimg").setAttribute("src", "");
    document.getElementById("immaginePersonaggioContenitore").style.height="0";
    document.getElementById("immagineTitoloContenitore").style.height="0";
    document.getElementById("immagineCoverContenitore").style.height="0";
    document.getElementById("titoloFileimmagineCover").innerHTML="";
    document.getElementById("titoloFileimmagineTitolo").innerHTML="";
    document.getElementById("titoloFileimmaginePersonaggio").innerHTML="";


    
  });