<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Beans.Profilo" %>
<!DOCTYPE html>


<html>
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
  
</head>

<body>

<%@include file="Header.jsp" %>

	


	       <!-----------------------------------------------------------Login---------------------------------------->
	       <%if (request.getParameter("errore")!= null) {%>
        <div class="errore">
        	<p>Email o Password errata!</p>
        </div>
        
        <%} %>
        <%request.setAttribute("errore", null);%>
        

  <div id="login">
    <div id="videoLogin">
      <video autoplay loop muted>
        <source src="Video/naruto.mp4" type="video/mp4">
        Your browser does not support HTML5 video.
      </video>
    </div>
    <div id="titoloLogin"><p>Login</p></div>
    <form id="formLogin" method="post" action="LoginServlet">
        <div class="inputLogin">
          <label for="email"><i class="fa-sharp fa-solid fa-envelope"></i> Email</label>
          <input required type="email" id="email" name="email" >
        </div>
        <div class="inputLogin">
          <label for="password"><i class="fa-sharp fa-solid fa-lock"></i> Password </i></label>
          <input required type="password" id="password" name="password" >
        </div>  
        <div class="contentBox">
          <div id="fifth" class="buttonBox">
            <button type="submit">Accedi</button>
          </div>
         </div>
       
        </form>
        </div>
        
                
 <script src="JS/Registrazione.js"> </script>
<script type="text/javascript">
if (performance.navigation.type === 1) {
    // Reload the page only when the user manually refreshes the page
    window.location.href = window.location.pathname;
}
</script>

</body>
</html>