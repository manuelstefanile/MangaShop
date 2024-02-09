<!DOCTYPE html>
<%@page import="Beans.Manga_Img"%>
<%@page import="Beans.EditoreBean"%>
<%@page import="Beans.AutoreBean"%>
<%@page import="Beans.ImmaginiMangaBean"%>
<%@page import="Beans.MangaBean"%>
<html>
<head>
  
  <script src='https://kit.fontawesome.com/5e1004225b.js' crossorigin='anonymous'></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="CSS/Carte.css">
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/Dettaglio.css">
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style> 
 
</style>
<title>Dettaglio Manga</title>
</head>
<body>
 <%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");

if(utente instanceof AmministratoreBean){
	response.sendRedirect("ErroreServlet?errore=Dettaglio");
	return;
}

 MangaBean mangaDettaglio = (MangaBean)request.getAttribute("MangaDettaglio");
 ImmaginiMangaBean immaginiDettaglio= (ImmaginiMangaBean)request.getAttribute("ImmaginiDettaglio");
 CategoriaBean categoriaDettaglio= (CategoriaBean)request.getAttribute("CategoriaDettaglio");
 AutoreBean autoreDettaglio= (AutoreBean)request.getAttribute("AutoreDettaglio");
 EditoreBean editoreDettaglio= (EditoreBean)request.getAttribute("EditoreDettaglio");
 List<Manga_Img> MangaimgDettaglio=(List<Manga_Img>) session.getAttribute("mangaNovitaDettaglio");


    %>
 
    
 <%@include file="Header.jsp" %>
      
      <!--------------------------------------------------------TESTO DETTAGLIO GRANDE----------------------------------------------->
    <div class="contenitore">

      <h1><%=mangaDettaglio.getNome() %></h1>
      <hr class="responsive">
      <hr class="separatore">
        <label >Manga rimasti: <%=mangaDettaglio.getQuantita() %> </label>
        <input id="quantita" type="number" min="1" value="1" max='<%=mangaDettaglio.getQuantita() %>'>
        <h2><%=mangaDettaglio.getPrezzo()%> &euro;</h2>
      
      <div>
      
        <button class="button-acquista" onclick="AggiungiCarrello('<%=mangaDettaglio.getId()%>',quantita())" id="bottoneAcquistaDetails" role="button"><span class="text">Acquista</span><span>Carrello</span></button>
      </div>
      <hr class="separatore">
      <h2 class="descrizione">Descrizione</h2>
      <p><%=mangaDettaglio.getDescrizione() %></p>
  <!--------------------------------------------------------CARTA COVER--------------------------------------------->
  </div>
  
    <div class="cardDetails">
      <div class="image">
        <% 
			String Personaggio = new String(Base64.getEncoder().encode(immaginiDettaglio.getCover()));
        %>
        <img src="data:image/jpeg;base64,<%=Personaggio %>" />
        <span class="frecc"><i class="fa-solid fa-angles-left"></i></span>
        <span class="frecc"><i class="fa-solid fa-angles-left"></i></span>
      </div>
      
      <div class="details">
        <div class="center">
          <h1>Dettaglio<br><span><%=autoreDettaglio.getCognome() %></span></h1>
          <p>Categoria: <%=categoriaDettaglio.getNome() %></p>
          <p>Editore: <%=editoreDettaglio.getNome() %></p>
          <p>Data pubblicazione: <%=mangaDettaglio.getData_rilascio() %></p>
        </div>
      </div>
    </div>
    

<!---------------------------------------------------CARTE----------------------------------------------------->
		  <div class="barraTitolo">
		    <span class="TitoloGrande">Novità</span>
		     
		    <span onclick="redirectToLista('novita','0')" class="vediTutto"> Vedi tutto</span>
		  </div>  
		  <div class="carte">
		  
		  <% 
		  for (Manga_Img manga : MangaimgDettaglio) {
			  Integer id=manga.getId();
			  String Copertina=null;
			  String Titolo=null;
			  String PersonaggioManga=null;
			  try {
      			Copertina = new String(Base64.getEncoder().encode(manga.getCover()));
      			Titolo = new String(Base64.getEncoder().encode(manga.getTitolo()));
      			PersonaggioManga = new String(Base64.getEncoder().encode(manga.getPersonaggio()));
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
      <img onclick="ReindirizzaDettaglio(<%=id%>)" src="data:image/jpeg;base64,<%=PersonaggioManga %>" class="character" />
      
      <div class="prezzo">
        <p class="titoloManga" onclick="ReindirizzaDettaglio(<%=id%>)"><%=manga.getNome()%></p>
        <p class="prezzoManga"><%=manga.getPrezzo()%> &euro;</p>
        <button class="button-acquista" onclick="AggiungiCarrello('<%=id %>',1)" role="button"><span class="text">Acquista</span><span>Carrello</span></button>
      </div>
    </div>
		  
		  <%}%>
		  </div>
<%@include file="Footer.html" %>   
 <script src="JS/RedirectCategoria.js"></script>   
  <script src="JS/AggiungiAlCarrello.js"></script>  
  <script src="JS/Dettaglio.js"></script>  
 

  


  
    
</body>
</html>