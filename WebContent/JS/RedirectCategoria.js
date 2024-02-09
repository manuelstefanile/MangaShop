
//quando premo sulla categoria, reindirizzami alla servlet che mi serve.Questa mi manderà alla pagina
//categoria di interesse.
function redirectToLista(categoria,id){
	
	var form = document.createElement("form");
	  form.setAttribute("method", "post");
	  form.setAttribute("action", "PagineMangaServlet");
	  form.setAttribute("target", "hiddenFrame"); // Nome del frame o finestra nascosta

	  // Aggiungi i parametri come campi nascosti nel modulo
	  var parametro1 = document.createElement("input");
	  parametro1.setAttribute("type", "hidden");
	  parametro1.setAttribute("name", "tipo");
	  parametro1.setAttribute("value", categoria);
	  form.appendChild(parametro1);

	  var parametro2 = document.createElement("input");
	  parametro2.setAttribute("type", "hidden");
	  parametro2.setAttribute("name", "id");
	  parametro2.setAttribute("value", id);
	  form.appendChild(parametro2);

	  document.body.appendChild(form);

	  form.submit();

		}

