function quantita(){
	return document.getElementById("quantita").value;
}
 function ReindirizzaDettaglio(id){
	 var form = document.createElement("form");
	  form.setAttribute("method", "get");
	  form.setAttribute("action", "DettaglioServlet");
	  form.setAttribute("target", "hiddenFrame"); // Nome del frame o finestra nascosta

	  var parametro2 = document.createElement("input");
	  parametro2.setAttribute("type", "hidden");
	  parametro2.setAttribute("name", "id");
	  parametro2.setAttribute("value", id);
	  form.appendChild(parametro2);

	  document.body.appendChild(form);

	  form.submit();

		}
//controllo input immesso in numero
 var numeroInput = document.getElementById("quantita");

 numeroInput.addEventListener("input", function() {
	 let regex = /^[0-9]+$/;
	 let valo=parseInt(numeroInput.value);
	 let max=parseInt(numeroInput.max);

	 if(regex.test(valo)&&valo>0&&valo<=max){
 		
	   	  		  
	 }else {
	 	  console.log("valore non valido");
	 	  numeroInput.value=1;
	 	  
	 }

 });
