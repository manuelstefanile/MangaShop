package Control;


import java.sql.Date;




import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import Dao.GeneralDao;
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

 
@WebServlet("/OrdiniServlet")
@MultipartConfig
public class OrdiniServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final GeneralManager<GeneralDao> generalmanager= new GeneralManager<GeneralDao>();
	
	
    
    public OrdiniServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession();
		Profilo utente=(Profilo) sessione.getAttribute("Profilo");
		if(utente!=null) {
			HashMap<OrdineBean, List<ProdottoOrdineBean>> listaOrdini=new HashMap<OrdineBean, List<ProdottoOrdineBean>>();
			List<OrdineBean> ordini=generalmanager.retriveByCampoManager(new OrdineBean(), "utente", utente.getEmail());
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
			 
			
			sessione.setAttribute("listaOrdini", ordiniOrdinati);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Ordini.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		 

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
			

		
	}
	
	
	/*******************************************************METODI***********************************************************/
	
	

}
