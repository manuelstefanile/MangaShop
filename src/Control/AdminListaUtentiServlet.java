package Control;


import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import Beans.OrdineBean;
import Beans.ProdottoOrdineBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Dao.GeneralDao;
import Manager.AmministratoreManager;

import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.MangaManager;
import Manager.UtenteManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

 
@WebServlet("/AdminListaUtentiServlet")
@MultipartConfig
public class AdminListaUtentiServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager<?> generalmanager= new GeneralManager<GeneralDao>();
	
	
    
    public AdminListaUtentiServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("utenti")!=null){
			HttpSession sessione= request.getSession();
			sessione.setAttribute("listaUtenti", generalmanager.retriveAllManager(new UtenteBean()));
			response.sendRedirect("AdminUtenti.jsp");
			return;
		}
		
		
		//reindirizza a modifica manga dettaglio
		if(request.getParameter("id")!=null) {
			Integer idManga =Integer.parseInt(request.getParameter("id"));
			
			MangaBean mangaDB = generalmanager.retriveById(new MangaBean(), idManga);
			ImmaginiMangaBean imgDB=null;
			
			//se non ci riesci , vuol dire che è stato eliminato il mangs
			try {
				imgDB= generalmanager.retriveById(new ImmaginiMangaBean(), mangaDB.getImmagini_manga());
			}catch (Exception e) {
				response.sendRedirect("errore.jsp");
				return;
				//fai qualcosa errore
			}
			
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
			
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String emailUtente=(request.getParameter("emailOrdini"));
		 if(emailUtente!=null) {
			 HttpSession sessione=request.getSession();
				HashMap<OrdineBean, List<ProdottoOrdineBean>> listaOrdini=new HashMap<OrdineBean, List<ProdottoOrdineBean>>();
				List<OrdineBean> ordini=generalmanager.retriveByCampoManager(new OrdineBean(), "utente", emailUtente);
				//ordino gli ordini in base alla data di acquisto
				 
				for(OrdineBean ord:ordini) {
					
					List<ProdottoOrdineBean> prod = generalmanager.retriveByCampoManager(new ProdottoOrdineBean(), "id_ordine", ord.getId());
					listaOrdini.put(ord,prod);
				}
				LinkedHashMap<OrdineBean, List<ProdottoOrdineBean>> ordiniOrdinati = listaOrdini.entrySet()
		                .stream()
		                .sorted(Map.Entry.<OrdineBean, List<ProdottoOrdineBean>>comparingByKey(Comparator.comparing(OrdineBean::getData_acquisto)).reversed())
		                .collect(Collectors.toMap(
		                        Map.Entry::getKey,
		                        Map.Entry::getValue,
		                        (e1, e2) -> e1,
		                        LinkedHashMap::new
		                ));
				 
				
				sessione.setAttribute("listaOrdiniAdmin", ordiniOrdinati);
				sessione.setAttribute("emailUtente", emailUtente.toUpperCase());
				
				response.sendRedirect("AdminUtenteOrdini.jsp");
			    return;
		 }
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	 
	
	
	

}
