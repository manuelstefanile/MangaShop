/****************************modale**************************/
var cap;
var provincia;
var citta;
var regione;
var via;

var nomeTitolare;
var data;
var codice;
var cvc;
 
function modalGenerale(tipo,nome){
  /*scuro*/
  var overlay = document.createElement("div");
  overlay.setAttribute("id", "scuro");
  document.body.appendChild(overlay);
  /*indirizzo*/
  document.getElementById("modaleProfilo").style.display="block";
  var titolo=document.querySelectorAll("#modalHeader>p");
  titolo[0].innerHTML=nome;
  var carta = document.getElementsByClassName("form"+tipo);
  carta[0].style.display="none";

  /**seleziona gli altri form e setta a none */
  var forms = document.querySelectorAll('[class^="form"]');
  for (var i=0;i<forms.length;i++){
    forms[i].style.display="none";
  }
  document.getElementsByClassName("form"+tipo)[0].style.display="flex";
  const modaleProfiloBody = document.querySelector('#modaleProfiloBody');
  console.log(tipo);
  if(tipo==='CartaCredito' || tipo=='Indirizzo' || tipo=='Categoria' || tipo =='Editore' || tipo=='Autore'){
	  console.log("dentrocaarta")
	modaleProfiloBody.style.maxWidth="500px";
  } 
  modaleProfiloBody.style.minHeight = '450px';
    

  
}
/**********************************MOdale indirizzo*/
$("#cap").on("input", function (){
	var capValue = this.value.trim();
    capValue = capValue.replace(/\D/g, "");  
    this.value = capValue;
	if(this.value.length==5){
		this.style.borderColor="green";
		this.setCustomValidity("");
		cap=true;
	}
	else{
		this.setCustomValidity("Inserisci 5 numeri");
		this.style.borderColor="red";
		cap=false;
	}
});
$("#provincia").on("input", function (){
	if(this.value.length>0){
		this.style.borderColor="green";
		provincia=true;
	}
	else{
		this.style.borderColor="red";
		provincia=false;
	}
});
$("#citta").on("input", function (){
	if(this.value.length>0){
		this.style.borderColor="green";
	citta=true;}
	else{
		this.style.borderColor="red";
	citta=false;}
});
$("#regione").on("input", function (){
	if(this.value.length>0){
		this.style.borderColor="green";
	regione=true;}
	else{
		this.style.borderColor="red";
	regione=false;}
});
$("#via").on("input", function (){
	if(this.value.length>0){
		this.style.borderColor="green";
		via=true;
	}
	else{
		this.style.borderColor="red";
		via=false;
		}
});

/****************************************CONTROLLICARTA*******************************************/
$("#codice").on("input", function (){
	// 
    var codiceValue = this.value.trim();

    codiceValue = codiceValue.replace(/\D/g, "");  
    this.value = codiceValue;
	if(this.value.length==16){
		this.style.borderColor="green";
		codice=true;
	}
	else{
		this.style.borderColor="red";
		codice=false;
		}
});
$("#cvc").on("input", function (){
	// 
    var cvcValue = this.value.trim();

    cvcValue = cvcValue.replace(/\D/g, "");  
    this.value = cvcValue;
	if(this.value.length==3){
		this.style.borderColor="green";
		cvc=true;
	}
	else{
		this.style.borderColor="red";
		cvc=false;
		}
});
$("#nomeTitolare").on("input", function (){
	// 
 
 
	if(this.value.length>0){
		this.style.borderColor="green";
		nomeTitolare=true;
	}
	else{
		this.style.borderColor="red";
		nomeTitolare=false;
		}
});
$("#data").on("input", function (){
	// 
	 var inputDate = new Date(this.value).getTime();
     var currentDate = new Date().getTime();
     
     

     // Confronta la data inserita con la data attuale
     if (inputDate > currentDate) {
		this.style.borderColor="green";
		data=true;
	}
	else{
		this.style.borderColor="red";
		data=false;
		}
});
 

  /***************************************Modale per la carta di credito*********************/
  function modalCarta(){
    /*scuro*/
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "scuro");
    document.body.appendChild(overlay);
    /*indirizzo*/
    document.getElementById("modaleProfilo").style.display="block";
    var titolo=document.querySelectorAll("#modalHeader>p");
    titolo[0].innerHTML="Carta";
    var carta = document.getElementsByClassName("formIndirizzo");
    carta[0].style.display="none";
    document.getElementsByClassName("formCarta")[0].style.display="flex";
    const modaleProfiloBody = document.querySelector('#modaleProfiloBody');
      modaleProfiloBody.style.minHeight = '400px';
  }
  function chiudiModale(){
    document.getElementById("modaleProfilo").style.display="none";
    $('.formIndirizzo input').css('border-color', 'black');
    $('.formCartaCredito input').css('border-color', 'black');
    var overlay = document.getElementById("scuro");
    overlay.remove();
  }
  
