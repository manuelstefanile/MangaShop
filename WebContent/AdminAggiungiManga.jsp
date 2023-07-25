<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
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
	
	if(!(utente instanceof AmministratoreBean)){
		response.sendRedirect("ErorrAutorizzazione.jsp");
	}
	String errore= request.getParameter("errore");
    %>
  
</head>

<body>

<%@include file="Header.jsp" %>

     <%if (errore != null ) {
     	if (Integer.parseInt(errore)==1){%>
        <div class="errore">
        	<p>Nome manga già presente.</p>
        </div>
        
        <%} else{%>
        	<div style="background-color:green;" class="errore">
        	<p>Manga inserito correttamente</p>
        </div>
        	
        	
     	<%}
     } %>
        <%request.setAttribute("errore", null);
        
 %>
 <div id="erroreDuplicate"></div>
        <!-----------------------------------------------------------REGISTRAZIONE Manga---------------------------------------->

          <div id="contenitore" >
            <div id="titolocontenitore">
	            <p>Aggiungi Manga</p>
            </div>
            
            <form id="formcontenitore" name="Form_Registrazione_Manga" 
            	enctype="multipart/form-data" method="post" action="AdminServlet">
            
        	<input id="immagineCoverInput" accept="image/*" type="file" name="immagineCoverInput">
        	<input id="immaginePersonaggioInput" accept="image/*" type="file" name="immaginePersonaggioInput">
        	<input id="immagineTitoloInput" type="file"  accept="image/*"  name="immagineTitoloInput">
        	
        	
              <div class="inputcontenitore">
                <label for="titolo">  Titolo</label>
                <input maxlength="100" placeholder="ex. Hunter x Hunter vol.3" type="text" id="titolo" required name="titolo" >
              </div>
             
              <div class="inputcontenitore">
                <label for="data_uscita">  Data di uscita </i></label>
                <input type="date" id="data_uscita" required name="data_uscita" >
              </div>  
              <div class="inputcontenitore widthDivPiccoli">
                <label for="quantita"> Quantita</label>
                <input min="0" step="1" required type="number"  oninput="checkInputValue(this)" placeholder="ex. 1,2,3" id="quantita" name="quantita" >
              </div>
              <div class="inputcontenitore widthDivPiccoli">
                <label for="prezzo">  prezzo</label>
                <input type="number" step="0.01" placeholder="ex. 5,99" required id="prezzo" name="prezzo" >
              </div>
              <div class="inputcontenitore widthDivPiccoli">
                <label for="disponibilita">  Disponibilità</label>
                <input type="checkbox" id="disponibilita" name="disponibilita" >
              </div>
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label > Categoria
                    <i onclick="modalGenerale('Categoria','Categoria')" class="aggiungiQualcosa fa-light fa-plus"></i>
                  </label>
                  <select name="categoria" id="categoria">
                    
                    
                  </select>
                </div>
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label >Autore
                    <i onclick="modalGenerale('Autore','Autore')" class="aggiungiQualcosa fa-light fa-plus"></i>
                  </label>
                  <select name="autore" id="autore">
                    
                    
                  </select>
                </div>  
                <div class="inputcontenitore div_select widthDivPiccoli">
                  <label >Editore 
                    <i onclick="modalGenerale('Editore','Editore')" class="aggiungiQualcosa fa-light fa-plus"></i>
                  </label>
                  <select name="editore" id="editore">
                  </select>
                </div>  
                <div class="inputcontenitore">
                  <label for="rilegatura">  Rilegatura </i></label>
                  <input type="text" placeholder="ex. Brossurato" id="rilegatura" name="rilegatura" >
                </div>  

                <div class="inputcontenitore">
                  <label for="descrizione" > Descrizione</i></label>
                  <textarea maxlength="255" id="descrizione" name="descrizione"></textarea>
                </div>  
                <div id="aggiungiImmagini"  onclick="modalGenerale('Carta','Immagini')" onmouseover="effettoImmagini()" onmouseleave="effettoImmaginiReverse()" >
                  <a id="testoBottone">Aggiungi Immagini</a>
                  <div id="effettoRiduzione"></div>
                </div>

                <div class="contentBottone">
                    <button class="button-acquista" id="SalvaManga" role="submit"><span class="text">Aggiungi</span><span>Aggiungi</span></button>
                  
                 </div>
                </form>
                </div>


<%@include file="Modale.html" %>
<script src="JS/AdminAggiungiManga.js"></script>
<script src="JS/AnnullaInputImage.js"></script>
<script>
if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}

</script>


      
 


</body>
</html>