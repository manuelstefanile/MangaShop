
function modalConfermaMostra(servletf,idf,nome_cognomef,tipof){
	 /*scuro*/
	  var overlay = document.createElement("div");
	  overlay.setAttribute("id", "scuro");
	  document.body.appendChild(overlay);
	  console.log(tipof,idf);
	  
	  document.getElementById("modalConferma").style.display="block";
	  document.getElementById("testoConferma").innerHTML="Sicuro di voler eliminare \"" + nome_cognomef +"\" ?";
	  var form = document.getElementById("formConferma");
	  form.action=servletf;
	  document.getElementById("tipoConferma").value= tipof;
	  console.log(document.getElementById("tipoConferma").value);
	  
	  document.getElementById("inputConferma").value= idf;


}
function chiudiModaleConferma(){
		    document.getElementById("modalConferma").style.display="none";
		    var overlay = document.getElementById("scuro");
		    overlay.remove();
}
