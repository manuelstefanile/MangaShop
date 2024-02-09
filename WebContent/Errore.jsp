<%@page import="Beans.WishlistBean"%>
<%@page import="Beans.CarrelloBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>


<html>
<head>

<meta charset="UTF-8">
<title>Errore</title>

  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  


<style type="text/css">
 body{
  background-color: darkred;
 }
 #SovraTesto{
  margin: 0;
    top: 40%;
    left: 30%;
    right: 30%;
    position: fixed;
    text-align: center;
    background-color: brown;
    border-radius: 20px;
 }
 #SottoTesto{
  margin-left: 5px;
    margin-right: 5px;
 }
 #testoErrore {
  color: white;
 }
 #titoloErrore{
  font-size: 20px;
 }
 .button-81 {
  margin-left: 7px;
    margin-right: 7px;
    border-radius: 20px;
    margin-bottom: 20px;
    /* width: 100%; */
    background-color: #fff;
    border: 0 solid #e2e8f0;
    box-sizing: border-box;
    color: black;
    cursor: pointer;
    display: inline-block;
    font-family: "Basier circle",-apple-system,system-ui,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
    /* font-size: 74%; */
    font-weight: 600;
    line-height: 1;
    padding: 1rem 1.6rem;
    text-align: center;
    text-decoration: none #0d172a solid;
    text-decoration-thickness: auto;
    transition: all .2s cubic-bezier(.4, 0, .2, 1);
    box-shadow: 0px 1px 2px rgba(166, 175, 195, 0.25);
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
}

.button-81:hover {
  background-color: green;
  color: #fff;
}

</style>

</head>
 
<body>
<% 
	String errore= (String)session.getAttribute("erroreDescrizioneError");
%>

   <div id="SovraTesto">
    <p id="titoloErrore">Errore</p>
    <div id="SottoTesto">
      <p id="testoErrore">Errore. <%=errore %> </p>
      <button onclick="redirectToHome()" class="button-81">Torna alla Home</button>
    </div>
  </div>
 <script type="text/javascript">
 function redirectToHome() {
     // Cambia l'URL alla pagina di home
     window.location.href = "HomePage.jsp";
   }
 </script>


</body>
</html>