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
<title><%=session.getAttribute("titoloPage") %></title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  

    <link rel="stylesheet" href="CSS/AdminACE.css">
    <link rel="stylesheet" href="CSS/erroreOut.css">
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Button-81.css">
  <link rel="stylesheet" href="CSS/ModaleConferma.css">
  
  <script src="JS/Errore.js"></script>

  


<style type="text/css">

/*cose vecchie*/
.button-acquista{
  margin-left: 0;
    border-radius: 0;
    
}
.button-acquista:after{
  width: 120%;
  height: 200%;
  bottom: -175%;
}
  .contentBottone{
    display: inline-grid;
    width: 50%;
  }
  .search{
    position: relative;
    right: 0;
  }

  .divNav:nth-child(5) {
    right: 214px;
  }
  .overlay-content {
    margin-top: 85px;
  }

  .button-acquista:after {
    background-color: cornflowerblue;
  }

.button-81{
  width: 80%;
  padding: 5px;
}
 
  
#tableACE{
text-align: -webkit-center;
}


</style>

</head>

<body>
<% %>
<%@include file="Header.jsp" %>
<% 
List<AutoreBean> autori =null;
List<CategoriaBean> categorie =null;
List<EditoreBean> editori=null; 
String modulo=null;
String errore=null;
Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");

if(!(utente instanceof AmministratoreBean)){
	response.sendRedirect("ErroreServlet?errore=Adminace");
	return;
} else {
errore= (String)request.getParameter("errore");

List generale = (List<General>)session.getAttribute("lista");
session.removeAttribute("lista");



autori = generale.get(0) instanceof AutoreBean ? (List<AutoreBean>)generale: null ;
categorie = generale.get(0) instanceof CategoriaBean ? (List<CategoriaBean>)generale: null ;
editori = generale.get(0) instanceof EditoreBean ? (List<EditoreBean>)generale: null ;
}

%>

<%if(errore!=null && errore.equals("1")){
	out.println("<script>");
	out.println("window.onload = function() {");
	out.println("  errorDuplicate('Nome già presente');"); // Chiamata alla funzione JavaScript
	out.println("};");
	out.println("</script>");
	
}else if (errore!=null && errore.equals("0")) {
	out.println("<script>");
	out.println("window.onload = function() {");
	out.println("  Successo('Aggiornamento avvenuto con successo')"); // Chiamata alla funzione JavaScript
	out.println("};");
	out.println("</script>");
	
} 
request.setAttribute("errore", null);
%>
<div id="erroreTipo"></div>

<!---------------------------------------------------TABLE----------------------------------------------------->
<form id="tableACE" name="tableACE" action="AdminServlet" method="post">
<div id="titolo"><%=session.getAttribute("titoloPage") %></div>
  <div class="tableGeneral" id="tavoloGenerale">
    <div class="rowGeneral" id="headerTable">
      
    
    <% if(autori!=null) { 
    modulo="Autore";%>
    	
    	<div class="cellGeneral">Nome</div>
        <div class="cellGeneral">Cognome</div>
        <div class="cellGeneral" >Azione</div>
      </div>
    	
    	<% 
    	if(autori.get(0).getId()!=-1){
    for(AutoreBean autore :autori)  {
    	out.print("<div class=\"rowGeneral\" id=\"bottoniAzione\">");
    	out.print("<div class=\"cellGeneral\">");
    	out.print("<input type='text' maxlenght='20' class='nomeAutore'   name=\"nomeAutore"+autore.getId()+"\" value=\"" + autore.getNome() + "\" ></div> ");
    	
    	out.print("<div class=\"cellGeneral\">");
    	out.print("<input maxlength='30' type='text' class='cognomeAutore'  name=\"cognomeAutore"+autore.getId()+"\" value=\"" + autore.getCognome() + "\" ></div> ");
    	
    	out.print("<div class=\"cellGeneral\" id=\"bottoniAzione\">");
		out.print("<button class=\"button-81 cestino\" type=\"button\" role=\"button\"    onclick=\"modalConfermaMostra('AdminServlet',"+ autore.getId() +",\'"+ autore.getCognome() +"\','autore')\"  >");
		out.print("<i class=\"fa-sharp fa-solid fa-trash\"></i></button>");
		out.print("<button class=\"button-81\" type=\"button\" onclick=\"salvaModifica('AdminServlet',"+ autore.getId() +",'autore',controlloAutore(document.getElementsByName('nomeAutore"+autore.getId()+"')[0],document.getElementsByName('cognomeAutore"+autore.getId()+"')[0]))\"   role=\"button\">Salva</button>");
    	out.print("</div></div>");
    	
    	
    		} 
    	}else {
    		out.print("<div class=\"rowGeneral\" id=\"rigaVuota\" style=\"height:30px\">");
        	out.print("");
        	out.print("</div>");
    		
    	}
    }
    else if(editori!=null) { 
        modulo="Editore";%>
	
	<div class="cellGeneral">Casa editrice</div>
    <div class="cellGeneral" >Azione</div>
  	</div>
	
	<%  
    	
 	if(editori.get(0).getId()!=-1){
        for(EditoreBean editore:editori)  {
        	out.print("<div class=\"rowGeneral\" id=\"bottoniAzione\">");
        		out.print("<div class=\"cellGeneral\">");
        		out.print("<input type='text' class='nomeEditore' name=\"nomeEditore"+editore.getId()+"\" value=\"" + editore.getNome() + "\" ></input></div> ");
        	
        		out.print("<div class=\"cellGeneral\" id=\"bottoniAzione\">");
        			out.print("<button class=\"button-81 cestino\" type=\"button\" role=\"button\"    onclick=\"modalConfermaMostra('AdminServlet',"+ editore.getId() +",\'"+ editore.getNome() +"\','editore')\"  >");
        				out.print("<i class=\"fa-sharp fa-solid fa-trash\"></i></button>");
        				out.print("<button class=\"button-81\" type=\"button\" onclick=\"salvaModifica('AdminServlet',"+ editore.getId() +",'editore',controlloEditore(document.getElementsByName('nomeEditore"+editore.getId()+"')[0]))\"   role=\"button\">Salva</button>");
        		out.print("</div></div>");
        	
        	
        } }else{
        	out.print("<div class=\"rowGeneral\" id=\"rigaVuota\" style=\"height:30px\">");
        	out.print("");
        	out.print("</div>");
        }
        }
    else if(categorie!=null) {
    	    modulo="Categoria";%>
	
	<div class="cellGeneral">Nome</div>
    <div class="cellGeneral">Descrizione</div>
    <div class="cellGeneral" >Azione</div>
    </div>
  	
	
	<% 
	if(categorie.get(0).getId()!=-1){
        for(CategoriaBean categoria: categorie)  {
        	out.print("<div class=\"rowGeneral\" id=\"bottoniAzione\">");
        	out.print("<div class=\"cellGeneral\">");
        	out.print("<input type='text' class='nomeCategoria' name=\"nomeCategoria"+categoria.getId()+"\"  value=\"" + categoria.getNome() + "\" ></div> ");
        	
        	out.print("<div class=\"cellGeneral\">");
        	out.print("<textarea rows=\"4\" type='text' class='descrizioneCategoria' name=\"descrizioneCategoria"+categoria.getId()+"\">"+categoria.getDescrizione()+"</textarea></div> ");
        	
        	out.print("<div class=\"cellGeneral\" id=\"bottoniAzione\">");
        	out.print("<button class=\"button-81 cestino\" type=\"button\" role=\"button\"    onclick=\"modalConfermaMostra('AdminServlet',"+ categoria.getId() +",\'"+ categoria.getNome() +"\','categoria')\"  >");
    		out.print("<i class=\"fa-sharp fa-solid fa-trash\"></i></button>");
        	out.print("<button class=\"button-81\" type=\"button\" onclick=\"salvaModifica('AdminServlet',"+ categoria.getId() +",'categoria',controlloCategoria(document.getElementsByName('nomeCategoria"+categoria.getId()+"')[0],document.getElementsByName('descrizioneCategoria"+categoria.getId()+"')[0]))\" role=\"button\">Salva</button>");
        	out.print("</div></div>");
        	
        	
        }}else{
        	out.print("<div class=\"rowGeneral\" id=\"rigaVuota\" style=\"height:30px\">");
        	out.print("");
        	out.print("</div>");
        } 
	}
        
        
    
    %>

</div>
  <button class="button-acquista bottoneAggiungi" type="button" onclick='modalGenerale("<%= modulo %>","<%= modulo %>")' role="button">
    <span class="text">Aggiungi</span>
    <span>Aggiungi</span>
  </button>
 
</form>
<%@include file="ModaleConferma.html" %>   
<%@include file="Modale.html" %>   
       
 <script src="JS/ModaleConferma.js"></script>
 <script src="JS/AdminACE.js"></script>
 
 <script type="text/javascript">
 var userAgent = navigator.userAgent;
 if (userAgent.indexOf("Firefox") !== -1) {
	    $("#tableACE").css("text-align", "-moz-center");
	} 
 if (performance.navigation.type === 1) {
	    // Reload the page only when the user manually refreshes the page
	    window.location.href = window.location.pathname;
	}

 </script>


</body>
</html>