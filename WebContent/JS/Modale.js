/****************************modale**************************/

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
    var overlay = document.getElementById("scuro");
    overlay.remove();
  }
  
