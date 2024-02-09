package Control;


import java.sql.Date;


import java.sql.SQLException;
import java.util.Enumeration;
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
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.ImmaginiMangaBean;
import Beans.MangaBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.ACEManager;
import Manager.AmministratoreManager;

import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.MangaManager;
import Manager.UtenteManager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

 
@WebServlet("/AdminServlet")
@MultipartConfig
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final MangaManager mangamanager= new MangaManager();
	private final ACEManager acemanager= new ACEManager();
	
    
    public AdminServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//richiesta ajsax dei autori,categorie,editori 
		if(request.getParameter("RichiestaAutori") != null) {
			RichiestaAJAX(response);

		}
		// richiesta di aggiungere il manga
		if(request.getParameter("titolo")!=null){
			
			if(InserisciManga(request)!=null)
				response.sendRedirect(request.getContextPath() + "/AdminAggiungiManga.jsp?errore=0");
			else
				response.sendRedirect(request.getContextPath() + "/AdminAggiungiManga.jsp?errore=1");
			
			
		}
		//richiesta ajax di inserire autore o editore o categoria
		if(request.getParameter("tipologia")!=null) {
			richiestaInserimentoAEC(request,response);
		}
		
		//richiesta di eliminare ACE
		if(request.getParameter("tipoConferma")!=null) {
			deleteACE(request, response);
		}
		
		//richiesta di aggiornamento ACE
		if(request.getParameter("inputTipo")!=null) {
			aggiornaACE(request, response);
		}
		
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	
	
	
	
	
	private void richiestaInserimentoAEC(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tipologia= request.getParameter("tipologia");
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession sessione=request.getSession();
		if(tipologia.equals("Autore")) {	
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			boolean check=false;
			List<AutoreBean> autori = generalmanager.retriveByCampoManager(new AutoreBean(), "nome", nome);
			if(autori.size()>0) {
				for (AutoreBean au : autori) {
					if(au.getCognome().equals(cognome)) {
						check = true;
					}
				}
			}
			if(!check) {
				out.print(generalmanager
						.insertManager(new AutoreBean(nome,cognome)));
				sessione.setAttribute("listaAutori", generalmanager.retriveAllManager(new AutoreBean()));
			}
			
			
		} else if(tipologia.equals("Categoria")) {
			String nome = request.getParameter("nome");
			String descrizione= request.getParameter("descrizione");
			CategoriaBean categoria= new CategoriaBean(nome,descrizione);
			
			if((generalmanager.retriveByCampoManager(new CategoriaBean(), "nome", nome)).size()==0) {
				out.print(generalmanager
						.insertManager(new CategoriaBean(nome,descrizione)));
				sessione.setAttribute("listaCategorie", generalmanager.retriveAllManager(new CategoriaBean()));
			}
			
			
		}else if(tipologia.equals("Editore")) {
			String nome = request.getParameter("nome");
			EditoreBean editore = new EditoreBean(nome);
			if((generalmanager.retriveByCampoManager(new EditoreBean(), "nome", nome)).size()==0) {

				out.print(generalmanager.insertManager(editore));
				sessione.setAttribute("listaEditori", generalmanager.retriveAllManager(new EditoreBean()));
			}
	
		}
		
	}
	//ritorna l'id del manga se inserito correttamente altrimenti null
	private Integer InserisciManga(HttpServletRequest request) throws IOException, ServletException {
		byte[] immaginePersonaggio= ConvertiImmagine(request,"immaginePersonaggioInput");
		byte[] immagineCover= ConvertiImmagine(request,"immagineCoverInput");
		byte[] immagineTitolo= ConvertiImmagine(request,"immagineTitoloInput");
		//setta l immagine di default
		if(immagineCover.length==0) {
			immagineCover=mangamanager.retriveById(new ImmaginiMangaBean(), 0).getCover();
		}
		ImmaginiMangaBean immagini=new ImmaginiMangaBean(immagineCover, immagineTitolo, immaginePersonaggio);

		String titolo=request.getParameter("titolo");
		if(mangamanager.controlloDuplicateNameManga(titolo)==null) {
			
			return null;
		};
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
		
		Integer idImmagini= mangamanager.insertImageMangaManager(immagini);

		MangaBean manga=new MangaBean(titolo,quantita,prezzo,disponibilita,idCategorie[0],idCategorie[1],idCategorie[2],rilegatura,data_uscita,descrizione,idImmagini);
		System.out.println(manga);
		return generalmanager.insertManager(manga);

		
	}
	private byte[] ConvertiImmagine(HttpServletRequest request,String immagine) throws IOException, ServletException {
		Part imgPersonaggio = request.getPart(immagine);
		System.out.println("part" +imgPersonaggio.getSize());
		InputStream oo = imgPersonaggio.getInputStream();
		System.out.println("input" + oo.toString());
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
	private void RichiestaAJAX(HttpServletResponse response) throws IOException {

		List<AutoreBean> autori=null;
		List<CategoriaBean> categorie=null;
		List<EditoreBean> editori=null;
		try {
			autori = generalmanager.retriveAllManager(AutoreBean.class.newInstance());
			categorie = generalmanager.retriveAllManager(CategoriaBean.class.newInstance());
			editori = generalmanager.retriveAllManager(EditoreBean.class.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonObject risultato = new JsonObject();
	    risultato.add("autori", gson.toJsonTree(autori));
	    risultato.add("categorie", gson.toJsonTree(categorie));
	    risultato.add("editori", gson.toJsonTree(editori));
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(risultato.toString());
	}
	

	
	public void deleteACE (HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sessione= request.getSession();
		if(request.getParameter("tipoConferma").equals("editore")) {
			
			int id=Integer.parseInt(request.getParameter("inputConferma"));
			
			if(generalmanager.retriveById(new EditoreBean(), id)!=null) {
			
				generalmanager.deleteManager(id, new EditoreBean());
			};
			
			sessione.setAttribute("listaEditori", generalmanager.retriveAllManager(new EditoreBean()));
			response.sendRedirect(request.getContextPath() + "/Editori.jsp");
 
		}
		else if(request.getParameter("tipoConferma").equals("autore")) {
			
			int id=Integer.parseInt(request.getParameter("inputConferma"));
			
			if(generalmanager.retriveById(new AutoreBean(), id)!=null) {
			
				generalmanager.deleteManager(id, new AutoreBean());
			};
			sessione.setAttribute("listaAutori", generalmanager.retriveAllManager(new AutoreBean()));
			response.sendRedirect(request.getContextPath() + "/Autori.jsp");
 
		}
		else if(request.getParameter("tipoConferma").equals("categoria")) {
			
			int id=Integer.parseInt(request.getParameter("inputConferma"));
			
			if(generalmanager.retriveById(new CategoriaBean(), id)!=null) {
			
				generalmanager.deleteManager(id, new CategoriaBean());
			};
			sessione.setAttribute("listaCategorie", generalmanager.retriveAllManager(new CategoriaBean()));
			
			response.sendRedirect(request.getContextPath() + "/Categorie.jsp");
 
		}
	}
	
	public void aggiornaACE(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sessione= request.getSession();
		if(request.getParameter("inputTipo").equals("autore")) {
			
			int id=Integer.parseInt(request.getParameter("inputAggiorna"));
			AutoreBean autore= generalmanager.retriveById(new AutoreBean(), id);
			String nomeAutore= request.getParameter("nomeAutore"+id);
			String cognomeAutore= request.getParameter("cognomeAutore"+id);
			System.out.println(nomeAutore + cognomeAutore);
			if(!acemanager.CheckACE(autore, nomeAutore,cognomeAutore)) {
				System.out.println("esiste gia . errore=1");
				
				sessione.setAttribute("listaAutori", generalmanager.retriveAllManager(new AutoreBean()));
				response.sendRedirect(request.getContextPath() + "/Autori.jsp?errore=1");
				return;
			}
			

			
			autore.setNome(nomeAutore);
			autore.setCognome(cognomeAutore);
			
			generalmanager.updateManager(autore);
			sessione.setAttribute("listaAutori", generalmanager.retriveAllManager(new AutoreBean()));
			
			response.sendRedirect(request.getContextPath() + "/Autori.jsp?errore=0");
			
		} else if(request.getParameter("inputTipo").equals("categoria")) {
			
			int id=Integer.parseInt(request.getParameter("inputAggiorna"));
			String nomeCategoria= request.getParameter("nomeCategoria"+id);
			CategoriaBean categoria= generalmanager.retriveById(new CategoriaBean(), id);
			
			if(!acemanager.CheckACE(categoria, nomeCategoria,null)) {
				
				request.setAttribute("errore", 1);
				sessione.setAttribute("listaCategorie", generalmanager.retriveAllManager(new CategoriaBean()));
				response.sendRedirect(request.getContextPath() + "/Categorie.jsp?errore=1");
				return;
			}

			
			categoria.setNome(nomeCategoria);
			categoria.setDescrizione(request.getParameter("descrizioneCategoria"+id));
			
			generalmanager.updateManager(categoria);
			sessione.setAttribute("listaCategorie", generalmanager.retriveAllManager(new CategoriaBean()));
			
			response.sendRedirect(request.getContextPath() + "/Categorie.jsp?errore=0");
			
		}else if(request.getParameter("inputTipo").equals("editore")) {
			int id=Integer.parseInt(request.getParameter("inputAggiorna"));
			EditoreBean editore= generalmanager.retriveById(new EditoreBean(), id);
			String nome = request.getParameter("nomeEditore"+id);
			
			if(!acemanager.CheckACE(editore, nome,null)) {
				request.setAttribute("errore", 1);
				sessione.setAttribute("listaEditori", generalmanager.retriveAllManager(new EditoreBean()));
				response.sendRedirect(request.getContextPath() + "/Editori.jsp?errore=1");
				return;
			}

			editore.setNome(nome);
			generalmanager.updateManager(editore);
			
			sessione.setAttribute("listaEditori", generalmanager.retriveAllManager(new EditoreBean()));
			response.sendRedirect(request.getContextPath() + "/Editori.jsp?errore=0");
			
		}
	}
	
	
 
	
	

}
