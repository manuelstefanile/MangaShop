<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.General" %>
<%@ page import="Beans.AutoreBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="Beans.EditoreBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Admin home</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  
  
  
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/TitoloCategoria.css">
  <link rel="stylesheet" href="CSS/AdminHome.css">


<style type="text/css">

 
  


</style>
  
</head>
<%  Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
	
	if(!(utente instanceof AmministratoreBean)){
		response.sendRedirect("ErroreServlet?errore=AdminHome");
		return;
	}
	List<CategoriaBean> categorie=(List<CategoriaBean>)session.getAttribute("listaCategorie");
	List<EditoreBean> editori=(List<EditoreBean>)session.getAttribute("listaEditori");
	List<AutoreBean> autori=(List<AutoreBean>)session.getAttribute("listaAutori");

    %>
<body>

<%@include file="Header.jsp" %>


	


  <!---------------------------------------------------HOME ADMIN----------------------------------------------------->
<div id="homeAdmin">

  
  <div class="contentBottone">
    <button class="button-acquista" onclick="redirectToLista('all',0);" role="button"><span class="text">Mostra tutti manga</span><span>All manga</span></button>
 </div>
   
 <div class="contentBottone">
  <button class="button-acquista" onclick="redirectToLista('disponibilita',1);" role="button"><span class="text">Mostra manga disponibili</span><span>All manga</span></button>
</div>
<div class="contentBottone">
  <button class="button-acquista" onclick="redirectToLista('disponibilita',0);"role="button"><span class="text">Mostra manga NON disponibili</span><span>All manga</span></button>
</div>
 <div class="contentBottone">
  <button class="button-acquista"  onclick="apriSubMenu('Categoria')" role="button"><span class="text">Autori manga
    <i class="fa-solid fa-caret-down"></i>
  </span><span>Autori</span></button>
  <div class="contenitoreSubMenu" id="subMenuCategoria">
  <% if(autori!=null) {
	  String tipoAutore="autore";
	  for(AutoreBean autore : autori){
		  out.print("<button onclick=\"redirectToLista('" + tipoAutore + "', " + autore.getId() + ")\">" + autore.getNome() + " " + autore.getCognome() + "</button>");

	  }
  } %>

  </div>
</div>

<div class="contentBottone">
  <button class="button-acquista"  onclick="apriSubMenu('Autore')" role="button"><span class="text">Editori manga
    <i class="fa-solid fa-caret-down"></i>
  </span><span>Editori</span></button>
  <div class="contenitoreSubMenu" id="subMenuAutore">
      <% if(editori!=null) {
	  String tipoEditore="editore";
	  for(EditoreBean editore: editori){
		  out.print("<button onclick=\"redirectToLista('" + tipoEditore + "', " + editore.getId() + ")\">" + editore.getNome() +"</button>");

	  }
  } %>
  </div>
</div>

<div class="contentBottone">
  <button class="button-acquista"  onclick="apriSubMenu('Editore')" role="button"><span class="text">Categorie manga
    <i class="fa-solid fa-caret-down"></i>
  </span><span>Categorie</span></button>
  <div class="contenitoreSubMenu" id="subMenuEditore">
      <% if(categorie!=null) {
	  String tipoCategoria="categoria";
	  for(CategoriaBean categoria: categorie){
		  out.print("<button onclick=\"redirectToLista('" + tipoCategoria + "', " + categoria.getId() + ")\">" + categoria.getNome() +"</button>");

	  }
  } %>
  </div>
</div>

</div>

                
 <script src="JS/AdminHome.js"> </script>
 <script type="text/javascript">
 
 function redirectToLista(tipo,id) {
   window.location.href = 'Admin2Servlet?tipo=' + encodeURIComponent(tipo) + '&id=' + encodeURIComponent(id);
 }
</script>
 


</body>
</html>