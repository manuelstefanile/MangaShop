package Beans;

public class CarrelloBean extends General {
	private int id;
	private String utente;
	private Integer manga;
	private int quantita;
	
	
	public CarrelloBean() {
		super.setNomeTabella("Carrello");
	}
	
	public CarrelloBean(String utente, Integer manga, int quantita) {
		super();
		
		this.utente = utente;
		this.manga = manga;
		this.quantita = quantita;
		
		super.setNomeTabella("Carrello");
	}
	@Override
	public String toString() {
		return "Carrello [id=" + id + ", utente=" + utente + ", manga=" + manga + ", quantita=" + quantita;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public Integer getManga() {
		return manga;
	}
	public void setManga(Integer manga) {
		this.manga = manga;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	
	
}
