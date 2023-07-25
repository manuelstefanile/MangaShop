<script>
document.getElementById("ordina").addEventListener("change", function() {
	  var ordine = this.value; // Ottieni il valore selezionato dall'elemento select
	  

	  var listaManga = [
	    <% if (listaManga != null) {
	         for (MangaBean manga : listaManga) {
	        	 
	        	 String Copertina=null;
	     		String Titolo=null;
	             for(ImmaginiMangaBean immagine: immaginiManga){
	             	if(manga.getImmagini_manga()==immagine.getId()){
	             		try {
	             			Copertina = new String(Base64.getEncoder().encode(immagine.getCover()));
	             			Titolo = new String(Base64.getEncoder().encode(immagine.getTitolo()));
	                     } catch (NullPointerException e) {
	                        immagine = null;
	                     }
	             	}
	             } 
 
	             %>
	             {
	                Copertina: "<%= Copertina %>",
	                Titolo: "<%= Titolo %>",
	                Nome: "<%= manga.getNome() %>",
	                Prezzo: <%= manga.getPrezzo() %>,
	                Id : <%=manga.getId()%>
	             },
	             <% 
	         }
	     }
	     %>
	  ];
	  

	  // Ordina la lista in base al tipo selezionato
	  if (ordine === "Prezzo") {
	    listaManga.sort(function(a, b) {
	      return a.prezzo - b.prezzo;
	    });
	  } else if (ordine === "Nome") {
	    listaManga.sort(function(a, b) {
	      return a.Nome.localeCompare(b.Nome);
	    });
	  } 

	  // Aggiorna il contenuto del div con id "carteCategoria" con la lista ordinata
	  var carteCategoriaDiv = document.getElementById("carteCategoria");
	  carteCategoriaDiv.innerHTML = "";

	  listaManga.forEach(function(manga) {
	    // Genera il codice HTML per la singola carta di manga e aggiungilo al div "carteCategoria"
	    var cardHtml = generateMangaCardHtml(manga);
	    carteCategoriaDiv.innerHTML += cardHtml;
	  });
	});
</script>