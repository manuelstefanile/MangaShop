
/*************Effetto del bottone aggiungi immagine**********************/
function effettoImmagini(){
      document.getElementById('effettoRiduzione').style.width='0';
      document.getElementById('testoBottone').style.color="black";
      
  }
function effettoImmaginiReverse(){
    document.getElementById('effettoRiduzione').style.width='100%';
    document.getElementById('testoBottone').style.color="white";
  }
  
/********************funzione errore dei Duplicati autore,editore,categoria********/


/**********************funzione inserimento autore,editore,categoria**************/
/*con chiamata ajax per ogni tipo. Se la risposta ricevuta dalla servlet è null
 * vuol dire che è un duplicato, altrimenti salva normalmente.
 * Qui mi prendo solo i nomi ed eventualmente i cognomi.*/


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
	  	    
		    if(response ==="null"){
		    	errorDuplicate("L' autore è già presente.");
		    	
		    } else{
		    	let option = document.createElement("option");
				option.innerHTML = nomeAutore + " " + cognomeAutore;
			    document.getElementById("autore").appendChild(option);
		    	
		    	
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
		    	  if(response==="null"){
		    		  errorDuplicate("La categoria è già presente.");
			    	
			    } else{  
				    let option = document.createElement("option");
					  option.innerHTML = nomeCategoria;
				      document.getElementById("categoria").appendChild(option); 
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
		    	  if(response==="null"){
		    		  errorDuplicate("L'editore è già presente.");
			    	
			    } else{  
				    let option = document.createElement("option");
					  option.innerHTML = nomeEditore;
				      document.getElementById("editore").appendChild(option); 
			    }},
		      error: function(jqXHR, textStatus, errorThrown)  {

		        }
		    });
		    document.getElementById("nomeEditore").value="";
		  }
	  else if ($("#regione").val().trim() != "" && $("#provincia").val().trim() != ""&& $("#cap").val().trim() != ""&& $("#citta").val().trim() != ""&& $("#via").val().trim() != "") {
		    var regione = $("#regione").val().trim();
		    var provincia= $("#provincia").val().trim();
		    var cap= $("#cap").val().trim();
		    var citta= $("#citta").val().trim();
		    var via = $("#via").val().trim();
		    
		    var dati = {
		      "regione": regione,
		      "provincia": provincia,
		      "cap": cap,
		      "citta": citta,
		      "via" :via,
		      "tipologia":"Indirizzo"
		    };
		    $.ajax({
		      url: "ProfiloServlet",
		      method: "POST",
		      data: dati,
		      success: function(response) {
		    	  console.log(response);
		    	  if(response==="null"){
		    		  errorDuplicate("L'indirizzo è già presente.");
			    	
			    } else{  
				    let option = document.createElement("option");
					  option.innerHTML = citta + " "+ via;
				      document.getElementById("indirizzi").appendChild(option); 
			    }},
		      error: function(jqXHR, textStatus, errorThrown) {
		    	    // Gestisci gli errori qui
		    	  }
		    });
		    
		    
		  } 
	 
		
	}

/*********************funzione chiamata ajax delle liste autore,editore,categoria***********/
function loadDoc() {
	  const xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      var risposta = JSON.parse(this.responseText);
	      var autori = risposta.autori;
	      var categorie = risposta.categorie;
	      var editori = risposta.editori;
	      
	      for (var i = 0; i < autori.length; i++) {
	    	  
	    	  let option = document.createElement("option");
	        option.innerHTML = autori[i].nome+ " " + autori[i].cognome;
	        document.getElementById("autore").appendChild(option); 
	      }
	      for (var i = 0; i < categorie.length; i++) {
	    	  
	    	  let option = document.createElement("option");
	        option.innerHTML = categorie[i].nome;
	        document.getElementById("categoria").appendChild(option); 
	      }
	      for (var i = 0; i < editori.length; i++) {
	    	  let option = document.createElement("option");
	    	  
	        option.innerHTML = editori[i].nome;
	        document.getElementById("editore").appendChild(option); 
	      }
	   
	    }
	  };
	  xhttp.open("POST", "AdminServlet");
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("RichiestaAutori=1");
	}

/*************************funzione del check di validita di alcuni input********************/

	
/**********************aggiorno le liste autori,categoria,editore************/
loadDoc();

/***************cambio input nel form immagini********************************/
/*quando cambio l input nel div del modale , dato che non è situato nel form, cambio valori
 * degli input presenti nel form.*/
var immCover=document.getElementById("immagineCover");
var immPersonaggio=document.getElementById("immaginePersonaggio");
var immTitolo=document.getElementById("immagineTitolo");

immCover.addEventListener('change', function() {
	  
	  let inputInterno = document.getElementById('immagineCoverInput');
	  inputInterno.files = immCover.files;

});
immPersonaggio.addEventListener('change', function() {
	  let inputInterno = document.getElementById('immaginePersonaggioInput');
	  inputInterno.files = immPersonaggio.files;

});
immTitolo.addEventListener('change', function() {
	  let inputInterno = document.getElementById('immagineTitoloInput');
	  inputInterno.files = immTitolo.files;

});


  /*******************immagini***************************************/
  var bottoni = document.querySelectorAll(".upload-button");
      for (var i = 0; i < bottoni.length; i++) {
        bottoni[i].addEventListener("change", function() {
          let immagine = document.getElementById(this.id + "img"); // chosenimg
          let reader = new FileReader();
          reader.readAsDataURL(this.files[0]); 
          var contenitoreimm= document.getElementById(this.id + "Contenitore");
          reader.onload = () => {
            immagine.setAttribute("src", reader.result);
            contenitoreimm.style.height="180px";
          }
          let titolo = document.getElementById("titoloFile" +this.id ); // fileName
          titolo.innerHTML = this.files[0].name;
        });
      }