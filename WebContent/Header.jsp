<!DOCTYPE html>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.AmministratoreBean" %>
<%@ page import="Beans.UtenteBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="java.util.*" %>
<html>

<head>
  <script src='https://kit.fontawesome.com/5e1004225b.js' crossorigin='anonymous'></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link href="https://fonts.googleapis.com/css?family=Raleway:900" rel="stylesheet">
  
  <link rel="stylesheet" href="CSS/Header.css">
  

  <style>
  <%  Profilo u = (Profilo) request.getSession().getAttribute("Profilo");
  List<CategoriaBean> categorieHeader=(List<CategoriaBean>)session.getAttribute("listaCategorie");
    %>


  </style>
</head>

<body>

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
    	<div class="divNav"><a href="Categorie.jsp">Categoria</a></div>
    	<div class="divNav"><a href="Autori.jsp">Autori</a></div>

    <%}else {%>
        <div class="divNav"><a href="HomePage.jsp" >Home</a></div>
    	<div class="divNav"><a>Novità</a></div>
    	<%if (u instanceof UtenteBean ) { %>
    	<div class="divNav"><a href="LogoutServlet">Logout</a></div>
    	<div class="divNav"><a>Ordini</a></div>
    	<div class="divNav"><a>Carrello</a></div>
    	<%}else { %>
    	<div class="divNav"><a href="Login.jsp">Login</a></div>
    	
    	<%} %>

    	
    <%} %>
    
    
   
    <div id="logoContenitore">
      <p id="logoTitolo">MangaShop.it <div id="logoCerchio"> </div></p>
      
    </div>
    <div id="myNav" class="overlay headerMenu">
      <a class="closebtn" onclick="closeNav('headerMenu')" id="x"><i class="fa-sharp fa-solid fa-xmark"></i></a>
      <div class="overlay-content">
        <% if(u instanceof AmministratoreBean){ %>
                
                <a href="LogoutServlet">Logout</a>
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

        <% } %>

        <a href="#">Lista dei desideri</a>
        
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
        		<%if (!(u instanceof UtenteBean)){%>
        			<a href="Login.jsp" id="oggNavRespons3" >Login</a>
        		<% }else { %>
        		<a href="LogoutServlet" id="oggNavRespons3" >Logout</a>
        		<a href="#" id="oggNavRespons4" >Ordini</a>
        		<a href="#" id="oggNavRespons5" >Carrello</a>
        	<% }%>

        <%} %>

      </div>
    </div>
    <%if(u instanceof UtenteBean || u instanceof AmministratoreBean) {
    	%>
    	<div class="search"  >
    	<%
    }else {%>
    <div class="search" style="right:87px;">
    <%} %>
      <div>
        <input type="text" placeholder="Search . . ." required>
      </div>
    </div>
  </div>
    <!---------------------------------------------------MENU LATERALE----------------------------------------------------->
        <span id="menuLaterale" style="font-size:30px;cursor:pointer" onclick="openNav('headerMenu')"><i
            class="fa-sharp fa-solid fa-list"></i></span>
            
 <script src="JS/Header.js"></script>
<script>
function redirectToLista(categoria,id){
	
	var form = document.createElement("form");
	  form.setAttribute("method", "post");
	  form.setAttribute("action", "PagineMangaServlet");
	  form.setAttribute("target", "hiddenFrame"); // Nome del frame o finestra nascosta

	  // Aggiungi i parametri come campi nascosti nel modulo
	  var parametro1 = document.createElement("input");
	  parametro1.setAttribute("type", "hidden");
	  parametro1.setAttribute("name", "tipo");
	  parametro1.setAttribute("value", categoria);
	  form.appendChild(parametro1);

	  var parametro2 = document.createElement("input");
	  parametro2.setAttribute("type", "hidden");
	  parametro2.setAttribute("name", "id");
	  parametro2.setAttribute("value", id);
	  form.appendChild(parametro2);

	  document.body.appendChild(form);

	  form.submit();

		}


</script>
</body>

</html>