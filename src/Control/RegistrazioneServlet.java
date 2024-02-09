package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.AmministratoreBean;
import Beans.CarrelloBean;
import Beans.UtenteBean;
import Beans.WishlistBean;
import Manager.AmministratoreManager;
import Manager.UtenteManager;

 
@WebServlet("/UtenteServlet")
public class RegistrazioneServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private final UtenteManager utentemanager= new UtenteManager();
    
    public RegistrazioneServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//se non ci sono dublicati con l'email allora registra l utente 
		UtenteBean utente = Utente(request);
		if(utentemanager.insertUtente(utente)) {
			HttpSession sessione=request.getSession();
			//crea anche un carrello e wishlist associato al cliente.
			//utentemanager.insertManager(new CarrelloBean(utente.getEmail(),null,0));
			//utentemanager.insertManager(new WishlistBean(utente.getEmail(),null,0));
        	sessione.setAttribute("Profilo", utente);
		    
		    
		    response.sendRedirect(request.getContextPath() + "/Home.jsp?errore=0");
		    return;
		} else {
		    
		    response.sendRedirect(request.getContextPath() + "/Registrazione.jsp?errore=1");
		    return;
		}


		
	}
	
	
	
	private UtenteBean Utente(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String telefono = request.getParameter("telefono");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		UtenteBean utentebean= new UtenteBean(email,password,telefono,nome,cognome);
		return utentebean;
	}

}
