package Control;

import java.io.IOException;
import java.util.ArrayList;
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

import Beans.ImmaginiMangaBean;
import Beans.CategoriaBean;
import Beans.MangaBean;
import Beans.Manga_Img;
import Beans.Profilo;
import Beans.UtenteBean;
import Manager.GeneralManager;
import Manager.MangaManager;



 
@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
	
	private final GeneralManager<?> generalmanager= new GeneralManager();
	private final MangaManager mangamanager= new MangaManager();
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessione= request.getSession();
		Profilo profilo = (Profilo) sessione.getAttribute("Profilo");
		if(profilo instanceof UtenteBean || profilo == null) {
			//setta listaCategorie , restituendo i manga e le loro img
			//in questo caso , prendo quelli della catecoria con id 19,20,21
			List<MangaBean> lista1=mangamanager.retriveMangaLimit(4, 19);
			List<MangaBean> lista2=mangamanager.retriveMangaLimit(4, 20);
			List<MangaBean> lista3=mangamanager.retriveMangaLimit(4, 21);
			
			List<Manga_Img> lista1Compl1=new ArrayList<Manga_Img>();
			List<Manga_Img> lista1Compl2=new ArrayList<Manga_Img>();
			List<Manga_Img> lista1Compl3=new ArrayList<Manga_Img>();
			
			addManga_img(lista1,lista1Compl1);
			addManga_img(lista2,lista1Compl2);
			addManga_img(lista3,lista1Compl3);
			
			String nomeCategoria1=mangamanager.retriveById(new CategoriaBean(), 19).getNome();
			String nomeCategoria2=mangamanager.retriveById(new CategoriaBean(), 20).getNome();
			String nomeCategoria3=mangamanager.retriveById(new CategoriaBean(), 21).getNome();
			
			Map<String,List<Manga_Img>> mappalista = new HashMap<String, List<Manga_Img>>();
			
			mappalista.put(nomeCategoria1, lista1Compl1);
			mappalista.put(nomeCategoria2, lista1Compl2);
			mappalista.put(nomeCategoria3,lista1Compl3);
			
			sessione.setAttribute("mappalista", mappalista);
			List<CategoriaBean> categorie = generalmanager.retriveAllManager(new CategoriaBean());
			
			sessione.setAttribute("listaCategorie", categorie);
			response.sendRedirect("Home.jsp");
			
		}else response.sendRedirect("AdminHome.jsp");
		
		
		

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
    /*creo una struttura dati con manga e immagini*/
    public void addManga_img(List<MangaBean> lista, List<Manga_Img> listaCompleta) {
    	for(MangaBean manga : lista) {
			Manga_Img mangaCompl=new Manga_Img();
			mangaCompl.setId(manga.getId());
			mangaCompl.setNome(manga.getNome());
			mangaCompl.setPrezzo(manga.getPrezzo());
			ImmaginiMangaBean imm1=  mangamanager.retriveById(new ImmaginiMangaBean(), manga.getImmagini_manga());
			mangaCompl.setCover(imm1.getCover());
			mangaCompl.setTitolo(imm1.getTitolo());
			mangaCompl.setPersonaggio(imm1.getPersonaggio());
			listaCompleta.add(mangaCompl);
		}
    }
}