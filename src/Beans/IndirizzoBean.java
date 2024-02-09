package Beans;

public class IndirizzoBean extends General{
	private int id;
	private String utente;
	private String regione;
	private String provincia;
	private String citta;
	private int cap;
	private String via;
	
	public IndirizzoBean() {
		super.setNomeTabella("Indirizzo");
	}
	
	public IndirizzoBean(String utente,String regione, String provincia, String citta, int cap, String via) {
		super();
		this.utente=utente;
		this.regione = regione;
		this.provincia = provincia;
		this.citta = citta;
		this.cap = cap;
		this.via = via;
		super.setNomeTabella("Indirizzo");
	}
	
	
	@Override
	public String toString() {
		return "IndirizzoBean [id=" + id + ", utente=" + utente + ", regione=" + regione + ", provincia=" + provincia
				+ ", citta=" + citta + ", cap=" + cap + ", via=" + via + "]";
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	
	

}
