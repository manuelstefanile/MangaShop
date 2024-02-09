
<script type="text/javascript">


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
	            	 Id:"<%=manga.getId()%>",
	                Copertina: "<%= Copertina %>",
	                Titolo: "<%= Titolo %>",
	                Nome: "<%= manga.getNome() %>",
	                Prezzo: <%= (float)manga.getPrezzo()%>
	             },
	             <% 
	         }
	     }
	     %>
	  ];
	  

	  // Ordina la lista in base al tipo selezionato
	  if (ordine === "Prezzo") {
	    listaManga.sort(function(a, b) {
	    	console.log(a.Prezzo + " "+ b.Prezzo);
	      return a.Prezzo - b.Prezzo;
	    });
	  } else if (ordine === "Nome") {
	    listaManga.sort(function(a, b) {
	    	console.log(a.Nome + " "+ b.Nome);
	      return a.Nome.localeCompare(b.Nome);
	    });
	  } 

	  // Aggiorna il contenuto del div con id "carteCategoria" con la lista ordinata
	  var carteCategoriaDiv = document.getElementById("carteCategoria");
	  carteCategoriaDiv.innerHTML = "";

	  listaManga.forEach(function(manga) {
	    // Genera il codice HTML per la singola carta di manga e lo aggingo al div "carteCategoria"
	    var cardHtml = generateMangaCardHtml(manga);
	    carteCategoriaDiv.innerHTML += cardHtml;
	  });
	});

function generateMangaCardHtml(manga) {
	  var Copertina = manga.Copertina; // Assumi che la proprietà Copertina contenga il valore corretto
	  var Titolo = manga.Titolo; // Assumi che la proprietà Titolo contenga il valore corretto
	  var Nome = manga.Nome; // Assumi che la proprietà Nome contenga il valore corretto
	  var Prezzo = manga.Prezzo; // Assumi che la proprietà Prezzo contenga il valore corretto
	  var Id=manga.Id;

	  var cardHtml = "";
	  cardHtml += '<figure class="snip1571">';
	  '<img  />';
	  cardHtml += '<img src="data:image/jpeg;base64,' + Copertina + '" class="cover-image cursore" onclick="ReindirizzaDettaglio('+Id+')"  />';
	  
	  cardHtml += '<figcaption class="cursore" onclick="ReindirizzaDettaglio('+Id+')">';
	  cardHtml += '<h3><img src="data:image/jpeg;base64,' + Titolo + '" class="title" /></h3>';
	  cardHtml += '</figcaption>';
	  cardHtml += '<div class="prezzo">';
	  cardHtml += '<p class="titoloManga">' + Nome + '</p>';
	  cardHtml += '<p class="prezzoManga">' + Prezzo + ' &euro;</p>';
	  
	  cardHtml += '<button onclick="AggiungiCarrello('+Id+',1)" value="'+Id+'"  name="buttonId" class="button-acquista" type="submit" role="button"><span class="text">Acquista</span><span>Aggiungi Carrello</span></button>';
	  cardHtml += '</div></figure>';

	  return cardHtml;
	}
	</script>