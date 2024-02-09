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

 
@WebServlet("/AcquistoServlet")
@MultipartConfig
public class AcquistoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final CarrelloManager carrellomanager= new CarrelloManager();
	
	
    
    public AcquistoServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessione =request.getSession();
		Profilo utente= (Profilo) sessione.getAttribute("Profilo");
		
		
		System.out.println("sonon in servletacqu");
		
		if(utente==null) {
			//in modo da reindirizzarlo poi dalla login al carrello direttamente
			response.sendRedirect("Login.jsp?Acquisto=1");
		}else {
			List<CarrelloBean> carr=(List<CarrelloBean>) request.getSession().getAttribute("carrello");
			//se non ci sono elementi nel carrello
			if(carr.size()==0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Carrello?carrelloVuoto=1");
			    dispatcher.forward(request, response);
			    return;
				
			}
			List<IndirizzoBean> indirizzi= carrellomanager.retriveByCampoManager(new IndirizzoBean(), "utente", utente.getEmail());
			sessione.setAttribute("indirizziCheck", indirizzi);
			List<CartaCreditoBean> carte= carrellomanager.retriveByCampoManager(new CartaCreditoBean(), "utente", utente.getEmail());
			sessione.setAttribute("carteCheck", carte);
			//response.sendRedirect("CheckList.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("CheckList.jsp");
		    dispatcher.forward(request, response);
		}
			 

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acquistoForm=request.getParameter("acquistoFine");
		
		if(acquistoForm!=null) {
			HttpSession sessione =request.getSession();
			Profilo utente= (Profilo) sessione.getAttribute("Profilo");
			List<CarrelloBean> carrello= (List<CarrelloBean>) sessione.getAttribute("carrello");

			String nome=request.getParameter("name");
			String cognome=request.getParameter("cognome");
			String email=request.getParameter("email");
			String telefono=(request.getParameter("telefono"));
			
			String regione=request.getParameter("regione");
			String provincia=request.getParameter("provincia");
			String citta=request.getParameter("citta");
			String indirizzo=request.getParameter("indirizzo");
			Integer cap=Integer.parseInt(request.getParameter("cap"));
			
			
			String numeroCarta=request.getParameter("numeroCarta");
			String nomeCarta=request.getParameter("nomeCarta");
			String dataModificata=request.getParameter("meseCarta")+"-11";
			Date meseCarta=Date.valueOf(dataModificata);
			Integer cvc=Integer.parseInt(request.getParameter("cvc"));
			
			//salva in ordine
			
			OrdineBean ordineUtente=new OrdineBean() ;
			Integer idOrdine= carrellomanager.insertManager(ordineUtente);
			

			
			float totale=0f;
			
			for(CarrelloBean car:carrello) {
				MangaBean mangaDaInserire = carrellomanager.retriveById(new MangaBean(), car.getManga());
				ImmaginiMangaBean immaginiMangaDaIns = carrellomanager.retriveById(new ImmaginiMangaBean(), mangaDaInserire.getImmagini_manga());
				ProdottoOrdineBean prodotto = new ProdottoOrdineBean(immaginiMangaDaIns.getCover(), mangaDaInserire.getNome(), mangaDaInserire.getId(),idOrdine,car.getQuantita(),mangaDaInserire.getPrezzo());
				carrellomanager.insertManager(prodotto);
				totale+=car.getQuantita()*mangaDaInserire.getPrezzo();
				//?
				carrellomanager.deleteManager(car.getId(), car);
			}
			

			String indirizzoStringa="Regione " + regione + ". Provincia "+ provincia + ". Citta " + citta + ". Indirizzo " + indirizzo + ". Cap " + cap;
			String cartaStringa = "Numero carta " + numeroCarta + ". Nome proprietario " + nomeCarta + ". CVC " + cvc + ". Mese scadenza " + meseCarta;
			ordineUtente=new OrdineBean("in consegna", Date.valueOf(LocalDate.now()) , totale, carrello.size(),indirizzoStringa, cartaStringa,nome,cognome,telefono,utente.getEmail());
			ordineUtente.setId(idOrdine);
			carrellomanager.updateManager(ordineUtente);
			
			//elimina dal carrello DB
			
			sessione.setAttribute("carrello", new ArrayList<CarrelloBean>());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
		    dispatcher.forward(request, response);
			
			
		}
			
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	

}
