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
<title>Admin Dettaglio manga</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  
  
  
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Button-81.css">
  
  <link rel="stylesheet" href="CSS/Registrazione.css">
  <link rel="stylesheet" href="CSS/AdminAggiungiManga.css">
  <link rel="stylesheet" href="CSS/erroreOut.css">
  <link rel="stylesheet" href="CSS/AdminMangaDettaglio.css">
  
  
  


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
		response.sendRedirect("ErroreServlet?errore=AdminDettaglio");
		return;
	}
	manga=(MangaBean)session.getAttribute("manga");
	//0=copertina 1=titolo 2=personaggio 
	immagine =(ImmaginiMangaBean)session.getAttribute("immagini");
	try {
		Cover = new String(Base64.getEncoder().encode(immagine.getCover()));
		Titolo = new String(Base64.getEncoder().encode(immagine.getTitolo()));
		Personaggio= new String(Base64.getEncoder().encode(immagine.getPersonaggio()));
		
    } catch (NullPointerException e) {
     
    }
	
	Integer errore=null;
	try{
		errore=Integer.parseInt(request.getParameter("errore"));
		}
	catch(Exception e ){};
	System.out.println(errore);
	
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
                <input  required type="text" value="<%=manga.getQuantita() %>"  placeholder="ex. 1,2,3" id="quantita" name="quantita" >
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
<script src="JS/ControlliMangaAdmin.js"></script>
<script >

prezzo=<%=manga.getPrezzo()%>;
quantitavar=<%=manga.getQuantita()%>;
titolo="<%=manga.getNome()%>";

</script>
<%@include file="JS/AdminMangaDettaglioJs.jsp" %>

      
 


</body>
</html>