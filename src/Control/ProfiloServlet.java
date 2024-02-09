package Control;


import java.sql.Date;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.Session;

import Beans.AmministratoreBean;
import Beans.AutoreBean;
import Beans.CartaCreditoBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;

import Beans.IndirizzoBean;


import Beans.UtenteBean;


import Manager.GeneralManager;


import Manager.UtenteManager;


import java.io.IOException;

import java.io.PrintWriter;

 
@WebServlet("/ProfiloServlet")
@MultipartConfig
public class ProfiloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final UtenteManager utentemanager= new UtenteManager();
	
    
    public ProfiloServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession();
		UtenteBean utente= (UtenteBean)sessione.getAttribute("Profilo");
		
		//richiedo ajax le carte di credito e gli indirizzi per mostrarle all'utente
		if(request.getParameter("RichiestaCarteIndirizzi")!=null) {
			RichiestaIndirizziCarta(request,response,utente);
		}

		//aggiorna il profilo dell'utente
		if(request.getParameter("email")!=null) {
			/*ricevi i dati dal form profilo. */
			String nome=request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String telefono = request.getParameter("telefono");
			
			String password = request.getParameter("password");
			
			UtenteBean utentenew = new UtenteBean(utente.getEmail(), password, telefono, nome, cognome);
			utentemanager.updateManager(utentenew);
			sessione.setAttribute("Profilo", utentenew);
			
			response.sendRedirect("Profilo.jsp?errore=1");
		}

		//ajax, inserisce un nuovo indirizzo o una nuova carta di credito
		if(request.getParameter("tipologia")!=null) {
			richiestaInserimento(request, response,sessione,utente);
		}
		

			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	private void RichiestaIndirizziCarta(HttpServletRequest request, HttpServletResponse response,UtenteBean utente) {

		List<CartaCreditoBean> carte=null;
		List<IndirizzoBean> indirizzi=null;
		List<EditoreBean> editori=null;
		
		
		//non retrive all, ma dell utente
		
		carte = generalmanager.retriveByCampoManager(new CartaCreditoBean(), "utente" ,utente.getEmail() );
		indirizzi = generalmanager.retriveByCampoManager(new IndirizzoBean(), "utente" ,utente.getEmail() );
		
		Gson gson = new Gson();
		JsonObject risultato = new JsonObject();
	    risultato.add("indirizzi", gson.toJsonTree(indirizzi));
	    risultato.add("carte", gson.toJsonTree(carte));
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write(risultato.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void richiestaInserimento(HttpServletRequest request, HttpServletResponse response, HttpSession sessione, UtenteBean utente) throws IOException {
		String tipologia= request.getParameter("tipologia");
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(tipologia.equals("Indirizzo")) {	
			String regione= request.getParameter("regione");
			String provincia= request.getParameter("provincia");
			String citta= request.getParameter("citta");
			int cap=Integer.parseInt(request.getParameter("cap"));
			String via= request.getParameter("via");
 
			IndirizzoBean ind = new IndirizzoBean(utente.getEmail(),regione,provincia,citta,cap,via);
			
		
			List<IndirizzoBean> indirizzi= generalmanager.retriveAllManager(new IndirizzoBean());
			for (IndirizzoBean in: indirizzi) {
				//sta inserendo un indirizzo già esistente
				if(cap==in.getCap() && regione.equals(in.getRegione()) 
						&&	provincia.equals(in.getProvincia()) && citta.equals(in.getCitta())
						&&  via.equals(in.getVia()))
					
					return;
					
			}
			
			
				out.print(generalmanager.
						insertManager(ind));
				sessione.setAttribute("listaIndirizzi", generalmanager.retriveAllManager(new IndirizzoBean()));
			
			
		} else if(tipologia.equals("CartaCredito")) {
			int cvc=Integer.parseInt(request.getParameter("cvc"));
			String codice = request.getParameter("codice");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			
			Date data_scadenza= Date.valueOf(request.getParameter("data"));
			CartaCreditoBean carta= new CartaCreditoBean(utente.getEmail(), codice, data_scadenza, cvc, nome);
	
			List<CartaCreditoBean> carte= generalmanager.retriveAllManager(new CartaCreditoBean());
			for (CartaCreditoBean car: carte) {
				//sta inserendo una carta già esistente
				if(codice.equals(car.getCodice()))
					{
					return;
					}
			}
			
			out.print(generalmanager
					.insertManager(carta));
			sessione.setAttribute("listaCarte", generalmanager.retriveAllManager(new CartaCreditoBean()));
		}
		
	}
	

}
