package Manager;
import java.util.List;

import Beans.AutoreBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.InterfaceACE;
import Dao.GeneralDao;


public class ACEManager {
	private GeneralManager<GeneralDao> generalmanager=new GeneralManager<GeneralDao>();
	
/*controlla se il nome e (eventualmente)cognome sono uguali */
public  boolean CheckACE(InterfaceACE oggetto,String nome,String cognomeAutore) {
 
	
	if(oggetto instanceof EditoreBean) {
		
		List<EditoreBean> editori= generalmanager.retriveAllManager(new EditoreBean());
		System.out.println(editori);
		for (EditoreBean edi : editori) {
			if(nome.equals(edi.getNome()) && !(nome.equals(((EditoreBean) oggetto).getNome())))
				{
				System.out.println("è uguale il nome");
				return false;
				}
		}
	}else if(oggetto instanceof CategoriaBean) {
		
		List<CategoriaBean> categorie= generalmanager.retriveAllManager(new CategoriaBean());
		System.out.println(categorie);
		for (CategoriaBean cat : categorie) {
			if(nome.equals(cat.getNome()) && !(nome.equals(((CategoriaBean) oggetto).getNome())))
				{
				System.out.println("è uguale il nome");
				return false;
				}
		}
	}else if(oggetto instanceof AutoreBean) {
		
		List<AutoreBean> autori= generalmanager.retriveAllManager(new AutoreBean());
		System.out.println(autori);
		for (AutoreBean au : autori) {
			if(nome.equals(au.getNome()) && !(nome.equals(((AutoreBean) oggetto).getNome())))
				System.out.println("nomi uguali");
				if(cognomeAutore.equals(au.getCognome()) && !(cognomeAutore.equals(((AutoreBean) oggetto).getCognome())))
				{
					System.out.println("uguali in managerautori");
					return false;
				}
		}
	}
	return true;
	
}
	
	
	
	
}
