 	

/**********************************************SPOSTA DIV FILTRI*************************** */
function spostaElemento() {
    var divDaSpostare = document.getElementsByClassName('dropdownCategoria')[0];
    var divDestinazione = document.getElementsByClassName('submenu-test')[0];
    var divFiltriNormali= document.getElementById('filtriCategoria');
    if(divFiltriNormali!=null) {
            
  var larghezzaSchermo = window.innerWidth;
  if (larghezzaSchermo < 800) { // esempio di soglia di larghezza
    divDestinazione.appendChild(divDaSpostare);
    
  } else {
    divFiltriNormali.appendChild(divDaSpostare);
  }
    }

}



// Aggiungere un ascoltatore di eventi resize alla finestra del browser
window.addEventListener('resize', spostaElemento);



// Chiamare la funzione per eseguirla la prima volta
spostaElemento();





/*************************************************************filtri per PagineManga NON IMPLEMENTATO**

var buttons = document.querySelectorAll(".bottoneCategoria");
for (var i = 0; i < buttons.length; i++) {
  buttons[i].addEventListener("click", function() {
    var submenuClass = this.getAttribute("data-submenu");
    var submenu = document.querySelector("#" + submenuClass);
    if (submenu.classList.contains("visible")) {
      submenu.classList.remove("visible");
    } else {
      submenu.classList.add("visible");
    }
  });
}
******/
/********************************************APRI E CHIUDI MENU LATERALE anche per filtri ***************************** */
function openNav(navAprire) {
    document.getElementsByClassName(navAprire)[0].style.width = "200px";
    var overlay = document.createElement("div");
    overlay.setAttribute("id", "scuroP");
    overlay.className = "scuro";
    document.body.appendChild(overlay);
  }

  function closeNav(navChiudere) {
    if(navChiudere=='submenu-test'){
      var buttons = document.querySelectorAll(".bottoneCategoria");
      for (var i = 0; i < buttons.length; i++) {

        var submenuClass = buttons[i].getAttribute("data-submenu");
        var submenu = document.querySelector("#" + submenuClass);
        if (submenu.classList.contains("visible")) {
          submenu.classList.remove("visible");
        }

    }}
    chiudi();
    document.getElementsByClassName(navChiudere)[0].style.width = "0%";
    var overlay = document.getElementById("scuroP");
    overlay.remove();
  }
  /***********************************************funzioni di apertura e chiusura dropdown*************************************** */
  function chiudi(){
    document.querySelectorAll('.menuDropdown-content').forEach(function(el) {
      el.style.display = "none";
    });
    }
    function apri(){
    document.querySelectorAll('.menuDropdown-content').forEach(function(el) {
      el.style.display = "block";
    });
    }
    
    function apriSottoMenu(){
      var dropdown = document.querySelector(".menuDropdown-content");
      // Controlla se il dropdown è aperto
      if (dropdown.style.display === "block") {
     
        chiudi();
      } else {
     
        apri();
      }
    }


   
    
    