package Control;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.ImmaginiMangaBean;
import Beans.CategoriaBean;
import Beans.MangaBean;
import Beans.Manga_Img;
import Beans.SearchManga;
import Dao.GeneralDao;
import Manager.GeneralManager;
import Manager.MangaManager;



 
@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	private final MangaManager mangamanager= new MangaManager();
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<MangaBean> lista = mangamanager.RetriveMangaParole(request.getParameter("parola"));
		List<SearchManga> risposta= new ArrayList<SearchManga>();
		int max=1;
		
		for(MangaBean mangasing : lista) {
			if(max<=5) {
				SearchManga sear = new SearchManga();
				sear.setId(mangasing.getId());
				sear.setNome(mangasing.getNome());
				String cover = new String(Base64.getEncoder().encode(mangamanager.retriveById(new ImmaginiMangaBean(), mangasing.getImmagini_manga()).getCover()));
				sear.setCover(cover);
				risposta.add(sear);
				max++;
			}
		}
		Gson gson = new Gson();
        String json = gson.toJson(risposta);
        response.setContentType("application/json");
        // Scrivi il JSON nella risposta
        PrintWriter out = response.getWriter();
        out.print(json);
	 
		
		

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
 
}