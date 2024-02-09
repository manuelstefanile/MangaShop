package Manager;

import java.sql.SQLException;

import java.util.List;

import Beans.AmministratoreBean;
import Beans.General;
import Beans.Profilo;
import Beans.UtenteBean;
import Dao.AmministratoreDao;
import Dao.GeneralDao;
import Dao.UtenteDao;

public class LoginManager  {
	private UtenteDao utentedao;
	private AmministratoreDao amministratoredao;
	
	
	
	public LoginManager() {
		this.utentedao=new UtenteDao();
		this.amministratoredao=new AmministratoreDao();
	}
	
	/*controlla se esiste email e pass in utente o amministratore*/
	public  Profilo login(String email, String password) {
		try {
			AmministratoreBean ambean= AmministratoreBean.class.newInstance();
			
			List<AmministratoreBean> listaammin= amministratoredao.retriveAll(ambean);
			if(listaammin !=null) {
				for(AmministratoreBean amm: listaammin) {
					if(amm.getEmail().equals(email)) {
						if(amm.getPassword().equals(password)) {
							return  amm;
						}else break;
					}
				}
			}
			List<UtenteBean> listautente= utentedao.retriveAll(UtenteBean.class.newInstance());
			if(listautente!=null) {
				for(UtenteBean ut: listautente) {
					if(ut.getEmail().equals(email)) {
						if(ut.getPassword().equals(password)) {
							return ut;
						}else return null;
					}
				}
			}
		}catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
