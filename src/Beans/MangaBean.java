package Beans;

import java.sql.Date;

public class MangaBean extends General {
	private int id;
	private String nome;
	private int quantita;
	private float prezzo;
	private boolean disponibilita;
	private int categoria;
	private int autore;
	private int editore;
	private String rilegatura;
	private Date data_rilascio;
	private String descrizione;
	private Integer immagini_manga;
	
	
	
	public MangaBean( String nome, int quantita, float prezzo, boolean disponibilita, int categoria, int autore,
			int editore, String rilegatura, Date data_rilascio, String descrizione, Integer immagini_manga) {
		super();
		
		this.nome = nome;
		this.quantita = quantita;
		this.prezzo = prezzo;
		this.disponibilita = disponibilita;
		this.categoria = categoria;
		this.autore = autore;
		this.editore = editore;
		this.rilegatura = rilegatura;
		this.data_rilascio = data_rilascio;
		this.descrizione = descrizione;
		this.immagini_manga = immagini_manga;
		super.setNomeTabella("Manga");
	}
	public MangaBean() {
		super.setNomeTabella("Manga");
	}
	@Override
	public String toString() {
		return "Manga [id=" + id + ", nome=" + nome + ", quantita=" + quantita + ", prezzo=" + prezzo
				+ ", disponibilita=" + disponibilita + ", categoria=" + categoria + ", autore=" + autore + ", editore="
				+ editore + ", rilegatura=" + rilegatura + ", date_rilascio=" + data_rilascio + ", descrizione="
				+ descrizione + ", immagini_manga=" + immagini_manga + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public boolean getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getAutore() {
		return autore;
	}
	public void setAutore(int autore) {
		this.autore = autore;
	}
	public int getEditore() {
		return editore;
	}
	public void setEditore(int editore) {
		this.editore = editore;
	}
	public String getRilegatura() {
		return rilegatura;
	}
	public void setRilegatura(String rilegatura) {
		this.rilegatura = rilegatura;
	}
	public Date getData_rilascio() {
		return data_rilascio;
	}
	public void setData_rilascio(Date date_rilascio) {
		this.data_rilascio = date_rilascio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getImmagini_manga() {
		return immagini_manga;
	}
	public void setImmagini_manga(Integer immagini_manga) {
		this.immagini_manga = immagini_manga;
	}
	
	
}
