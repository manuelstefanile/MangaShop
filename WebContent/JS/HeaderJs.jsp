<script type="text/javascript">
var amministratore
	
function UtenteAdmin(booleano){
	amministratore= booleano;
}
function barraSearch(){
	  var larghezzaSchermo = window.innerWidth;
	  const element = document.querySelector('.search');
	  <% if(u==null ){%>
	  
	 		 if(larghezzaSchermo>800){
		  		
				  element.style.right = '183px';
	  		}else
	  			element.style.right = '0px';
	  <%}%>
	  <% if(u instanceof UtenteBean || u instanceof AmministratoreBean){%>
	 		 if(larghezzaSchermo>800){
		  		
				  element.style.right = '271px';
	  		}else
	  			element.style.right = '0px';
	  <%}%>
	  
	  
	  	  
	  }
	 
barraSearch();
window.addEventListener('resize', barraSearch);

function showResult(str){
	if (str.length==0) {
		  let overlay = document.getElementById("scuroS");
		  if(overlay!=null)
		    	overlay.remove();
		 document.getElementById("searchManga").innerHTML="";	
	    return;
	  }
	else{
		let overlay = document.getElementById("scuroS");
		if(overlay==null){
			let overlay = document.createElement("div");
	    	overlay.setAttribute("id", "scuroS");
	    	overlay.className = "scuro";
	    	document.body.appendChild(overlay);
		}
	    
	  var xmlhttp=new XMLHttpRequest();
	  xmlhttp.onreadystatechange=function() {
	    if (this.readyState==4 && this.status==200) {
	    	document.getElementById("searchManga").innerHTML ="";
	    		var jsonRisposta=this.responseText;
	    		var mangaList = JSON.parse(jsonRisposta);
	    		mangaList.forEach(function(manga) {
	    	    var title = manga.nome;
				console.log(title);	
					 let titolo = document.createElement("p");
					 titolo.id="titolo"+manga.id;
					 titolo.className="testoSearchManga";
					 titolo.innerHTML=title;
					 
					 let immagine=document.createElement("img");
					 immagine.id="immagine"+manga.id;
					 immagine.className="immagineSize";
					 
					 immagine.onclick= function() {
						 	if(amministratore)				
							 	ReindirizzaDettaglio(manga.id,"Admin2Servlet","buttonId");
						 	else
						 		ReindirizzaDettaglio(manga.id);
						 
					 };
					 	
					 immagine.src="data:image/jpeg;base64,"+manga.cover;
					 
					 
				 	var newDiv = document.createElement("div");
				 	newDiv.className = "contenitoreSearch";
				 	newDiv.id="divManga"+manga.id;
				 

				 	newDiv.appendChild(immagine);
				 	newDiv.appendChild(titolo);
			        
			        // Aggiungi il nuovo div al div con ID "test"
			        document.getElementById("searchManga").appendChild(newDiv);
				
	    	
				
	    	});
	      
	    }
	  }
	  xmlhttp.open("GET","SearchServlet?parola="+str,true);
	  xmlhttp.send();
	}
}

document.addEventListener("click", function(event) {
	  var searchv = document.getElementById("searchManga");
	  
	  
	  if (!searchv.contains(event.target)) {
		  searchv.innerHTML = "";  
		  let overlay = document.getElementById("scuroS");
		  if(overlay!=null)
		   	overlay.remove();
	  }
	});


</script>