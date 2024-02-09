var nome;
var cognome;

var telefonoInput = document.getElementById("telefono");
	telefonoInput.addEventListener("input", function() {
		// rimuovi eventuali spazi vuoti
        var telefonoValue = telefonoInput.value.trim();
        // rimuovi tutti i caratteri non numerici
        telefonoValue = telefonoValue.replace(/\D/g, "");  
        telefonoInput.value = telefonoValue;
        
        if (telefonoValue.length < 10) {
        	telefonoInput.style.borderColor="red";
            telefonoInput.setCustomValidity("Il numero di telefono deve contenere almeno 10 cifre.");
          } else {
        	  telefonoInput.style.borderColor="green";
            telefonoInput.setCustomValidity("");
          }
      });
      
	var emailInput = document.getElementById("email");
    emailInput.addEventListener("input", function() {

      var emailValue = emailInput.value;
      // crea una regex per controllare se l'email � valida
      var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      // controlla se l'email � valida
      if (!emailRegex.test(emailValue)) {
    	  emailInput.style.borderColor="red";
        // se l'email non � valida, mostra un messaggio di errore
        emailInput.setCustomValidity("Inserisci un'email valida");
      } else {
    	  emailInput.style.borderColor="green";
        emailInput.setCustomValidity("");
      }
    });
    
    
    function togglePasswordVisibility() {
  	  const passwordInput = document.getElementById("password");
  	  const eyeIcon = document.querySelector(".input-password .eye-icon i");
  	  
  	  if (passwordInput.type === "password") {
  	    passwordInput.type = "text";
  	    eyeIcon.classList.remove("fa-eye-slash");
  	    eyeIcon.classList.add("fa-eye");
  	  } else {
  	    passwordInput.type = "password";
  	    eyeIcon.classList.remove("fa-eye");
  	    eyeIcon.classList.add("fa-eye-slash");
  	    
  	  }
  	}

	const passwordInput = document.getElementById("password");
	passwordInput.addEventListener("input", function() {
  	  const passwordValue = passwordInput.value;

  	  /*.test controlla se matcha */
  	  if (passwordValue.length < 8|| !/[A-Z]/.test(passwordValue) || !/[0-9]/.test(passwordValue)) {
  		passwordInput.style.borderColor="red";
  		//blocca l invio del form
    	  passwordInput.setCustomValidity("Inserisci una password valida.\nLunghezza almeno 8.\nAlmeno una maiuscola.\nAlmeno un numero");
  	    
  	  } else {
  		passwordInput.style.borderColor="green";
  		// puoi inviare.
  		  passwordInput.setCustomValidity("");
  	    
  	  }
  	});
	
	$("#nome").on("input", function (){
		if(this.value.length>0){
			nome=true
			this.style.borderColor="green";
		}
		else{
			nome=false
			this.style.borderColor="red";
		}
	});
	$("#nome").on("blur", function (){
		if(this.value.trim().length>0){
			nome=true
			this.style.borderColor="green";
		}
		else{
			nome=false
			this.style.borderColor="red";
		}
		console.log("nom3 " +nome);
	});
	
	$("#cognome").on("input", function (){
		if(this.value.length>0){
			cognome=true
			this.style.borderColor="green";
		}
		else{
			congome=false
			this.style.borderColor="red";
		}
	});
	$("#cognome").on("blur", function (){
		console.log(this.value.trim().length);
		if(this.value.trim().length>0){
			cognome=true
			this.style.borderColor="green";
		}
		else{
			cognome=false
			this.style.borderColor="red";
		}
		console.log(cognome);
	});
 	