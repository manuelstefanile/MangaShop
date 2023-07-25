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
  


<style type="text/css">

 
  


</style>

</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");


HashMap<String,List<Manga_Img>> mappalista =(HashMap<String,List<Manga_Img>>) session.getAttribute("mappalista");

    %>
<body>

<%@include file="Header.jsp" %>


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
		    <span class="vediTutto"> Vedi tutto</span>
		  </div>  
		  <div class="carte">
		  
		  <% 
		  List<Manga_Img> mangaList = mappalista.get(key);
		  for (Manga_Img manga : mangaList) {
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
			

    <div class="card">
      <div class="wrapper">
        <img src="data:image/jpeg;base64,<%=Copertina %>"class="cover-image" />
      </div>
      <img src="data:image/jpeg;base64,<%=Titolo %>"  class="title" />
      <img src="data:image/jpeg;base64,<%=Personaggio %>" class="character" />
      
      <div class="prezzo">
        <p class="titoloManga"><%=manga.getNome()%></p>
        <p class="prezzoManga"><%=manga.getPrezzo()%> euro</p>
        <button class="button-acquista" role="button"><span class="text">Acquista</span><span>Carrello</span></button>
      </div>
    </div>
    
<% 			  
			  
		  }
		  
		  
		  %></div><%  
	  }
	  
	  
  } %>

  

                
 


</body>
</html>