 function ReindirizzaDettaglio(id,servlet="DettaglioServlet",nomeId="id"){
	 console.log(servlet);
	 var form = document.createElement("form");
	  form.setAttribute("method", "get");
	  form.setAttribute("action", servlet);
	  form.setAttribute("target", "hiddenFrame"); // Nome del frame o finestra nascosta

	  var parametro2 = document.createElement("input");
	  parametro2.setAttribute("type", "hidden");
	  parametro2.setAttribute("name", nomeId);
	  parametro2.setAttribute("value", id);
	  form.appendChild(parametro2);

	  document.body.appendChild(form);

	  form.submit();

		}