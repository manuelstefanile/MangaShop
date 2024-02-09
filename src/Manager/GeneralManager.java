package Manager;

import java.sql.SQLException;
import java.util.List;

import Beans.CategoriaBean;
import Beans.General;
import Dao.GeneralDao;


/* Prendo il .class del dao che mi serve. Lo istanzio e lo uso, richiamando 
 * i metodi della superClasse GeneralDao*/
public class GeneralManager<L extends GeneralDao> {
	
	public  GeneralDao generaldao ;
	
	public GeneralManager() {
		generaldao= new GeneralDao();
	}
	
	public<T extends General> Integer insertManager(T oggetto) {
		
		try {
			return generaldao.insert(oggetto);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
		
	}
	
	public<T extends General> void deleteManager(Object id, T oggetto) {
		try {
			generaldao.delete(id, oggetto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public<T extends General> Object updateManager(T oggetto) {
		try {
			return generaldao.update(oggetto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	/*oggetto deve essere un bean*/
	public<T extends General> List<T> retriveAllManager(T oggetto) {
		List<T> lista=null;
		try {
			 lista= generaldao.retriveAll(oggetto);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
		
	}
	public<T extends General> T retriveById(T oggetto, Object id) {
		
		
			 try {
				return generaldao.retriveById(oggetto, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

	}

	public<T extends General> List<T> retriveByCampoManager(T oggetto, Object campo, Object id) {
		
		return generaldao.retriveByCampo(oggetto, campo, id);
		
	}
}
