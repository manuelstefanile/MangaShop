
function abilitaPulsanti(){
    const inputs = document.querySelectorAll('.inputProfilo');
    const select = document.gete
    for(let i = 0; i < inputs.length; i++){
    	if(inputs[i].name!=="email"){
      inputs[i].style.pointerEvents = 'auto';
      inputs[i].style.border = '2px solid ';
    	}
    }
    document.querySelectorAll(".div_select").forEach(function(element) {
    element.classList.remove("div_select");
  
    });
  document.querySelectorAll("select").forEach(function(element) {
  element.style.display = "block";
  });
  document.getElementById("annullaProfilo").style.setProperty("display","inline");
  document.getElementById("salvaProfilo").style.setProperty("display","inline");
  document.getElementById("modificaProfilo").style.setProperty("display","none");
  var button = document.getElementById("salvaProfilo");
  button.addEventListener("mouseover", function() {
    this.style.backgroundColor = "green";
  });
  button.addEventListener("mouseout", function() {
    this.style.backgroundColor = "";
  });  
  }
