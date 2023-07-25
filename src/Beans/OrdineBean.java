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
	private int prodotto_ordine;
	private String indirizzo;
	private String carta;
	
	
	
	public OrdineBean(int id, String stato, Date data_acquisto, Date data_consegna, float totale, int quantita,
			int prodotto_ordine, String indirizzo, String carta) {
		super();
		this.id = id;
		this.stato = stato;
		this.data_acquisto = data_acquisto;
		this.data_consegna = data_consegna;
		this.totale = totale;
		this.quantita = quantita;
		this.prodotto_ordine = prodotto_ordine;
		this.indirizzo = indirizzo;
		this.carta = carta;
		super.setNomeTabella("Ordine");
	}
	@Override
	public String toString() {
		return "OrdineBean [id=" + id + ", stato=" + stato + ", data_acquisto=" + data_acquisto + ", data_consegna="
				+ data_consegna + ", totale=" + totale + ", quantita=" + quantita + ", prodotto_ordine="
				+ prodotto_ordine + ", indirizzo=" + indirizzo + ", carta=" + carta + "]";
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
	public int getProdotto_ordine() {
		return prodotto_ordine;
	}
	public void setProdotto_ordine(int prodotto_ordine) {
		this.prodotto_ordine = prodotto_ordine;
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
