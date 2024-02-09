package Beans;

import java.util.Arrays;

public class ProdottoOrdineBean extends General {
	private int id;
	private byte[] cover_manga;
	private String titolo_manga;
	private Integer id_ordine;
	private int manga;
	private int quantita;
	private float prezzo_acquisto;
	
	
	public ProdottoOrdineBean() {
		super();
		super.setNomeTabella("ProdottoOrdine");
	}
	
	public ProdottoOrdineBean(byte[] cover_manga, String titolo_manga, int manga,Integer id_ordine,int quantita,float prezzo_acquisto) {
		super();
		
		this.cover_manga = cover_manga;
		this.titolo_manga = titolo_manga;
		this.manga = manga;
		this.id_ordine = id_ordine;
		this.quantita=quantita;
		this.prezzo_acquisto=prezzo_acquisto;
		super.setNomeTabella("ProdottoOrdine");
	}
	
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getPrezzo_acquisto() {
		return prezzo_acquisto;
	}

	public void setPrezzo_acquisto(float prezzo_acquisto) {
		this.prezzo_acquisto = prezzo_acquisto;
	}

	public Integer getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(Integer id_ordine) {
		this.id_ordine = id_ordine;
	}
	
	@Override
	public String toString() {
		return "ProdottoOrdineBean [id=" + id + ", cover_manga=" + Arrays.toString(cover_manga) + ", titolo_manga="
				+ titolo_manga + ", id_ordine=" + id_ordine + ", manga=" + manga + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getCover_manga() {
		return cover_manga;
	}
	public void setCover_manga(byte[] cover_manga) {
		this.cover_manga = cover_manga;
	}
	public String getTitolo_manga() {
		return titolo_manga;
	}
	public void setTitolo_manga(String titolo_manga) {
		this.titolo_manga = titolo_manga;
	}
	public int getManga() {
		return manga;
	}
	public void setManga(int manga) {
		this.manga = manga;
	}
	
	
}
