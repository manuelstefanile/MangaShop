<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<!DOCTYPE html>


<html lang="en">
<head>

<meta charset="UTF-8">
<title>Login</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
  
  
  
  <link rel="stylesheet" href="CSS/Bottone.css">
  <link rel="stylesheet" href="CSS/Login.css">
  <link rel="stylesheet" href="CSS/erroreOut.css">

<style type="text/css">
@media screen and (max-width: 650px) {
    #login{
      left: 15%;
  
    }
    
  }
 
</style>
  <% 
  	String carrelloReindirizzo= request.getParameter("Acquisto");
  %>
</head>

<body>

<%@include file="Header.jsp" %>
<% if(u!=null || u instanceof AmministratoreBean){
	response.sendRedirect("ErroreServlet?errore=Login");
	return;
} %>

	


	       <!-----------------------------------------------------------Login---------------------------------------->
	       <%if (request.getParameter("errore")!= null) {%>
        <div class="errore">
        	<p>Email o Password errata!</p>
        </div>
        
        <%} %>
        <%request.setAttribute("errore", null);%>
        

  <div id="login">
    <div id="videoLogin">
      <video autoplay loop muted title="Video di Naruto">
       	<source src="Video/naruto.mp4" type="video/mp4">
   		<track label="English" kind="captions" srclang="en" src="Video/naruto.mp4" default>
   		<track label="Deutsch" kind="captions" srclang="de" src="Video/naruto.mp4">
   		<track label="Español" kind="captions" srclang="es" src="Video/naruto.mp4">
        
        Your browser does not support HTML5 video.
      </video>
    </div>
    <div id="titoloLogin"><p>Login</p></div>
    <form id="formLogin" method="post" action="LoginServlet">
    <input type="hidden" name="carrelloReindirizzo" value="<%=carrelloReindirizzo%>">
        <div class="inputLogin">
          <label for="email"><i class="fa-sharp fa-solid fa-envelope"></i> Email</label>
          <input required type="email" id="email" name="email" >
        </div>
        <div class="inputLogin">
          <label for="password"><i class="fa-sharp fa-solid fa-lock"></i> Password </label>
          <input required type="password" id="password" name="password" >
        </div>  
        <div class="contentBox">
          <div id="fifth" class="buttonBox">
            <button type="submit">Accedi</button>
          </div>
         </div>
       
        </form>
        </div>
        
<%@include file="Footer.html" %>   
 
<script type="text/javascript">
if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}
</script>

</body>
</html>