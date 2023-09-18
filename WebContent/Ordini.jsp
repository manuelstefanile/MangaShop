<%@page import="Beans.ProdottoOrdineBean"%>
<%@page import="Beans.OrdineBean"%>
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
  

  
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">


  <link rel="stylesheet" href="CSS/Carte.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/Ordini.css">
  
  
  



<style type="text/css">

 
  


</style>

</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
if(utente==null || utente instanceof AmministratoreBean){
	response.sendRedirect("ErroreServlet?errore=Ordini");
	return;
}
LinkedHashMap<OrdineBean,List<ProdottoOrdineBean>> listaOrdini=(LinkedHashMap<OrdineBean,List<ProdottoOrdineBean>>) session.getAttribute("listaOrdini");
	
	

    %>
<body>

<%@include file="Header.jsp" %>

 <!-----------------------------------------------------categoria--------------------------------------------->
<div id="Ordini">
  <!-----------------------------------------------------titolo----------------------------->
  <div id="titoloOrdini">
    <div class="barraTitolo">
      <span class="TitoloGrande">ORDINI</span>
    </div>
  </div>


  <!---------------------------------------------ORDINA Anno ordine
  <div id="ordinaOrdina">
    <select id="ordina" name="ordina">
      <option value="prezzo">2023</option>
      <option value="data">2022</option>
      <option value="popolarita">2021</option>
    </select>
  </div>
     ORDINI-------------------->

  <div id="OrdiniDiv"> 
  <% for(Map.Entry<OrdineBean, List<ProdottoOrdineBean>> entry : listaOrdini.entrySet()){
	  OrdineBean ordineKey=entry.getKey();
	  List<ProdottoOrdineBean> listaP=entry.getValue();
	  //qui sono la chiave %>
	  <table>

      <tr class="backgroundBianco">
        <th>Ordine effettuato il:</th>
        <th>Totale</th>
        <th class="evidenzia">Fattura/Ricevuta</th>
      </tr>
      <tr  class="backgroundBianco fontSizeReduct">
        <th class="paddingInferiore"><%=ordineKey.getData_acquisto()%></th>
        <th class="paddingInferiore"><%=ordineKey.getTotale()%> &euro;</th>
        <th class="thvuoto"></th>
      </tr>
	<% String Background =  ordineKey.getStato().equals("in consegna")?"backgroundBianco":"backgroundVerde";   %>
      <tr class="bordoSuperiore <%=Background%> ">
        <th colspan="3"><%=ordineKey.getStato() %> <%=ordineKey.getData_consegna()!=null?ordineKey.getData_consegna():"" %></th>
      </tr>
      
          
    <%
	  for (ProdottoOrdineBean prod: listaP){
			String Copertina=null;  
			try {
	      			Copertina = new String(Base64.getEncoder().encode(prod.getCover_manga()));
			}catch(NullPointerException e){
				Copertina=null;
			}
	  
	  %>
	<tr>

        <td class="immagineOrdine">
          <a target="_blank">
            <img onclick="ReindirizzaDettaglio(<%=prod.getManga()%>)" src="data:image/jpeg;base64,<%= Copertina%>" alt="Paris" >
          </a>
    
        </td>

        <td colspan="2">
        	<p onclick="ReindirizzaDettaglio(<%=prod.getManga()%>)" class="evidenzia"><%=prod.getTitolo_manga() %></p>
        	<p >Prezzo  <%=prod.getPrezzo_acquisto() %> &euro;</p>
        	<p >Quantità  <%=prod.getQuantita() %></p>
        
        </td>

	</tr>


	
	  
  <% }%>
    
    
    <tr class="colorWhite">
      <th >Indirizzo di spedizione:</th>
      <th colspan="2">Modalità di pagamento:</th>
      <th class="thvuoto" ></th>
    </tr>
    <tr class="colorWhite fontSizeReduct">
      <th id="indirizzoValore"><%=ordineKey.getIndirizzo() %></th>
      <th id="cartaValore" colspan="2"><%=ordineKey.getCarta() %></th>
    </tr>
    </table>
    <% 
  } %>
    
   
   
  </div>
  
  
</div>
<%@include file="Footer.html" %>   
   <script src="JS/DettaglioMangaReindirizza.js"></script>
   
</body>
</html>