package Control;


import java.sql.Date;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
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
import Beans.CarrelloBean;
import Beans.CartaCreditoBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.ImmaginiMangaBean;
import Beans.IndirizzoBean;
import Beans.MangaBean;
import Beans.Manga_Img;
import Beans.OrdineBean;
import Beans.ProdottoOrdineBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.ACEManager;
import Manager.AmministratoreManager;
import Manager.CarrelloManager;
import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.MangaManager;
import Manager.UtenteManager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

 
@WebServlet("/ErroreServlet")
@MultipartConfig
public class ErroreServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	
	
    
    public ErroreServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errore=request.getParameter("errore");
		HttpSession sessione = request.getSession();
	if(errore!=null) {	
		if(errore.equals("Profilo")) {
			sessione.setAttribute("erroreDescrizioneError", "Tentativo di accedere alla pagina Profilo senza autenticazione");
			response.sendRedirect("Errore.jsp?errore=Profilo");
			return;
		}else if(errore.equals("Registrazione")) {
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina della registrazione da loggato");
			response.sendRedirect("Errore.jsp?errore=Registrazione");
			return;
		}else if(errore.equals("PagineManga")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina se sei un amministratore");
			System.out.println("pagine" + sessione.getAttribute("erroreDescrizioneError"));
			response.sendRedirect("Errore.jsp?errore=PagineManga");
			return;
		}else if(errore.equals("Ordini")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina se non sei loggato come utente");			
			response.sendRedirect("Errore.jsp?errore=Ordini");
			return;
		}else if(errore.equals("Login")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina se sei registrato");			
			response.sendRedirect("Errore.jsp?errore=Login");
			return;
		}else if(errore.equals("HomeUtente")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla home page utente da amministratore");			
			response.sendRedirect("Errore.jsp?errore=HomePage");
			return;
		}else if(errore.equals("CheckList")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla checkList");			
			response.sendRedirect("Errore.jsp?errore=CheckList");
			return;
		}else if(errore.equals("Dettaglio")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina dettaglio del manga da amministratore");			
			response.sendRedirect("Errore.jsp?errore=Dettaglio");
			return;
		}else if(errore.equals("Carrello")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina carrello da amministratore");			
			response.sendRedirect("Errore.jsp?errore=Carrello");
			return;
		}else if(errore.equals("AdminUtenti")|| errore.equals("AdminDettaglio") || errore.equals("AdminLista")
				|| errore.equals("AdminHome") || errore.equals("AdminAggiungi") || errore.equals("Adminace")) {
			
			sessione.setAttribute("erroreDescrizioneError", "Non puoi accedere alla pagina se non sei amministratore");			
			response.sendRedirect("Errore.jsp?errore=Admin");
			return;
	
		}else if(errore.equals("MangaNonPresente")) {

			sessione.setAttribute("erroreDescrizioneError", "Questo manga non è più presente nel database");			
			response.sendRedirect("Errore.jsp?errore=MangaNonPresente");
			return;
		}else if(errore.equals("General")) {

			sessione.setAttribute("erroreDescrizioneError", "Pagina non disponibile. Vai alla home.");			
			response.sendRedirect("Errore.jsp?errore=Generale");
			return;
		} 
	}else {
		int erroreStato=response.getStatus();
		
		if (erroreStato==404) {
			sessione.setAttribute("erroreDescrizioneError", "404 Pagina non trovata");			
			response.sendRedirect("Errore.jsp?errore=404");
			return;
		}else if (erroreStato==500) {
			sessione.setAttribute("erroreDescrizioneError", "500.");			
			response.sendRedirect("Errore.jsp?errore=500");
			return;
		}
	}
	System.out.println(response.getStatus());
	
		
		
		
 
		
		 
		 

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
			
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	

}
