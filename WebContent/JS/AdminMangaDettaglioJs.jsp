 <script>
/*************Effetto del bottone aggiungi immagine**********************/
function effettoImmagini(){
      document.getElementById('effettoRiduzione').style.width='0';
      document.getElementById('testoBottone').style.color="black";
      
  }
function effettoImmaginiReverse(){
    document.getElementById('effettoRiduzione').style.width='100%';
    document.getElementById('testoBottone').style.color="white";
  }
  



/*********************funzione chiamata ajax delle liste autore,editore,categoria al caricamento del doc***********/
function loadDoc() {
	  const xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      var risposta = JSON.parse(this.responseText);
	      var autori = risposta.autori;
	      var categorie = risposta.categorie;
	      var editori = risposta.editori;
	      var var1;
	      var var2;
	      var ACEId ; 
	      for (var i = 0; i < autori.length; i++) {
	    	ACEId = <%=manga.getAutore()%>; 
	    	let option = document.createElement("option");
	        option.innerHTML = autori[i].nome+ " " + autori[i].cognome;
 
	        if(autori[i].id===ACEId){
				var1=autori[i].nome;
				var2=autori[i].cognome;
	        }else
	        	document.getElementById("autore").appendChild(option); 
	      }
	      
	      let option = document.createElement("option");
	      option.innerHTML = var1+ " " + var2;
	      option.selected=true;
	      document.getElementById("autore").insertBefore(option, document.getElementById("autore").firstChild);
	      
	      for (var i = 0; i < categorie.length; i++) {
	    	  ACEId = <%=manga.getCategoria()%>; 
	    	  let option = document.createElement("option");
	        option.innerHTML = categorie[i].nome;
	        
	        if(categorie[i].id===ACEId){
				var1=categorie[i].nome;
				
	        }else
	        	document.getElementById("categoria").appendChild(option); 
	        
	        
	      }
	      let option2 = document.createElement("option");
	      option2.innerHTML = var1;
	      option2.selected=true;
	      document.getElementById("categoria").insertBefore(option2, document.getElementById("categoria").firstChild);
	      
	      for (var i = 0; i < editori.length; i++) {
	    	  ACEId = <%=manga.getEditore()%>;
	    	  let option = document.createElement("option");
	    	  
	        option.innerHTML = editori[i].nome;
	        
	        if(editori[i].id===ACEId){
				var1=editori[i].nome;
				
	        }else
	        	document.getElementById("editore").appendChild(option);	
	        
	         
	      }
	      let option3 = document.createElement("option");
	      option3.innerHTML = var1;
	      option3.selected=true;
	      document.getElementById("editore").insertBefore(option3, document.getElementById("editore").firstChild);
	   
	    }
	  };
	  xhttp.open("POST", "AdminServlet");
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("RichiestaAutori=1");
	}

/*************************funzione del check di validita di alcuni input********************/



function checkInputValue(input) {
	 let inputVal = parseInt(input.value);

	  // verifica se il numero intero è uguale alla stringa dell'input
	  if (inputVal.toString() !== input.value || inputVal < 0) {
	    // l'input non è un numero naturale
	    input.setCustomValidity('Inserisci solo numeri Interi Naturali ex. 1,2,3,4...');
	  } else {
	    // l'input è un numero naturale
	    input.setCustomValidity('');
	  }
	  
	}
	
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


  /*******************immagini. Mostra le immagini che si stanno cambiando***************************************/
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

 
document.getElementById("immagineCoverimg").setAttribute("src", "data:image/jpeg;base64,<%=Cover%>");
document.getElementById("immagineTitoloimg").setAttribute("src", "data:image/jpeg;base64,<%=Titolo%>");
document.getElementById("immaginePersonaggioimg").setAttribute("src", "data:image/jpeg;base64,<%=Personaggio%>");


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
	  
  input1.value = '';
  input2.value = '';
  input3.value = '';
  document.getElementById("immagineCoverInput").value='';
  document.getElementById("immaginePersonaggioInput").value='';
  document.getElementById("immagineTitoloInput").value='';
  document.getElementById("immagineCoverimg").setAttribute("src", "data:image/jpeg;base64,<%=Cover%>");
  document.getElementById("immagineTitoloimg").setAttribute("src", "data:image/jpeg;base64,<%=Titolo%>");
  document.getElementById("immaginePersonaggioimg").setAttribute("src", "data:image/jpeg;base64,<%=Personaggio%>");
 
  document.getElementById("titoloFileimmagineCover").innerHTML="";
  document.getElementById("titoloFileimmagineTitolo").innerHTML="";
  document.getElementById("titoloFileimmaginePersonaggio").innerHTML="";


  
});


if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}
</script>