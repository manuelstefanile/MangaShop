function reindirizzaOrdiniEmail(email){
	 let emailin = document.getElementById("emailNascostaUtente");
	 emailin.value=email;
	 document.getElementById("tableACE").submit();
	 
 }
 if (performance.navigation.type === 1) {
	    // Reload the page only when the user manually refreshes the page
	    window.location.href = window.location.pathname;
	}

 document.getElementById("tavoloGenerale").style.display = "block";
 document.getElementById("headerTable").style.display = "block";
 
 
 var elements = document.querySelectorAll(".cellGeneral");

 for (var i = 0; i < elements.length; i++) {
   elements[i].style.display = "block";
 }
 var elementi = document.querySelectorAll(".rowGeneral");

 for (var i = 0; i < elementi.length; i++) {
	 elementi[i].style.display = "block";
	 elementi[i].style.maxWidth = '100%';
 }
