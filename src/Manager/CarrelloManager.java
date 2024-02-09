package Manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.AutoreBean;
import Beans.CarrelloBean;
import Beans.CategoriaBean;
import Beans.EditoreBean;
import Beans.ImmaginiMangaBean;
import Beans.MangaBean;
import Dao.GeneralDao;
import Dao.MangaDao;
import Dao.UtenteDao;

public class CarrelloManager extends GeneralManager<GeneralDao>{
	
	
	
	
	
	
	public CarrelloManager() {
		super();
		
		super.generaldao=new GeneralDao();
		
	}
	
	public boolean insertManager(List<CarrelloBean> carrello ,CarrelloBean carrelloDaIns) {
		//carrello>0 utente
		if(carrello.size()>0) {
		
			for (CarrelloBean carrelloLista: carrello) {
				
				//se voglio inserire un altro manga di quelli che gia ci sono nel carrello
		
		
				if(carrelloLista.getManga()==carrelloDaIns.getManga()) {
					
					//
					
				
					//	se posso aggiungere allora: setta la quantita del carrello attuale a quella nuova		
						try {
							aggiornaMangaQuantita(carrelloDaIns.getManga(),carrelloDaIns.getQuantita());
							carrelloDaIns.setQuantita(carrelloLista.getQuantita()+carrelloDaIns.getQuantita());
							
							
							carrelloLista.setQuantita(carrelloDaIns.getQuantita());
							generaldao.update(carrelloLista);
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
						
				}
				
			}
			
			try {
				generaldao.insert(carrelloDaIns);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				aggiornaMangaQuantita(carrelloDaIns.getManga(),carrelloDaIns.getQuantita());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
			
		//carrello vuoto Utente
		}else {
			try {
			
				generaldao.insert(carrelloDaIns);
				aggiornaMangaQuantita(carrelloDaIns.getManga(),carrelloDaIns.getQuantita());
				return true;
			} catch (SQLException e) {
			// 	TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//aggiorna il carrello dell utente non registrato o loggato
	public void aggiornaCarrelloSessione(List<CarrelloBean> carrello ,CarrelloBean carrelloDaIns) {
		if(carrello.size()>0) {
			for (CarrelloBean carrelloLista: carrello) {
		
				if(carrelloLista.getManga()==carrelloDaIns.getManga()) {
					
					//	se posso aggiungere allora: setta la quantita del carrello attuale a quella nuova		
						
							//indeciso se settare comunque la quantita del manga...
							
							carrelloDaIns.setQuantita(carrelloLista.getQuantita()+carrelloDaIns.getQuantita());
							carrelloLista.setQuantita(carrelloDaIns.getQuantita());
						
							return;
				
				
				}
			}
			
			carrello.add(carrelloDaIns);
			return;
		//carrello vuoto Utente
		}else {
			
				carrello.add(carrelloDaIns);
				return;
	
		}
		
		
	}
	
	
	private void aggiornaMangaQuantita(Integer idManga, Integer quantita) throws SQLException {
		//devo diminuire i manga disponibili
		MangaBean mangaRid=generaldao.retriveById(new MangaBean(), idManga);
		mangaRid.setQuantita(mangaRid.getQuantita()-quantita);
		System.out.println("manga id = " + idManga);
		System.out.println("quantita da aggiornare = " + mangaRid.getQuantita());
		
		generaldao.update(mangaRid);
	}
	
	
	//controlla se il manga che voglio inserire ha le copie.
	public boolean controlloPrelievoManga(Integer id,Integer quantita) {
		MangaBean manga=super.retriveById(new MangaBean(), id);
		System.out.println("controlloPrelievo mangaq = " + manga.getQuantita() + "quantita che voglio add " + quantita);
		if(manga.getQuantita()>=quantita) {
			return true;
		}else return false;
	}
	
	public List<CarrelloBean>  aggiungiCarrelloSessione(List<CarrelloBean> carrelloUtente,List<CarrelloBean> carrellosessione,String email) throws SQLException {
		ArrayList<Integer> mangaNonAdd= new ArrayList<Integer>();
			if(carrellosessione.size()==0) {
				
				return carrelloUtente;
			} else {
				System.out.println("carrelloSessione >0");
				//carrello utente vuoto
				if(carrelloUtente.size()==0) {
					for(CarrelloBean carrelloSessionetemp: carrellosessione) {
						//se 
							boolean check = controlloPrelievoManga(carrelloSessionetemp.getManga(), carrelloSessionetemp.getQuantita());
							if(check) {
								carrelloSessionetemp.setUtente(email);
								carrelloUtente.add(carrelloSessionetemp);
								//
								aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloSessionetemp.getQuantita());
								
								
							}else {
								carrelloSessionetemp.setQuantita(generaldao.retriveById(new MangaBean(), carrelloSessionetemp.getManga()).getQuantita());
								carrelloSessionetemp.setUtente(email);
								carrelloUtente.add(carrelloSessionetemp);
								//
								aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloSessionetemp.getQuantita());
							}
						
					}
					
					return carrelloUtente;
				}
				else {
					System.out.println("carrelloUtente e sessione >0 e");
					for(CarrelloBean carrelloSessionetemp: carrellosessione) {
						for (CarrelloBean carrelloUtentetemp : carrelloUtente) {
							if(carrelloUtentetemp.getManga()==carrelloSessionetemp.getManga()) {
								mangaNonAdd.add(carrelloUtentetemp.getManga());
								System.out.println("manga uguali ");
								int quantita= carrelloSessionetemp.getQuantita();
								System.out.println("quantita totale da aggiungere "+ quantita);
								boolean check = controlloPrelievoManga(carrelloUtentetemp.getManga(), quantita);
								System.out.println("posso : " + check);
								//posso aggiornare perchè ci sono piu manga di quanto richiedo
								if(check) {
									//
									carrelloUtentetemp.setQuantita(quantita+carrelloUtentetemp.getQuantita());
									aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloSessionetemp.getQuantita());
									
									
								//ne ho richiesti troppi quindi setto al massimo	
								}else {
									carrelloUtentetemp.setQuantita(generaldao.retriveById(new MangaBean(), carrelloUtentetemp.getManga()).getQuantita());
									//
									aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloUtentetemp.getQuantita());
								}
							}
						}
					}
					//aggiornamento carrelloutente quelli non uguali , quindi i manga nuovi da inserire
					for(CarrelloBean carrelloSessionetemp: carrellosessione) {
						
						if(!(mangaNonAdd.contains(carrelloSessionetemp.getManga()))) {
							boolean check = controlloPrelievoManga(carrelloSessionetemp.getManga(), carrelloSessionetemp.getQuantita());
							if(check) {
								aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloSessionetemp.getQuantita());
								carrelloSessionetemp.setUtente(email);
								carrelloUtente.add(carrelloSessionetemp);
							}else {
								carrelloSessionetemp.setQuantita(generaldao.retriveById(new MangaBean(), carrelloSessionetemp.getManga()).getQuantita());
								aggiornaMangaQuantita(carrelloSessionetemp.getManga(),carrelloSessionetemp.getQuantita());
								carrelloSessionetemp.setUtente(email);
								carrelloUtente.add(carrelloSessionetemp);
							}
						}
					}
					
					
					
					return carrelloUtente;
				}
			}
			
		
	}
}