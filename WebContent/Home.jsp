<%@page import="Beans.WishlistBean"%>
<%@page import="Beans.CarrelloBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.General" %>
<%@ page import="Beans.MangaBean" %>
<%@ page import="Beans.AutoreBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="Beans.Manga_Img" %>
<%@ page import="Beans.ImmaginiMangaBean" %>
<%@ page import="Beans.EditoreBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Home</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  

  
  <link rel="stylesheet" href="CSS/Video.css">
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Carte.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/erroreOut.css">
  <link rel="stylesheet" href="CSS/CaricamentoAjax.css">
  <link rel="stylesheet" href="CSS/Home.css">
  
  
  <script src="JS/Errore.js"></script>


<style type="text/css">


</style>

</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");

if(utente instanceof AmministratoreBean){
	response.sendRedirect("ErroreServlet?errore=HomeUtente");
	return;
}


HashMap<String,List<Manga_Img>> mappalista =(HashMap<String,List<Manga_Img>>) session.getAttribute("mappalista");
String errore= (String)request.getParameter("errore");

    %>
<body>

<%@include file="Header.jsp" %>

 <%if(errore!=null && errore.equals("0")){
	out.println("<script>");
	out.println("window.onload = function() {");
	out.println("  Successo('Registrazione avvenuta con successo');"); // Chiamata alla funzione JavaScript
	out.println("};");
	out.println("</script>");
	
}
//request.setAttribute("errore", null);
%>

<div id="erroreTipo"></div>   
<!------------------------------------------------- CARICAMENTO AJAX---> 


     

 <!---------------------------------------------------VIDEO----------------------------------------------------->
  <div class="wrappper">
    <div class="libro"></div>
    <div class="video-container">
      <video src="Video/intro.mp4" autoplay loop muted></video>
    </div>
    <div class="test"> Il sito "Manga Store" è la destinazione online perfetta per gli amanti del manga di tutto il
      mondo. Qui potrete trovare una vasta selezione di titoli, dai classici più amati ai nuovi successi di vendita,
      tutti in formato cartaceo.
    </div>
  </div>
  
  <!---------------------------------------------------CARTE----------------------------------------------------->
  <% if(mappalista.size()!=0){
	  for (String key : mappalista.keySet()) {
		  %>

		  <div class="barraTitolo">
		    <span class="TitoloGrande"><%=key %></span>
		     
		    <span onclick="redirectToLista('categoria','<%=key%>')" class="vediTutto"> Vedi tutto</span>
		  </div>  
		  <div class="carte">
		  
		  <% 
		  List<Manga_Img> mangaList = mappalista.get(key);
		  for (Manga_Img manga : mangaList) {
			  Integer id=manga.getId();
			  String Copertina=null;
			  String Titolo=null;
			  String Personaggio=null;
			  try {
      			Copertina = new String(Base64.getEncoder().encode(manga.getCover()));
      			Titolo = new String(Base64.getEncoder().encode(manga.getTitolo()));
      			Personaggio = new String(Base64.getEncoder().encode(manga.getPersonaggio()));
              } catch (NullPointerException e) {
    			    Copertina=null;
    			    Titolo=null;
    			    Personaggio=null;
              }
			  
			%>
			

    <div class="card" >
      <div class="wrapper" onclick="ReindirizzaDettaglio(<%=id%>)">
        <img src="data:image/jpeg;base64,<%=Copertina %>"class="cover-image" />
      </div>
      <img onclick="ReindirizzaDettaglio(<%=id%>)" src="data:image/jpeg;base64,<%=Titolo %>"  class="title" />
      <img onclick="ReindirizzaDettaglio(<%=id%>)" src="data:image/jpeg;base64,<%=Personaggio %>" class="character" />
      
      <div class="prezzo">
        <p class="titoloManga" onclick="ReindirizzaDettaglio(<%=id%>)"><%=manga.getNome()%></p>
        <p class="prezzoManga"><%=manga.getPrezzo()%> &euro;</p>
        <button class="button-acquista" onclick="AggiungiCarrello('<%=id %>',1)" role="button"><span class="text">Acquista</span><span>Aggiungi Carrello</span></button>
      </div>
    </div>
    
<% 			  
			  
		  }
		  
		  
		  %></div><%  
	  }
	  
	  
  } %>

  
<%@include file="Footer.html" %>   
 <script src="JS/RedirectCategoria.js"></script>  
 <script src="JS/DettaglioMangaReindirizza.js"></script>  
 <script src="JS/AggiungiAlCarrello.js"></script>  
 <script type="text/javascript">
//Modifica solo l'URL senza ricaricare la pagina
 history.replaceState({}, "", "HomePage.jsp");

 
 </script>
 
 


</body>
</html>