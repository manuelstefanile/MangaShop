package Control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
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
import Beans.CarrelloBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Beans.WishlistBean;
import Manager.AmministratoreManager;
import Manager.CarrelloManager;
import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.UtenteManager;

 
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final LoginManager loginmanager= new LoginManager();
	private final CarrelloManager carrellomanager= new CarrelloManager();
	private final GeneralManager<?> generalmanager= new GeneralManager<>();
    
    public LoginServlet() {
        super();

    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  HttpSession sessione = request.getSession();
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        Profilo profilo = loginmanager.login(email,password);
	        //hai sbagliato email o pass
	        if(profilo ==null) {
	        	 response.sendRedirect(request.getContextPath() + "/Login.jsp?errore=1");
	        	 return;
	        }else {
	        	sessione.setAttribute("Profilo", profilo);
	        	if(profilo instanceof UtenteBean) {
	        		List<CarrelloBean> carrello= generalmanager.retriveByCampoManager(new CarrelloBean(), "utente", profilo.getEmail());
	        		List<CarrelloBean> carrellosessione= (List<CarrelloBean>)sessione.getAttribute("carrello");
	        		
	        		
	        		//
	        		
	        		
	        		//non c è niente nel carrello allora crealo vuoto
	        		if(carrello==null) {
	        			carrello=new ArrayList<CarrelloBean>() ;
	        			
	        		} 
	        		//aggiungi al carrello utente il carrello sessione
	        		try {
						carrello=carrellomanager.aggiungiCarrelloSessione(carrello,carrellosessione,profilo.getEmail());
						
						//aggiorna il db carrello utente
						for(CarrelloBean carrellotemp: carrello) {
							
							//se il manga è nuovo da inserire allora
							if(carrellotemp.getId()==0) {
								generalmanager.insertManager(carrellotemp);
								
								//se è gia inserito aggiorna
							}else generalmanager.updateManager(carrellotemp);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        		sessione.setAttribute("carrello", carrellomanager.retriveByCampoManager(new CarrelloBean(), "utente", profilo.getEmail()));
	        		
	        		if(request.getParameter("carrelloReindirizzo").equals("1")) {
	        			response.sendRedirect(request.getContextPath() + "/Carrello");
	        		}else {
	        			response.sendRedirect("Home.jsp");
	        		}
	             
	        	}else {
	        		List<AutoreBean> autori = generalmanager.retriveAllManager(new AutoreBean());
	        		List<EditoreBean> editori= generalmanager.retriveAllManager(new EditoreBean());
	        		List<CategoriaBean> categorie = generalmanager.retriveAllManager(new CategoriaBean());
	        		
	        		sessione.setAttribute("listaCategorie", categorie);
	        		sessione.setAttribute("listaEditori", editori);
	        		sessione.setAttribute("listaAutori", autori);
	        		response.sendRedirect("AdminHome.jsp");
					/*RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
				    dispatcher.forward(request, response);
				    return;*/
		             
		       
	        	}
	        }
	        
		
		


		
	}
	
	
	
	

}
