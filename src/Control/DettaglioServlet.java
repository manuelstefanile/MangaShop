package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.env.AutomaticModuleNaming;

import Beans.ImmaginiMangaBean;
import Beans.AutoreBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.MangaBean;
import Beans.Manga_Img;
import Manager.GeneralManager;
import Manager.MangaManager;



 
@WebServlet(name = "DettaglioServlet", value = "/DettaglioServlet")
public class DettaglioServlet extends HttpServlet {
	
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final MangaManager mangamanager= new MangaManager();
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession sessione= request.getSession();
    	Integer id=Integer.parseInt(request.getParameter("id"));
    	MangaBean manga=null;
		ImmaginiMangaBean immagini=null;
    	try {
    		manga= mangamanager.retriveById(new MangaBean(), id);
    		immagini= mangamanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
    	}catch (NullPointerException e) {
       	 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErroreServlet?errore=MangaNonPresente");
       	 	dispatcher.forward(request, response);
       	 	return;
		}
    	CategoriaBean categoria=mangamanager.retriveById(new CategoriaBean(), manga.getCategoria());
    	AutoreBean autore=mangamanager.retriveById(new AutoreBean(), manga.getAutore());
    	EditoreBean editore = mangamanager.retriveById(new EditoreBean(), manga.getEditore());
    	//setta i 4 manga novità per manga dettaglgio
    	if(sessione.getAttribute("mangaNovitaDettaglio")==null) {
    		List<MangaBean> listaNovita= mangamanager.retriveMangaNovita();
    		List<MangaBean> listaRidotta= listaNovita.stream().limit(4).collect(Collectors.toList());
    		List<Manga_Img> mangaImg= new ArrayList<Manga_Img>();
    		for(MangaBean mangarid :listaRidotta) {
    			
    			ImmaginiMangaBean immaginiTemp= mangamanager.retriveById(new ImmaginiMangaBean(), mangarid.getImmagini_manga());
    			mangaImg.add(new Manga_Img(mangarid.getId(),mangarid.getNome(),mangarid.getPrezzo(),
    					immaginiTemp.getCover() ,immaginiTemp.getTitolo(),immaginiTemp.getPersonaggio()));
    		}
    		
    		sessione.setAttribute("mangaNovitaDettaglio",mangaImg );
    	}
    	request.setAttribute("MangaDettaglio", manga);
    	request.setAttribute("ImmaginiDettaglio", immagini);
    	request.setAttribute("AutoreDettaglio", autore);
    	request.setAttribute("EditoreDettaglio", editore);
    	request.setAttribute("CategoriaDettaglio", categoria);
    	
    	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Dettaglio.jsp");
        dispatcher.forward(request, response);
        return;
	    //response.sendRedirect(request.getContextPath() + "/Dettaglio.jsp");
    	
		
		
		

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
 
}