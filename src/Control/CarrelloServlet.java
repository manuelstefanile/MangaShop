package Control;


import java.sql.Date;



import java.sql.SQLException;
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
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.ImmaginiMangaBean;
import Beans.MangaBean;
import Beans.Manga_Img;
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

 
@WebServlet("/CarrelloServlet")
@MultipartConfig
public class CarrelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final CarrelloManager carrellomanager= new CarrelloManager();
	
	
    
    public CarrelloServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
				HttpSession sessione=request.getSession();
				List<CarrelloBean> carrello=(List<CarrelloBean>)sessione.getAttribute("carrello");
				List<Manga_Img> manga_immagini=new ArrayList<Manga_Img>();
				HashMap<Integer, Integer> quantita = new HashMap<Integer, Integer>();
				if(carrello!=null) {
					for(CarrelloBean oggInCarrello : carrello) {
						
						MangaBean manga = carrellomanager.retriveById(new MangaBean(), oggInCarrello.getManga());
						ImmaginiMangaBean immagini= carrellomanager.retriveById(new ImmaginiMangaBean(),manga.getImmagini_manga());
						Manga_Img mangaImg= new Manga_Img(manga.getId(),manga.getNome(),manga.getPrezzo(),immagini.getCover(),null,null);
						manga_immagini.add(mangaImg);
						quantita.put(manga.getId(), manga.getQuantita());
					}
				
				}
				sessione.setAttribute("carrello", carrello);
				sessione.setAttribute("arrayNumeroDisponibile", quantita);
				sessione.setAttribute("mangaCarrello", manga_immagini);
				
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
	            dispatcher.forward(request, response);
				
			
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione=request.getSession();
		Profilo profilo = (Profilo)sessione.getAttribute("Profilo");
		String tipo = request.getParameter("tipo");
		List<CarrelloBean> carrello=(List<CarrelloBean>)sessione.getAttribute("carrello");
		
		//aggiungi manga al carrello
		if(tipo.equals("aggiungi")) {
			String idStringa=request.getParameter("id");
			Integer id=null;
			if(idStringa!=null)id=Integer.parseInt(idStringa);
			else id=null;
			
		
			//manga id da aggiungere
			if(id!=null) {
				int quantita=Integer.parseInt( request.getParameter("quantita"));
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();


			
			
				String email =null;
				if(profilo!=null) {
					// esiste utente registrato allora aggiungi al db il manga,e aggiorna carrelloSessione
					email = profilo.getEmail();
					CarrelloBean carrelloDaIns= new CarrelloBean(email,id,quantita);
				
					boolean controlloInserimento=carrellomanager.controlloPrelievoManga(carrelloDaIns.getManga(), carrelloDaIns.getQuantita());
					//posso inserire la quantita di manga richiesto
				
					if(controlloInserimento) {
						carrellomanager.insertManager(carrello,carrelloDaIns);
					}
					carrello=carrellomanager.retriveByCampoManager(new CarrelloBean(), "utente", profilo.getEmail());
				
					//se non è registrato l utente
				}else {
					CarrelloBean carrelloDaIns= new CarrelloBean(null,id,quantita);
					CarrelloBean controlloManga = null;
					MangaBean mangaAdd= carrellomanager.retriveById(new MangaBean(), carrelloDaIns.getManga());
					
					//se posso prelevare il manga allora
					if(carrellomanager.controlloPrelievoManga(carrelloDaIns.getManga(), carrelloDaIns.getQuantita())) {
						
						for(CarrelloBean car : carrello) {
							//c è un manga cosi nel carrello sessione
							if(car.getManga()==carrelloDaIns.getManga()) {
								controlloManga=car;
							}
						}
						//se c è allora
						if(controlloManga!=null) {
							
							//se la quantita di manga rimasti è minore di mangaSessione+ manga che vuoi inserire
							if(mangaAdd.getQuantita()<carrelloDaIns.getQuantita()+controlloManga.getQuantita()) {
								
								//setta il manga da inserire a MangaRimasti
								carrelloDaIns.setQuantita(mangaAdd.getQuantita()-controlloManga.getQuantita());
							}
						}
						carrellomanager.aggiornaCarrelloSessione(carrello, carrelloDaIns);
					}
					
				
				}
 
			
				sessione.setAttribute("carrello",carrello);
				out.print(carrello.size());

		
			}
		}else if(tipo.equals("rimuovi")) {
		//rimuovi dal carrello
			String idCarrelloStringa=request.getParameter("idCarrello");
			Integer idCarrello=null;
		
			if(idCarrelloStringa!=null)idCarrello=Integer.parseInt(idCarrelloStringa);
			else idCarrello=null;

		//è l utente a chiedere la rimozione
			if(profilo!=null) {
			
			
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();

				//aggiorno la quantita del manga, dato che elimino nel carrello
					CarrelloBean carrelloManga = carrellomanager.retriveById(new CarrelloBean(), idCarrello);
					MangaBean mangaUpdate = carrellomanager.retriveById(new MangaBean(), carrelloManga.getManga());
					mangaUpdate.setQuantita(mangaUpdate.getQuantita()+carrelloManga.getQuantita());
					carrellomanager.updateManager(mangaUpdate);
				//elimino dal carrello.
				carrellomanager.deleteManager(idCarrello, new CarrelloBean());
				//carrello senza il manga eliminato
					carrello=carrellomanager.retriveByCampoManager(new CarrelloBean(), "utente", profilo.getEmail());
					Float totale=0f;
					for (CarrelloBean carre: carrello) {
						int quantitaMangaCarrello=carre.getQuantita();
						Float prezzo=(Float)carrellomanager.retriveById(new MangaBean(), carre.getManga()).getPrezzo();
						totale+=(prezzo*quantitaMangaCarrello);
							
					}
					carrello=carrellomanager.retriveByCampoManager(new CarrelloBean(), "utente", profilo.getEmail());
				
					sessione.setAttribute("carrello",carrello);
					out.print(totale+"-"+carrello.size());				
			//l utente non loggato vuole rimuovere allora idCarrello=al manga da eliminare
			}else {
				
				Integer idManga = Integer.parseInt(request.getParameter("idManga"));
				
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
			
				carrello=(List<CarrelloBean>) sessione.getAttribute("carrello");
				float totale=0f;
				
				
				CarrelloBean carrdaRimuovere=null;
				for (CarrelloBean car: carrello) {
					if(car.getManga()==idManga) {
						carrdaRimuovere=car;
					}
				}
				carrello.remove(carrdaRimuovere);
				
				for(CarrelloBean car:carrello) {
					totale+=car.getQuantita()*(Float)carrellomanager.retriveById(new MangaBean(), car.getManga()).getPrezzo();
					
				}
				
				sessione.setAttribute("carrello", carrello);
				out.print(totale+"-"+carrello.size());
			}
		}else if(tipo.equals("aggiornaQuantita")) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			
    		
			Integer idManga=Integer.parseInt(request.getParameter("idManga"));
			Integer quantita = Integer.parseInt(request.getParameter("quantita"));
			for(CarrelloBean car:carrello) {
				
				//aggiorna il manga quantita nel carrello
				if(car.getManga()==idManga) {
					Integer quantitavecchia=car.getQuantita();
					car.setQuantita(quantita);
					MangaBean manga=carrellomanager.retriveById(new MangaBean(), idManga);
					if(profilo!=null) {
						carrellomanager.updateManager(car);
						
						
						manga.setQuantita(manga.getQuantita()+quantitavecchia-car.getQuantita());
						carrellomanager.updateManager(manga);
					}
					
					}
				}
			sessione.setAttribute("carrello", carrello);
			}
			
			
		
 
			
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	

}
