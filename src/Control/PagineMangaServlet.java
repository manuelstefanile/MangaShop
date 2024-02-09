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

 
@WebServlet("/PagineMangaServlet")
@MultipartConfig
public class PagineMangaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final MangaManager mangamanager= new MangaManager();
	
    
    public PagineMangaServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//porta alle varie categorie manga dell admin
			reindirizzaPage(request, response);
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("tipo")!=null) {
			System.out.println("reindirizza");
			reindirizzaPage(request, response);
			
		}
		

		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
 

 

	
	public void reindirizzaPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("tipo");

		if(tipo!=null) {
			 HttpSession sessione = request.getSession();
			 //id della categoria
			 Object id=null;
			 try {
				 id=Integer.parseInt(request.getParameter("id"));
			} catch (Exception e) {
				id=(String)request.getParameter("id");
			}
			 
			 
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
			}else if(tipo.equals("novita")) {
				
				listaManga= mangamanager.retriveMangaNovita();
				 
				 
				 for(MangaBean manga: listaManga) {
					 ImmaginiMangaBean img=generalmanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
					 immaginiManga.add(img);
				 }
				 
				 
				 sessione.setAttribute("titolo","Novità della settimana" );
				 sessione.setAttribute("descrizione","Questi sono i manga usciti nell'ultima settimana." );
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
				 if((Integer)id==0) {
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
				System.out.println("categoria");
				//vuol dire che ho solo il nome della categoria. allora recupero l'id
				
				if(id instanceof String) {
					CategoriaBean cat= generalmanager.retriveByCampoManager(new CategoriaBean(), "nome", id).get(0);
					id=(Integer)cat.getId();
				}
			
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
			response.sendRedirect("PagineManga.jsp");
            /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagineManga.jsp");
            dispatcher.forward(request, response);*/
            return;
			
		}
		
		

		
	}

	
	
	
	

}
