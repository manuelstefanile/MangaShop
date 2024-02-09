package Manager;

import java.sql.SQLException;
import java.util.List;

import Beans.UtenteBean;
import Dao.UtenteDao;

public class UtenteManager extends GeneralManager<UtenteDao> {
	private UtenteDao utentedao;
	
	
	
	public UtenteManager() {
		super();
		this.utentedao=new UtenteDao();
	}
	
	public boolean insertUtente(UtenteBean oggetto) {
		List<UtenteBean> listaUtente=null;
		try {
			listaUtente = utentedao.retriveAll(oggetto);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/*se l'email è già presente return false altrimenti inser*/
		for(UtenteBean u:listaUtente) {
			if(u.getEmail().equals(oggetto.getEmail())) {
				return false;
			}
		}
			try {
				utentedao.insert(oggetto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		
	}
	
	

}
