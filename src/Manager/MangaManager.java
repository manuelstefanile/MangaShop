package Manager;

import java.sql.SQLException;

import java.util.List;

import Beans.AutoreBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.ImmaginiMangaBean;
import Beans.MangaBean;
import Dao.GeneralDao;
import Dao.MangaDao;
import Dao.UtenteDao;

public class MangaManager extends GeneralManager<UtenteDao>{
	
	private GeneralDao generaldao;
	private MangaDao mangadao;
	
	
	
	public MangaManager() {
		super();
		
		this.generaldao=new GeneralDao();
		this.mangadao= new MangaDao();
	}
	
	public int[] returnId(String cat,String au,String ed) {
		int[] id= {0,0,0};
			try {
				
				List<AutoreBean> listaautori= generaldao.retriveAll(AutoreBean.class.newInstance());
				List<CategoriaBean> listacategoria= generaldao.retriveAll(CategoriaBean.class.newInstance());
				List<EditoreBean> listaeditori= generaldao.retriveAll(EditoreBean.class.newInstance());
				
				for (AutoreBean autore: listaautori) {
					String nome_cognome= autore.getNome() +" "+ autore.getCognome();
					System.out.println("string au = "+ au);
					System.out.println("string nome cohnome = "+ nome_cognome);
					
					if(nome_cognome.equals(au)) {
						
						id[1]=autore.getId();
					}
					
				}
				for (EditoreBean editore: listaeditori) {
					if(editore.getNome().equals(ed))
						id[2]=editore.getId();
				}
				for (CategoriaBean categoria: listacategoria) {
					if(categoria.getNome().equals(cat))
						id[0]=categoria.getId();
				}
				
			} catch (InstantiationException | IllegalAccessException | SQLException e) {

				e.printStackTrace();
			}
			
			return id;
	}
	
	//inserisce l immaagine del manga
	public Integer insertImageMangaManager(ImmaginiMangaBean immage) {

		if(immage.getCover().length==0&&immage.getPersonaggio().length==0&&immage.getTitolo().length==0) {
			
			return null;
		}
		else {
			try {
				return generaldao.insert(immage);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}
	
	/*controlla se ci sono duplicati nel nome del manga*/
	public Integer controlloDuplicateNameManga(String nomeManga) {
		try {
			List<MangaBean> listaManga=generaldao.retriveAll(new MangaBean());
			for(MangaBean manga :listaManga) {
				
				if(manga.getNome().equals(nomeManga)) {
					
					return null;
				}
			}
			return 1;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	
	

	public boolean comparaManga(MangaBean manga1, MangaBean mangaDB, ImmaginiMangaBean img1,ImmaginiMangaBean imgDB) {
		
		
		if(!(manga1.getNome().equals(mangaDB.getNome()))){
		 
			return true;
		}if(manga1.getPrezzo()!=mangaDB.getPrezzo()) {
		 
			return true;
		}if(manga1.getQuantita()!=mangaDB.getQuantita()) {
			 
			return true;
		}if(manga1.getDisponibilita()!=mangaDB.getDisponibilita()) {
			 
			return true;
		}if(manga1.getAutore()!=mangaDB.getAutore()) {
			 
			return true;
		}if(manga1.getCategoria()!=mangaDB.getCategoria()) {
			 
			return true;
		}if(manga1.getEditore()!=mangaDB.getEditore()) {
			 
			return true;
		}if(!(manga1.getData_rilascio().equals(mangaDB.getData_rilascio()))) {
		 
			return true;
		}if(!(manga1.getDescrizione().equals(mangaDB.getDescrizione()))) {
			 
			return true;
		}if(!(manga1.getRilegatura().equals(mangaDB.getRilegatura()))) {
			 
			return true;
		}
		
		if(img1.getCover()!=imgDB.getCover()) {
			 
			return true;
		}if(img1.getPersonaggio()!=imgDB.getPersonaggio()) {
			 
			return true;
		}if(img1.getTitolo()!=imgDB.getTitolo()) {
			 
			return true;
		}
		return false;
		
}
	public List<MangaBean> retriveMangaLimit(int limite,int idCategoria) {
		return mangadao.RetriveMangaLimit(limite,idCategoria);
		
	}
	//ritorna i manga dell'ultima settimana
	public List<MangaBean> retriveMangaNovita() {
		return mangadao.RetriveMangaNovitaSettimana();
		
	}
	
	public List<MangaBean> RetriveMangaParole(String parola){
		return mangadao.RetriveMangParola(parola);
	}
}

