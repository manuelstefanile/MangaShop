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
<title>Home</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <link rel="stylesheet" href="CSS/Bottone.css">

  <link rel="stylesheet" href="CSS/Carte.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/Categoria.css">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  


<style type="text/css">
 
#carteCategoria{
  flex-basis: 100%;
}
.button-acquista{

    margin-left: 0;
    border-radius: 0;
}

.button-acquista::after{
  background-color: cornflowerblue;
}


</style>

  
</head>

<body>
<%
Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
List<ImmaginiMangaBean> immaginiManga=null;
String titolo =null;
String descrizione =null;
List<MangaBean> listaManga=null;
if(!(utente instanceof AmministratoreBean)){
	response.sendRedirect("ErorrAutorizzazione.jsp");
} else {
listaManga= (List<MangaBean>)session.getAttribute("lista");
	immaginiManga= (List<ImmaginiMangaBean>)session.getAttribute("immagini");
	titolo =(String)session.getAttribute("titolo");
	descrizione =(String)session.getAttribute("descrizione");

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


  <!---------------------------------------------ORDINA CATEGORIA---------------------->
  <div id="ordinaCategoria">
    <select id="ordina" onclick="ordina" name="ordina">
      <option value="Prezzo">Prezzo</option>
      <option value="Nome" >Nome</option>

    </select>
   
  </div>

  <div id="carteCategoria">
    <!-------------------------------------------------------------Carte-------------------------->
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
      <img src="data:image/jpeg;base64,<%=Copertina %>"  class="cover-image" />
      <figcaption>
        <h3><img src="data:image/jpeg;base64,<%=Titolo %>"  class="title" /></h3>
      </figcaption>
      <div class="prezzo">
        <p class="titoloManga"><%=manga.getNome() %></p>
        <p class="prezzoManga"><%=manga.getPrezzo() %></p>
        <form action="Admin2Servlet" method="post" >
        <button class="button-acquista" value="<%=manga.getId() %>" name="buttonId" type="submit" role="button"><span class="text">Modifica</span><span>Modifica</span></button>
        </form>
      </div>
    </figure>
    
    <% 	}	
    } %>


  </div>
  <!-------------------------------------------Paginazione-->
  <div id="paginazioneCategoria"><p>pippo</p></div>
</div>
        





<script>
document.getElementById("ordina").addEventListener("change", function() {
	  var ordine = this.value; // Ottieni il valore selezionato dall'elemento select
	  

	  var listaManga = [
	    <% if (listaManga != null) {
	         for (MangaBean manga : listaManga) {
	        	 
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
	             {
	                Copertina: "<%= Copertina %>",
	                Titolo: "<%= Titolo %>",
	                Nome: "<%= manga.getNome() %>",
	                Prezzo: <%= manga.getPrezzo() %>
	             },
	             <% 
	         }
	     }
	     %>
	  ];
	  

	  // Ordina la lista in base al tipo selezionato
	  if (ordine === "Prezzo") {
	    listaManga.sort(function(a, b) {
	      return a.prezzo - b.prezzo;
	    });
	  } else if (ordine === "Nome") {
	    listaManga.sort(function(a, b) {
	      return a.Nome.localeCompare(b.Nome);
	    });
	  } 

	  // Aggiorna il contenuto del div con id "carteCategoria" con la lista ordinata
	  var carteCategoriaDiv = document.getElementById("carteCategoria");
	  carteCategoriaDiv.innerHTML = "";

	  listaManga.forEach(function(manga) {
	    // Genera il codice HTML per la singola carta di manga e aggiungilo al div "carteCategoria"
	    var cardHtml = generateMangaCardHtml(manga);
	    carteCategoriaDiv.innerHTML += cardHtml;
	  });
	});

function generateMangaCardHtml(manga) {
	  var Copertina = manga.Copertina; // Assumi che la proprietà Copertina contenga il valore corretto
	  var Titolo = manga.Titolo; // Assumi che la proprietà Titolo contenga il valore corretto
	  var Nome = manga.Nome; // Assumi che la proprietà Nome contenga il valore corretto
	  var Prezzo = manga.Prezzo; // Assumi che la proprietà Prezzo contenga il valore corretto

	  var cardHtml = "";
	  cardHtml += '<figure class="snip1571">';
	  cardHtml += '<img src="data:image/jpeg;base64,' + Copertina + '" class="cover-image" />';
	  cardHtml += '<figcaption>';
	  cardHtml += '<h3><img src="data:image/jpeg;base64,' + Titolo + '" class="title" /></h3>';
	  cardHtml += '</figcaption>';
	  cardHtml += '<div class="prezzo">';
	  cardHtml += '<p class="titoloManga">' + Nome + '</p>';
	  cardHtml += '<p class="prezzoManga">' + Prezzo + '</p>';
	  cardHtml += '<button class="button-acquista" role="button"><span class="text">Modifica</span><span>Modifica</span></button>';
	  cardHtml += '</div></figure>';

	  return cardHtml;
	}

</script>




      
 


</body>
</html>