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
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.General;
import Beans.ImmaginiMangaBean;
import Beans.MangaBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.AmministratoreManager;

import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.MangaManager;
import Manager.UtenteManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

 
@WebServlet("/Admin2Servlet")
@MultipartConfig
public class Admin2Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final MangaManager mangamanager= new MangaManager();
	
    
    public Admin2Servlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//porta alle varie categorie manga dell admin
			reindirizzaPage(request, response);
			System.out.println("sono in admin" + request.getParameter("buttonId"));
			if(request.getParameter("buttonId")!=null) {
				doPost(request, response);
				return;
			}
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//manda alla pagina dettaglio del manga amministratore
		if(request.getParameter("buttonId")!=null) {
			MangaDettaglio(request,response);
			return;
			
		}
		
		//aggiorna manga dettaglio di admin
		if(request.getParameter("titolo")!=null) {
			AggiornamentoManga(request,response);
			
		}
		
		if(request.getParameter("inputConferma")!=null) {
			Integer IdManga =Integer.parseInt(request.getParameter("inputConferma"));
			HttpSession sessione=request.getSession();
			List<MangaBean> lista=(List<MangaBean>) sessione.getAttribute("lista");
			if(IdManga!=null) {
				mangamanager.deleteManager(IdManga, new MangaBean());
				
				if(lista.size()>0) {
					List<ImmaginiMangaBean> immagini=(List<ImmaginiMangaBean>) sessione.getAttribute("immagini");
					MangaBean mangaEliminare = null;
					for(MangaBean manga: lista) {
						if(manga.getId()==IdManga) {
							mangaEliminare=manga;
						}
					}
					if(mangaEliminare!=null) {
						lista.remove(mangaEliminare);
					}
				}
				
			}
			sessione.setAttribute("lista", lista);
			response.sendRedirect("AdminListaManga.jsp");
 
		}
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	private void AggiornamentoManga(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessione=request.getSession();
		int idManga= Integer.parseInt(request.getParameter("idManga"));
		try {
			if(AggiornaManga(request,idManga)) {
				
				MangaBean mangaUpdate= generalmanager.retriveById(new MangaBean(), idManga);
				ImmaginiMangaBean imgUpdate= generalmanager.retriveById(new ImmaginiMangaBean(), mangaUpdate.getImmagini_manga());
			
				sessione.setAttribute("immagini", imgUpdate);
				sessione.setAttribute("manga", mangaUpdate);
				
				response.sendRedirect("AdminMangaDettaglio.jsp?errore=0");
				
				
			}else {
				MangaBean mangaUpdate= generalmanager.retriveById(new MangaBean(), idManga);
				ImmaginiMangaBean imgUpdate= generalmanager.retriveById(new ImmaginiMangaBean(), mangaUpdate.getImmagini_manga());
				
				request.setAttribute("immagini", imgUpdate);
				request.setAttribute("manga", mangaUpdate);
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMangaDettaglio.jsp");
				dispatcher.forward(request, response);
			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//reindirizza alla pagina manga admin
	private void MangaDettaglio(HttpServletRequest request, HttpServletResponse response) {
		int id= Integer.parseInt(request.getParameter("buttonId"));
		MangaBean mangaDB = generalmanager.retriveById(new MangaBean(), id);
		ImmaginiMangaBean imgDB= generalmanager.retriveById(new ImmaginiMangaBean(), mangaDB.getImmagini_manga());
		
		HttpSession sessione=request.getSession();
		sessione.setAttribute("immagini", imgDB);
		sessione.setAttribute("manga", mangaDB);
		
		try {
			response.sendRedirect("AdminMangaDettaglio.jsp");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	//converti le immagini prese dalla request in byte
	private byte[] ConvertiImmagine(HttpServletRequest request,String immagine) throws IOException, ServletException {
		Part imgPersonaggio = request.getPart(immagine);
		
		InputStream oo = imgPersonaggio.getInputStream();
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		
		int nRead;
		byte[] data = new byte[221024];

		
		while ((nRead = oo.read(data, 0, data.length)) != -1) {
		    buffer.write(data, 0, nRead);
		}
		
		buffer.flush();
		
		byte[] imageData = buffer.toByteArray();
		
		oo.close();
		buffer.close();
		return imageData;
		
	}
	//ritorna l'id del manga se inserito correttamente altrimenti null
	private boolean AggiornaManga(HttpServletRequest request, int id) throws IOException, ServletException {
		HttpSession sessione=request.getSession();
		byte[] immaginePersonaggio= ConvertiImmagine(request,"immaginePersonaggioInput");
		byte[] immagineCover= ConvertiImmagine(request,"immagineCoverInput");
		byte[] immagineTitolo= ConvertiImmagine(request,"immagineTitoloInput");
		System.out.println("dim img pass= " + immaginePersonaggio.length + " " + immagineTitolo.length + " "+ immagineCover.length);
		//immaginiAggiornate
		
		ImmaginiMangaBean img1=new ImmaginiMangaBean(immagineCover, immagineTitolo, immaginePersonaggio);
		

		String titolo=request.getParameter("titolo");
		
		

		Date data_uscita= Date.valueOf(request.getParameter("data_uscita"));
		int quantita=Integer.valueOf(request.getParameter("quantita"));
		float prezzo=Float.valueOf(request.getParameter("prezzo"));
		boolean disponibilita= request.getParameter("disponibilita") != null;
		
		String categoria= request.getParameter("categoria");
		String autore= request.getParameter("autore");
		String editore= request.getParameter("editore");
		int[] idCategorie=mangamanager.returnId(categoria,autore,editore);
		

		String rilegatura= request.getParameter("rilegatura");
		String descrizione= request.getParameter("descrizione");
		
		
		//mangaAggiornato
		
		MangaBean mangaDB = generalmanager.retriveById(new MangaBean(), id);
		if(!(titolo.equals(mangaDB.getNome()))) {
			System.out.println(titolo+" " + mangaDB.getNome());
			if(mangamanager.controlloDuplicateNameManga(titolo) == null) {
				request.setAttribute("errore", 1);
				return false;
			}
		}
		
		img1.setId(mangaDB.getImmagini_manga());
		ImmaginiMangaBean imgDB= generalmanager.retriveById(new ImmaginiMangaBean(), mangaDB.getImmagini_manga());
		if(immaginePersonaggio.length==0) 
			img1.setPersonaggio(imgDB.getPersonaggio());
		if(immagineCover.length==0)
			img1.setCover(imgDB.getCover());
		if(immagineTitolo.length==0)
			img1.setTitolo(imgDB.getTitolo());
		MangaBean manga1=new MangaBean(titolo,quantita,prezzo,disponibilita,idCategorie[0],idCategorie[1],idCategorie[2],rilegatura,data_uscita,descrizione,imgDB.getId());
		manga1.setId(id);

		
		if(mangamanager.comparaManga(manga1, mangaDB, img1, imgDB)) {
			Integer idImg=(Integer) generalmanager.updateManager(img1);
			manga1.setImmagini_manga(idImg);
			manga1.setId(id);
			generalmanager.updateManager(manga1);
			
			sessione.setAttribute("immagini", img1);
			sessione.setAttribute("manga", manga1);
			return true;
		}else { 
			sessione.setAttribute("immagini", imgDB);
			sessione.setAttribute("manga", mangaDB);
			return false;
		}
		

		
	}

	
	public void reindirizzaPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("tipo");

		if(tipo!=null) {
			 HttpSession sessione = request.getSession();
			 //id della categoria
			 int id=Integer.parseInt(request.getParameter("id"));
			 List<MangaBean> listaManga=null;
			 List<ImmaginiMangaBean> immaginiManga=new ArrayList<ImmaginiMangaBean>();
			 
			if(tipo.equals("all")) {
				
				 listaManga= generalmanager.retriveAllManager(new MangaBean());
				 
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				 
				sessione.setAttribute("titolo","Tutti i manga" );
				//se tipo è uguale ad una di queste macro categorie, in base all'id restituisci la lista manga satta
			} else if(tipo.equals("autore")) {
				AutoreBean ACE =(AutoreBean)generalmanager.retriveById(new AutoreBean(), id);
				 listaManga= generalmanager.retriveByCampoManager(new MangaBean(),tipo, request.getParameter("id"));
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				sessione.setAttribute("titolo",ACE.getNome() + " " + ACE.getCognome() );

			}else if(tipo.equals("disponibilita")) {
				
				 listaManga= generalmanager.retriveByCampoManager(new MangaBean(),tipo, id);
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				 if(id==0) {
					 sessione.setAttribute("titolo","Manga non Disponibili" );
				 }else	
					 sessione.setAttribute("titolo","Manga Disponibili" );

			} else if(tipo.equals("editore")) {
				EditoreBean ACE =generalmanager.retriveById(new EditoreBean(), id);
				listaManga= generalmanager.retriveByCampoManager(new MangaBean(),tipo, id);
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				sessione.setAttribute("titolo",ACE.getNome() + " " + ACE.getNome() );

			} else if(tipo.equals("categoria")) {
				CategoriaBean ACE =generalmanager.retriveById(new CategoriaBean(), id);
				listaManga= generalmanager.retriveByCampoManager(new MangaBean(),tipo, id);
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				sessione.setAttribute("titolo",ACE.getNome());
				sessione.setAttribute("descrizione",ACE.getDescrizione());

			}
			
			
			sessione.setAttribute("lista", listaManga);
			sessione.setAttribute("immagini", immaginiManga);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminListaManga.jsp");
            dispatcher.forward(request, response);
			
		}
		
		

		
	}

	
	
	
	

}
