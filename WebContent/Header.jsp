<!DOCTYPE html>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.AmministratoreBean" %>
<%@ page import="Beans.UtenteBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="Beans.WishlistBean" %>
<%@ page import="Beans.CarrelloBean" %>
<%@ page import="java.util.*" %>
<html>

<head>
  <script src='https://kit.fontawesome.com/5e1004225b.js' ></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta charset="UTF-8">
  <link href="https://fonts.googleapis.com/css?family=Raleway:900" rel="stylesheet">
  <link rel="stylesheet" href="CSS/Footer.css">
  <link rel="stylesheet" href="CSS/Header.css">
  

  
  <%! Profilo u=null;%>
  
  <%  
  
  
  try {
	u=(Profilo) request.getSession().getAttribute("Profilo");  
  }catch(Exception e){
	  u=null;
  }

  List<CategoriaBean> categorieHeader=(List<CategoriaBean>)session.getAttribute("listaCategorie");
  List<CarrelloBean> carrello= (List<CarrelloBean>)session.getAttribute("carrello");
  if(carrello==null){
	  carrello=new ArrayList<CarrelloBean>();
	  session.setAttribute("carrello", carrello);
  }
  boolean amministratore = u instanceof AmministratoreBean;
  
  //WishlistBean wish= (WishlistBean)session.getAttribute("wishlist");
    %>


  
</head>

<body  onload="UtenteAdmin(<%=amministratore%>)">

<!---------------------------------------------NAVBAR HEADER--------------------------------------------->
<div id="myNav" class="overlay submenu-test">
  <a class="closebtn" onclick="closeNav('submenu-test')" id="x"><i class="fa-sharp fa-solid fa-xmark"></i></a>
</div>
   <!-------------------------------------------------------------------------------------------------------------->

   <div class="flexbox">
   <% if(u instanceof AmministratoreBean){ %>
    	<div class="divNav"><a href="AdminHome.jsp">Home</a></div>
    	<div class="divNav"><a href="AdminAggiungiManga.jsp">Aggiungi Manga</a></div>
    	<div class="divNav"><a href="Editori.jsp">Editori</a></div>
    	<div class="divNav" style="right:80px;"><a href="Categorie.jsp">Categoria</a></div>
    	<div class="divNav"><a href="Autori.jsp">Autori</a></div>

    <%}else {%>
        <div class="divNav"><a href="HomePage.jsp" >Home</a></div>
    	<div onclick="redirectToLista('novita','0')" class="divNav"><a>Novità</a></div>
    	
    	<div class="divNav">
    		<a class="carro" href="CarrelloServlet?carrello=true">Carrello</a>
    		<% if(carrello!=null && carrello.size()>0){ %>
    		<div class="notifica"><%=carrello.size() %></div>
    		<% }%>
    		</div>
    	
    	<%if (u instanceof UtenteBean ) { %>
    	<div class="divNav"><a href="LogoutServlet">Logout</a></div>
    	<div class="divNav"><a href="OrdiniServlet">Ordini</a></div>
    	
    	<%}else { %>
    	
    	<div class="divNav"><a href="Login.jsp">Login</a></div>
    	
    	<%} %>

    	
    <%} %>
    
    
   
    <div id="logoContenitore" 
    	<%if(u instanceof AmministratoreBean){%>    onclick="VaiHome('Admin')"<%}else{ %>onclick="VaiHome('Utente')"<%} %>

    >
      <p id="logoTitolo">MangaShop.it <div id="logoCerchio"> </div> </p>
      
    </div>
    <div id="myNav" class="overlay headerMenu">
      <a class="closebtn" onclick="closeNav('headerMenu')" id="x"><i class="fa-sharp fa-solid fa-xmark"></i></a>
      <div class="overlay-content">
        <% if(u instanceof AmministratoreBean){ %>
                
                <a href="LogoutServlet">Logout</a>
                <a href="AdminListaUtentiServlet?utenti=1">Utenti</a>
        <%}else{
        	if(u instanceof UtenteBean){	
        	%>
        	
        	<% } %>
        
        
        <div class="menuDropdown" id="drop" onclick="apriSottoMenu()">
          <a>Categoria <i class="fa-sharp fa-solid fa-caret-down"></i></a>
         	<% if(categorieHeader!=null) {
	  			String tipoCategoria="categoria";
	  			for(CategoriaBean categoria: categorieHeader){
	  				
	  				out.print("<div onclick=\"redirectToLista('" + tipoCategoria + "', " + categoria.getId() + ")\" class=\"menuDropdown-content\" <p>"+categoria.getNome()+ " </p></div>");

				  }
  				} %>
  	
        </div>
        <%if(u instanceof UtenteBean){%>
        	<a href="ProfiloPage.jsp">Profilo</a>

        <% }else if(u==null){%>
        	<a href="Registrazione.jsp">Registrazione</a>
        <% } %>
		
        
        
        <%} %>
        
         <% if(u instanceof AmministratoreBean){ %>
        	<a href="AdminHome.jsp" id="oggNavRespons1">Home</a>
        	<a href="AdminAggiungiManga.jsp" id="oggNavRespons2" >Aggiungi Manga</a>
        	<a href="Editori.jsp" id="oggNavRespons3" >Editori</a>
        	<a href="Categorie.jsp" id="oggNavRespons4" >Categorie</a>
        	<a href="Autori.jsp" id="oggNavRespons5" >Autori</a>
        <%}else{ %>
        		<a href="#" id="oggNavRespons1">Novita</a>
        		<a href="HomePage.jsp" id="oggNavRespons2" >Home</a>
        		<a href="CarrelloServlet?carrello=true" id="oggNavRespons5" >Carrello
        			<% if(carrello!=null && carrello.size()!=0){ %>
    					<div style="left:130px; top: 277px;" class="notifica"><%=carrello.size() %></div>
    				<% }%>
        			
        		</a>
        		<%if (!(u instanceof UtenteBean)){%>
        			<a href="Login.jsp" id="oggNavRespons3" >Login</a>
        			
        		<% }else { %>
        		<a href="LogoutServlet" id="oggNavRespons3" >Logout</a>
        		<a href="OrdiniServlet" id="oggNavRespons4" >Ordini</a>
        		
        	<% }%>

        <%} %>

      </div>
    </div>
    
    <div class="search">
    
      <div>
        <input type="text"  onkeyup="showResult(this.value)" placeholder="Search . . ." required>
      </div>

    </div>
   <div id="searchManga">
 	
   </div>
  </div>
    <!---------------------------------------------------MENU LATERALE----------------------------------------------------->
        <span id="menuLaterale" style="font-size:30px;cursor:pointer" onclick="openNav('headerMenu')"><i
            class="fa-sharp fa-solid fa-list"></i></span>
            
 <script src="JS/Header.js"></script>
 <script src="JS/RedirectCategoria.js"></script>
  <script src="JS/DettaglioMangaReindirizza.js"></script>  
<%@include file="JS/HeaderJs.jsp" %>
<script type="text/javascript">
function VaiHome(luogoHome){
	if(luogoHome=="Admin")
		window.location.href = "AdminHome.jsp";
	else
		window.location.href = "Home.jsp";
}
</script> 
</body>

</html>