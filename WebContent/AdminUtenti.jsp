<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<%@ page import="Beans.General" %>
<%@ page import="Beans.AutoreBean" %>
<%@ page import="Beans.CategoriaBean" %>
<%@ page import="Beans.UtenteBean" %>
<%@ page import="Beans.EditoreBean" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.stream.Collectors" %>

<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Admin Utenti</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  

    <link rel="stylesheet" href="CSS/AdminACE.css">
 

  


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
.cursore{
	cursor:pointer;
}


</style>

</head>

<body>
<% %>
<%@include file="Header.jsp" %>
<% 

	Profilo utente = (Profilo) request.getSession().getAttribute("Profilo");
	List<UtenteBean> listaUtenti= new ArrayList<UtenteBean>();
	
	if(!(utente instanceof AmministratoreBean) || utente==null){
		response.sendRedirect("ErroreServlet?errore=AdminUtenti");
		return;
	} else {
		
		listaUtenti= (List<UtenteBean>)session.getAttribute("listaUtenti");
		
	}

%>



<!---------------------------------------------------TABLE----------------------------------------------------->
<form id="tableACE" name="tableACE" action="AdminListaUtentiServlet" method="post">
<input type="hidden" id="emailNascostaUtente" name="emailOrdini" value=0>
<div id="titolo">Utenti</div>
  <div class="tableGeneral" id="tavoloGenerale">
    <div class="rowGeneral" id="headerTable">
      
    
    <% if(listaUtenti.size()>0) { 
   
   %>
    	
    	<div class="cellGeneral">Email</div>
 
      </div>
    	
    	<% 
    	
    for(UtenteBean profiloSingolo:listaUtenti)  {
    	out.print("<div class=\"rowGeneral\" id=\"bottoniAzione\">");
    	out.print("<div class=\"cellGeneral\">");
    	out.print("<p class=\"cursore\" onclick=\"reindirizzaOrdiniEmail('"+ profiloSingolo.getEmail()+"')\" name='"+ profiloSingolo.getEmail()+"'>"+profiloSingolo.getEmail()+"</p></div>");
    	
    	
    	
    	
    	out.print("</div>");
    	
    	
    		} 
    	}else {
    		out.print("<div class=\"rowGeneral\" id=\"rigaVuota\" style=\"height:30px\">");
        	out.print("");
        	out.print("</div>");
    		
    	}
    %>
    
</div>
  
</form>
 
<script src="JS/AdminUtenti.js"></script>

</body>
</html>