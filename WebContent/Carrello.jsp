<!DOCTYPE html>
<%@page import="Beans.Manga_Img"%>
<html>
<head>
  <title>Carrello</title>
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Carrello.css">
  <link rel="stylesheet" href="CSS/Button-81.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="CSS/erroreOut.css">
  <script src="JS/Errore.js"></script>
  
  <script src='https://kit.fontawesome.com/5e1004225b.js' crossorigin='anonymous'></script>
<style>

  </style>
  <% 
  	List<Manga_Img> oggettiCarrello = (List<Manga_Img>)session.getAttribute("mangaCarrello");
  	//id manga, numero ancora disponibile.
  	 HashMap<Integer,Integer> numeroDisponibile=(HashMap<Integer,Integer>)session.getAttribute("arrayNumeroDisponibile");
  	Float totale=0f;
  	String errore= (String)request.getParameter("carrelloVuoto");
  	
  	
  	
  %>
</head>
  <body>

<%@include file="Header.jsp" %>
<%
if(u instanceof AmministratoreBean ){
	response.sendRedirect("ErroreServlet?errore=Carrello");
	return;
}
%>
 <%if(errore!=null && errore.equals("1")){
	out.println("<script>");
	out.println("window.onload = function() {");
	out.println("  errorDuplicate('Non ci sono manga nel carrello');"); // Chiamata alla funzione JavaScript
	out.println("};");
	out.println("</script>");
	
}
//request.setAttribute("errore", null);
%>

<div id="erroreTipo"></div>  

 
<!-----------------------------------------------------CARRELLO--------------------------------------------->
<div id="Carrello">
  <div id="headerCarrello">
    <i class="fa-solid fa-cart-shopping"></i>
    <p>Carrello</p>
    <div class="separatoreHeaderCarrello"></div>
    <p>CheckList</p>
    <div class="separatoreHeaderCarrello"></div>
    <p>Ricevuta</p>
  </div>
  <div id="tabellaCarrello">
    
<table>

  <tr>
    <th colspan="2">Prodotto</th>
    <th>Prezzo</th>
    <th>Quantità</th>
    <th>SubTotale</th>
  </tr>
 
 <% 
	if(oggettiCarrello!=null){
		for(Manga_Img carrelloSingolo : oggettiCarrello){
			
			String Copertina=null;  
			try {
	      			Copertina = new String(Base64.getEncoder().encode(carrelloSingolo.getCover()));
			}catch(NullPointerException e){
				Copertina=null;
			}
			Integer quantita=null;
			Integer idCarrello=null;
			Integer mangaId=null;
			
			for(CarrelloBean oggInCarrello :carrello){
				
				if(oggInCarrello.getManga()==carrelloSingolo.getId()){
					quantita=oggInCarrello.getQuantita();
					idCarrello=oggInCarrello.getId();
					mangaId=oggInCarrello.getManga();
				}
			}
			
			totale+=carrelloSingolo.getPrezzo()*quantita ;
			
		%>
	<tr id="rigaCarrello<%=u!=null?idCarrello:carrelloSingolo.getId()%>">
	<%     System.out.println("idCarrello = " + idCarrello + "carrellMangaPerNonUtenti = " + carrelloSingolo.getId()); %>
    	<td >
      		<a >
        		<img  onclick="ReindirizzaDettaglio(<%=carrelloSingolo.getId()%>)" src="data:image/jpeg;base64,<%= Copertina%>"  alt="Paris" >
      		</a>
    	</td>
    	<td><%=carrelloSingolo.getNome() %></td>
    	<td><%=carrelloSingolo.getPrezzo() %> &euro;</td>
    	<td><input id="quantita<%=carrelloSingolo.getId() %>" 
    		oninput="cambiaSubTotale('subtotale<%=carrelloSingolo.getId()%>',<%=carrelloSingolo.getPrezzo()%>,this,<%=mangaId %>)" 
    		type="number" min="1" value="<%=quantita%>" max="
    			<%=u!=null?(numeroDisponibile.get(carrelloSingolo.getId())+quantita):numeroDisponibile.get(carrelloSingolo.getId())%>">
    		<p id="massimoManga">/
    			<%=u!=null?(numeroDisponibile.get(carrelloSingolo.getId())+quantita):numeroDisponibile.get(carrelloSingolo.getId())%></p>
    	</td>
    	<td id="subtotale<%=carrelloSingolo.getId()%>"><%=carrelloSingolo.getPrezzo()*quantita %> &euro;</td>
    	<td id="td_cestino"><button class="button-81 cestino" id="cestino" onclick="rimuoviDalCarrello(<%=idCarrello%>,<%=carrelloSingolo.getId() %>)" role="button"><i class="fa-sharp fa-solid fa-trash"></i></button></td>
  	</tr>
		<%}
	} %>
  
   
</table>

  </div>
  <div id="procediCarrello">
    <p> </p>
    <p id="totaleCarrello"><%=String.format("%.2f", totale)%> &euro;</p>
    <button class="button-acquista" onclick="ProseguiAcquisto()" role="button"><span class="text">Prosegui</span><span>CheckList</span></button>
  </div>
</div>
<%@include file="Footer.html" %>   
<script src="JS/DettaglioMangaReindirizza.js"></script>
<script src="JS/Carrello.js"></script>
 

</body>
</html>