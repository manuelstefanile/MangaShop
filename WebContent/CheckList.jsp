<%@page import="Beans.CartaCreditoBean"%>
<%@page import="Beans.IndirizzoBean"%>
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
  <title>CheckList</title>
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/CheckList.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src='https://kit.fontawesome.com/5e1004225b.js' crossorigin='anonymous'></script>
  
<style>
 
</style>
    <% 
  	List<CartaCreditoBean> listaCarteCredito =(List<CartaCreditoBean>) session.getAttribute("carteCheck");
  List<IndirizzoBean> listaIndirizzi = (List<IndirizzoBean> )session.getAttribute("indirizziCheck");
  UtenteBean utenteCheck=null;
  System.out.println(listaCarteCredito);
  %>
</head>
  <body>


 <%@include file="Header.jsp" %>
 <% if(u!=null && !(u instanceof AmministratoreBean)){
	 utenteCheck=(UtenteBean)u;

 }else {
		response.sendRedirect("ErroreServlet?errore=CheckList");
		return;
 }
 %>


<!-----------------------------------------------------Spedizione--------------------------------------------->
<form method="post" action="AcquistoServlet">
 <input type="hidden" name="acquistoFine" value="1">
<div id="spedizione">
  <div id="headerCarrello">
    <i class="fa-solid fa-cart-shopping"></i>
    <p>Carrello</p>
    <div class="separatoreHeaderCarrello"></div>
    <p>CheckList</p>
    <div class="separatoreHeaderCarrello"></div>
    <p>Ricevuta</p>
  </div>
  
    <div id="grid-container">
      <div class="grid-item item1">
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="Name" name="name" id='name' value="<%=utenteCheck.getNome() %>" required />
          <label for="name" class="form__label">Nome</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="cognome" value="<%=utenteCheck.getCognome()%>" name="cognome" id='cognome' required />
          <label for="cognome" class="form__label">Cognome</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="email" class="form__field" placeholder="email" value="<%=utenteCheck.getEmail()%>" name="email" id='email' required />
          <label for="email" class="form__label">Email</label>
        </div>
      </div>
      <div class="grid-item">
      	<select id="Indirizzi" onchange="selezionaOpzione()">
      		<%for(IndirizzoBean indirizzo:listaIndirizzi){
      			String[] arrayDiStringhe = new String[4];
      			int cap = indirizzo.getCap();
      			arrayDiStringhe[0]=indirizzo.getCitta();
      			arrayDiStringhe[1]=indirizzo.getProvincia();
      			arrayDiStringhe[2]=indirizzo.getRegione();
      			arrayDiStringhe[3]=indirizzo.getVia();
      			
      			
      			out.print("<option value='"+ arrayDiStringhe[0] +","+ arrayDiStringhe[1]+","+ arrayDiStringhe[2]+","+ arrayDiStringhe[3]+","+ cap+"'>"+indirizzo.getVia()+"</option>");
      		} %>
      	</select>
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="regione" name="regione" id='regione' required />
          <label for="regione" class="form__label">Regione</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="provincia" name="provincia" id='provincia' required />
          <label for="provincia" class="form__label">Provincia</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="citta" name="citta" id='citta' required />
          <label for="citta" class="form__label">Città</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" class="form__field" placeholder="indirizzo" name="indirizzo" id='indirizzo' required />
          <label for="indirizzo" class="form__label">Indirizzo</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" maxlength="5" class="form__field" placeholder="cap" name="cap" id='cap' required />
          <label for="cap" class="form__label">Cap</label>
        </div>
      </div>
      <div class="grid-item">
        <div class="form__group field">
          <input type="text" maxlength="10" class="form__field"  placeholder="telefono" value="<%=utenteCheck.getTelefono()%>" name="telefono" id='telefono' required />
          <label for="telefono" class="form__label">Telefono</label>
        </div>
      </div>
    </div>
  
</div>
<!-------------------------------------->
<div id="contenitoreCarta">
  <div id="cartaFronte">
    <div class="item item1">
    <p>CARTA CREDITO
    	<select id="Carte" onchange="selezionaOpzioneCarte()">
      		<%for(CartaCreditoBean carta:listaCarteCredito){
 
      			
      			out.print("<option value='" + carta.getCodice() +","+ carta.getCvc()+","+ carta.getNome()+","+ carta.getData_scadenza()+"'>"+carta.getCodice()+"</option>");
      		} %>
      	</select>
    </p>

    </div>
    <div class="item item2 ">
      
      <label for="name" class="labelCarta">Numero carta</label>
      <br>
      <input type="text"  placeholder="1234 2334 2334 6567" maxlength="16" name="numeroCarta" id='numeroCarta' required />
    </div>
    <div class="item item3">
      <label for="name" class="labelCarta">Nome</label>
      <br>
      <input type="text" placeholder="Nome e cognome" name="nomeCarta" id='nomeCarta' required />
    </div> 
    <div class="item item4">
      <label for="name" class="labelCarta">Mese</label>
      <br>
      <input type="month" placeholder="Scadenza" name="meseCarta" id='meseCarta' required />
    </div>

  </div>
  <div id="cartaRetro">
    <div id="rigaCarta"></div>
    <div id="cvcCarta">
      <label for="cvc" >cvc</label>
      <br>
      <input type="text"  maxlength="3"  placeholder="098" name="cvc" id='cvc' required />
    </div>
  </div>
</div> 


<div class="contentBottone">
  <button class="button-acquista" type="submit" role="button"><span class="text">Acquista</span><span>Acquista</span></button>
</div>
  
</form>
 
 
<%@include file="Footer.html" %>   
</body>

<script src="JS/CheckList.js"></script>  


</html>