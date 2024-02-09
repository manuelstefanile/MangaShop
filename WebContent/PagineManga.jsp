<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.General" %>
<%@ page import="Beans.MangaBean" %>
<%@ page import="Beans.AutoreBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="Beans.ImmaginiMangaBean" %>
<%@ page import="Beans.EditoreBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Lista manga</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <link rel="stylesheet" href="CSS/Bottone.css">

  <link rel="stylesheet" href="CSS/Carte.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/PagineManga.css">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  
  
</head>

<body>
<%
Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
if(utente instanceof AmministratoreBean){

	response.sendRedirect("ErroreServlet?errore=PagineManga");
	return;

}
List<ImmaginiMangaBean> immaginiManga=null;
String titolo =null;
String descrizione =null;
List<MangaBean> listaManga=null;

listaManga= (List<MangaBean>)session.getAttribute("lista");
	immaginiManga= (List<ImmaginiMangaBean>)session.getAttribute("immagini");
	titolo =(String)session.getAttribute("titolo");
	descrizione =(String)session.getAttribute("descrizione");

	Enumeration<String> attributeNames = session.getAttributeNames();
	while (attributeNames.hasMoreElements()) {
        String attributeName = attributeNames.nextElement();
        //Object attributeValue = session.getAttribute(attributeName);
        System.out.println("nome = " + attributeName );
    }
	if(titolo==null){
		response.sendRedirect("ErroreServlet?errore=General");
		return;
	}
	

%>
<%@include file="Header.jsp" %>

     
 <div id="erroreDuplicate"></div>
    
    
    <!-----------------------------------------------------categoria--------------------------------------------->
<div id="categoriaP">
  <div id="titoloCategoriaP">
    <div class="barraTitolo">
      <span class="TitoloGrande"><%=titolo %></span>
      <p><% if(descrizione!=null){
    	  out.print(descrizione);
      } %>
      </p>
      
    </div>
  </div>


<!-----------------------------------------------------categoria2--------------------------------


  <div id="elencoFiltriCategoria">
    <p>pippo <i class="fa-sharp fa-light fa-x"></i>
    </p>
  </div>
  ------------->
  <!---------------------------------------------ORDINA CATEGORIA---------------------->
  <div id="ordinaCategoria">
    <select id="ordina" onclick="ordina" name="ordina">
      <option value="Prezzo">Prezzo</option>
      <option value="Nome" >Nome</option>

    </select>
    <!-- <div id="filtriCategoriaCell">
      <button onclick="openNav('submenu-test')" data-submenu="submenu-test"> premi</button>
    </div> -->
  </div>
<!----------------------------------------------------FILTRI CATEGORIA--
  <div id="filtriCategoria">
    <p>Filtri</p>
    <div class="dropdownCategoria">
      <button class="bottoneCategoria" onclick="aprisubfiltro()"  data-submenu="submenu-2">Premimi <i class="fa-solid fa-plus"></i></button>
      <ul class="elenco" id="submenu-2">
        <li><input type="checkbox">Naruto</li> 
        <li><input type="checkbox">One piece</li>
        <li><input type="checkbox">Digimon</li>
        <li><input type="checkbox">prova1</li>
      </ul>
      <button class="bottoneCategoria"  data-submenu="submenu-3">Premimi</button>
      <ul class="elenco" id="submenu-3">
        <li><input type="checkbox">prova2</li> 
        <li><input type="checkbox">prova2</li>
        <li><input type="checkbox">prova2</li>
        <li><input type="checkbox">prova2</li>
      </ul>
      <button class="bottoneCategoria"  data-submenu="submenu-4">Premimi</button>
      <ul class="elenco" id="submenu-4">
        <li><input type="checkbox">prova2</li> 
        <li><input type="checkbox">prova2</li>
        <li><input type="checkbox">prova2</li>
        <li><input type="checkbox">prova2</li>
      </ul>
    </div>
  </div>
  --->
  <div id="carteCategoria">
    <!--------------------------------prova-->
 <%if(listaManga!=null) {
    	for(MangaBean manga:listaManga) {
    		String Copertina=null;
    		String Titolo=null;
            for(ImmaginiMangaBean immagine: immaginiManga){
            	if(manga.getImmagini_manga()==immagine.getId()){
            		try {
            			Copertina = new String(Base64.getEncoder().encode(immagine.getCover()));
            			Titolo = new String(Base64.getEncoder().encode(immagine.getTitolo()));
                    } catch (NullPointerException e) {
                       immagine = null;
                    }
            	}
            }

    		
    %>

    <figure class="snip1571">
      <img onclick="ReindirizzaDettaglio(<%=manga.getId()%>)" src="data:image/jpeg;base64,<%=Copertina %>"  class="cover-image cursore" />
      <figcaption class="cursore" onclick="ReindirizzaDettaglio(<%=manga.getId()%>)">
        <h3><img src="data:image/jpeg;base64,<%=Titolo %>"  class="title" /></h3>
      </figcaption>
      <div class="prezzo">
        <p class="titoloManga"><%=manga.getNome() %></p>
        <p class="prezzoManga"><%=manga.getPrezzo() %> &euro;</p>
        
        <button class="button-acquista" onclick="AggiungiCarrello('<%=manga.getId()%>',1)" value="<%=manga.getId() %>" name="buttonId" type="submit" role="button"><span class="text">Acquista</span><span>Aggiungi carrello</span></button>
        
      </div>
    </figure>
    
    <% 	}	
    } %>


  </div>
  <!-------------------------------------------Paginazione-->
  
</div>


<%@include file="Footer.html" %>   
 <script src="JS/AggiungiAlCarrello.js"></script>  
 <script src="JS/DettaglioMangaReindirizza.js"></script>  

<%@include file="JS/OrdinaJs.jsp" %>   


      
 


</body>
</html>