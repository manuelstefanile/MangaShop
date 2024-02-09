package Beans;

import java.sql.Date;

/*Indirizzo,carta sono solo delle copie per questo sono stringhe*/
public class OrdineBean extends General {
	private int id ;
	private String stato;
	private Date data_acquisto;
	private Date data_consegna;
	private float totale;
	private int quantita;
	
	private String indirizzo;
	private String carta;
	private String nome;
	private String cognome;
	private String telefono;
	private String utente;
	
	public OrdineBean() {
		super();
		super.setNomeTabella("Ordine");
	}
	
	public OrdineBean(String stato, Date data_acquisto, float totale, int quantita,
			 String indirizzo, String carta,String nome,String cognome, String telefono,String utente) {
		super();
		
		this.stato = stato;
		this.data_acquisto = data_acquisto;
		
		this.totale = totale;
		this.quantita = quantita;
		
		this.indirizzo = indirizzo;
		this.carta = carta;
		this.nome=nome;
		this.cognome=cognome;
		this.telefono=telefono;
		this.utente=utente;
		super.setNomeTabella("Ordine");
	}
	

	
	
	@Override
	public String toString() {
		return "OrdineBean [id=" + id + ", stato=" + stato + ", data_acquisto=" + data_acquisto + ", data_consegna="
				+ data_consegna + ", totale=" + totale + ", quantita=" + quantita + ", prodotto_ordine="
				  + ", indirizzo=" + indirizzo + ", carta=" + carta + ", nome=" + nome + ", cognome="
				+ cognome + ", telefono=" + telefono + "]";
	}

	
	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Date getData_acquisto() {
		return data_acquisto;
	}
	public void setData_acquisto(Date data_acquisto) {
		this.data_acquisto = data_acquisto;
	}
	public Date getData_consegna() {
		return data_consegna;
	}
	public void setData_consegna(Date data_consegna) {
		this.data_consegna = data_consegna;
	}
	public float getTotale() {
		return totale;
	}
	public void setTotale(float totale) {
		this.totale = totale;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	
	
}
