package Manager;






import Dao.AmministratoreDao;


public class AmministratoreManager extends GeneralManager<AmministratoreDao>{
	
	private AmministratoreDao amministratoredao;
	
	
	
	public AmministratoreManager() {
		super();
		this.amministratoredao=new AmministratoreDao();
		
	}
	
	
	
	
}
