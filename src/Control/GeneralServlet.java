package Control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.AmministratoreBean;
import Beans.AutoreBean;
import Beans.CartaCreditoBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.IndirizzoBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.AmministratoreManager;
import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.UtenteManager;

 
@WebServlet("/GeneralServlet")
public class GeneralServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final GeneralManager generalmanager= new GeneralManager();
	
    
    public GeneralServlet() {
        super();

    }

	 
    //manda l utente alla pagina ACE scelta, dato che è dinamica , modificandola a seconda della scelta
    //manda anche al Profilo
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("sono in general");
			String url = request.getRequestURI();
			String pageName = url.substring(url.lastIndexOf("/") + 1);
			HttpSession sessione = request.getSession();
			 
			if(pageName.equals("Autori.jsp")) {
				List <AutoreBean> listaAutori = generalmanager.retriveAllManager(new AutoreBean());
				 if(listaAutori.size()==0) {
					 AutoreBean autore=new AutoreBean(null,null);
					 autore.setId(-1);
					 listaAutori.add(autore);
				 }
				 
				 sessione.setAttribute("lista", listaAutori);
				 
				 
				 sessione.setAttribute("titoloPage", "Autori");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminACE.jsp");
				    dispatcher.forward(request, response);
				    

			} else if(pageName.equals("Editori.jsp")) {
				
				List <EditoreBean> listaEditori = generalmanager.retriveAllManager(new EditoreBean());
				
				 if(listaEditori.size()==0) {
					 EditoreBean editore=new EditoreBean(null);
					 editore.setId(-1);
					 listaEditori.add(editore);
				 }
				
				 sessione.setAttribute("lista", listaEditori);
				 
				 
				 sessione.setAttribute("titoloPage", "Editori");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminACE.jsp");
				    dispatcher.forward(request, response);

			}else if(pageName.equals("Categorie.jsp")) {
				
				List <CategoriaBean> listaCategorie = generalmanager.retriveAllManager(new CategoriaBean());
				 
				 sessione.setAttribute("lista", listaCategorie);
				 if(listaCategorie.size()==0) {
					 CategoriaBean categoria=new CategoriaBean(null,null);
					 categoria.setId(-1);
					 listaCategorie.add(categoria);
				 }
				 
				 sessione.setAttribute("titoloPage", "Categorie");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminACE.jsp");
				    dispatcher.forward(request, response);

			} else if(pageName.equals("ProfiloPage.jsp")) {
				List <CartaCreditoBean> listaCarte = generalmanager.retriveAllManager(new CartaCreditoBean());
				List <IndirizzoBean> listaIndirizzi = generalmanager.retriveAllManager(new IndirizzoBean());
				sessione.setAttribute("listaCarte", listaCarte);
				sessione.setAttribute("listaIndirizzi", listaIndirizzi);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Profilo.jsp");
			    dispatcher.forward(request, response);
				
				
			}
			
		
		
	
	        
		
		

		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}
	
	
	
	

}
