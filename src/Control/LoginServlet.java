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
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.AmministratoreManager;
import Manager.GeneralManager;
import Manager.LoginManager;
import Manager.UtenteManager;

 
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final LoginManager loginmanager= new LoginManager();
	private final GeneralManager generalmanager= new GeneralManager();
    
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
	        if(profilo ==null) {
	        	 response.sendRedirect(request.getContextPath() + "/Login.jsp?errore=1");
	        }else {
	        	sessione.setAttribute("Profilo", profilo);
	        	if(profilo instanceof UtenteBean) {
	        		System.out.println("sono in utente");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				    dispatcher.forward(request, response);
	             
	        	}else {
	        		List<AutoreBean> autori = generalmanager.retriveAllManager(new AutoreBean());
	        		List<EditoreBean> editori= generalmanager.retriveAllManager(new EditoreBean());
	        		List<CategoriaBean> categorie = generalmanager.retriveAllManager(new CategoriaBean());
	        		
	        		sessione.setAttribute("listaCategorie", categorie);
	        		sessione.setAttribute("listaEditori", editori);
	        		sessione.setAttribute("listaAutori", autori);
					RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
				    dispatcher.forward(request, response);
	        		
		             
		             return;
	        	}
	        }
	        
		
		


		
	}
	
	
	
	

}
