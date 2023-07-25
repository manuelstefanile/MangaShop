<%@page import="Beans.ImmaginiMangaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.MangaBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Home</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  
  
  
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Button-81.css">
  
  <link rel="stylesheet" href="CSS/Registrazione.css">
  <link rel="stylesheet" href="CSS/AdminAggiungiManga.css">
  <link rel="stylesheet" href="CSS/erroreOut.css">
  
  
  


<style type="text/css">
 
#immagineCoverInput, #immaginePersonaggioInput,#immagineTitoloInput{
display:none;

}

</style>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
String Titolo=null;
String Cover=null;
String Personaggio=null;
MangaBean manga=null;
ImmaginiMangaBean immagine =null;

	if(!(utente instanceof AmministratoreBean)){
		response.sendRedirect("ErorrAutorizzazione.jsp");
	}
	manga=(MangaBean)request.getAttribute("manga");
	//0=copertina 1=titolo 2=personaggio 
	immagine =(ImmaginiMangaBean)request.getAttribute("immagini");
	try {
		Cover = new String(Base64.getEncoder().encode(immagine.getCover()));
		Titolo = new String(Base64.getEncoder().encode(immagine.getTitolo()));
		Personaggio= new String(Base64.getEncoder().encode(immagine.getPersonaggio()));
		
    } catch (NullPointerException e) {
     
    }
	System.out.print(manga.getDisponibilita());
	Integer errore= (Integer)request.getAttribute("errore");
    %>
  
</head>

<body>

<%@include file="Header.jsp" %>

     <%if (errore != null ) {
     	if (errore==1){%>
        <div class="errore">
        	<p>Nome manga già presente.</p>
        </div>
        
        <%} else{%>
        	<div style="background-color:green;" class="errore">
        	<p>Manga aggiornato correttamente</p>
        </div>
        	
        	
     	<%}
     } %>
        <%request.setAttribute("errore", null);
 %>
 <div id="erroreDuplicate"></div>
        <!-----------------------------------------------------------Dettaglio Manga---------------------------------------->

          <div id="contenitore" >
            <div id="titolocontenitore">
	            <p>Modifica Manga</p>
            </div>
            
            <form id="formcontenitore" name="Form_Registrazione_Manga" 
            	enctype="multipart/form-data" method="post" action="Admin2Servlet">
            
        	<input id="immagineCoverInput"  accept="image/*" type="file" name="immagineCoverInput">
        	<input id="immaginePersonaggioInput"  accept="image/*" type="file" name="immaginePersonaggioInput">
        	<input id="immagineTitoloInput"  type="file"  accept="image/*"  name="immagineTitoloInput">
        	
        	
              <div class="inputcontenitore">
                <label for="titolo">  Titolo</label>
                <input maxlength="100" placeholder="ex. Hunter x Hunter vol.3" type="text" value="<%=manga.getNome() %>" id="titolo" required name="titolo" >
              </div>
             
              <div class="inputcontenitore">
                <label for="data_uscita">  Data di uscita </i></label>
                <input type="date" id="data_uscita" value="<%=manga.getData_rilascio() %>" required name="data_uscita" >
              </div>  
              <div class="inputcontenitore widthDivPiccoli">
                <label for="quantita"> Quantita</label>
                <input min="0" step="1" required type="number" value="<%=manga.getQuantita() %>"  oninput="checkInputValue(this)" placeholder="ex. 1,2,3" id="quantita" name="quantita" >
              </div>
              <div class="inputcontenitore widthDivPiccoli">
                <label for="prezzo">  prezzo</label>
                <input type="number" step="0.01" placeholder="ex. 5,99" value="<%=manga.getPrezzo() %>" required id="prezzo" name="prezzo" >
              </div>
              <div class="inputcontenitore widthDivPiccoli">
                <label for="disponibilita">  Disponibilità</label>
                <input type="checkbox" id="disponibilita" <%if(manga.getDisponibilita()==true){ %> checked <%}%> name="disponibilita" >
              </div>
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label > Categoria
                    
                  </label>
                  <select name="categoria" id="categoria">

                  </select>
                </div>
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label >Autore
                  </label>
                  <select name="autore" id="autore">
                    
                    
                  </select>
                </div>  
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label >Editore 
                   
                  </label>
                  <select name="editore" id="editore">
                  </select>
                </div>  
                <div class="inputcontenitore">
                  <label for="rilegatura">  Rilegatura </i></label>
                  <input type="text" value="<%=manga.getRilegatura() %>" placeholder="ex. Brossurato" id="rilegatura" name="rilegatura" >
                </div>  

                <div class="inputcontenitore">
                  <label for="descrizione" > Descrizione</i></label>
                  <textarea maxlength="255" value="<%=manga.getDescrizione() %>" id="descrizione" name="descrizione"><%=manga.getDescrizione() %></textarea>
                </div>  
                <div id="aggiungiImmagini"  onclick="modalGenerale('Carta','Immagini')" onmouseover="effettoImmagini()" onmouseleave="effettoImmaginiReverse()" >
                  <a id="testoBottone">Modifica Immagini</a>
                  <div id="effettoRiduzione"></div>
                </div>

                <div class="contentBottone">
                    <button class="button-acquista" id="SalvaManga" role="submit"><span class="text">Modifica</span><span>Modifica</span></button>
                  
                 </div>
                 <input type="hidden" value=<%=manga.getId() %> name="idManga">
                </form>
                </div>


<%@include file="Modale.html" %>

<script >

/*************Effetto del bottone aggiungi immagine**********************/
function effettoImmagini(){
      document.getElementById('effettoRiduzione').style.width='0';
      document.getElementById('testoBottone').style.color="black";
      
  }
function effettoImmaginiReverse(){
    document.getElementById('effettoRiduzione').style.width='100%';
    document.getElementById('testoBottone').style.color="white";
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

</script>
<script>
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


      
 


</body>
</html>