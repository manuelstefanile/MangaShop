package Beans;

public class CarrelloBean extends General {
	private int id;
	private int utente;
	private int manga;
	private int quantita;
	private boolean wishilist; 
	
	
	public CarrelloBean(int id, int utente, int manga, int quantita, boolean wishilist) {
		super();
		this.id = id;
		this.utente = utente;
		this.manga = manga;
		this.quantita = quantita;
		this.wishilist = wishilist;
		super.setNomeTabella("Carrello");
	}
	@Override
	public String toString() {
		return "Carrello [id=" + id + ", utente=" + utente + ", manga=" + manga + ", quantita=" + quantita
				+ ", wishilist=" + wishilist + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUtente() {
		return utente;
	}
	public void setUtente(int utente) {
		this.utente = utente;
	}
	public int getManga() {
		return manga;
	}
	public void setManga(int manga) {
		this.manga = manga;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public boolean isWishilist() {
		return wishilist;
	}
	public void setWishilist(boolean wishilist) {
		this.wishilist = wishilist;
	}
	
	
}
