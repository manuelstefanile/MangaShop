package Beans;

import java.util.Arrays;

public class ProdottoOrdineBean extends General {
	private int id;
	private byte[] cover_manga;
	private String titolo_manga;
	private int manga;
	
	
	
	public ProdottoOrdineBean(int id, byte[] cover_manga, String titolo_manga, int manga) {
		super();
		this.id = id;
		this.cover_manga = cover_manga;
		this.titolo_manga = titolo_manga;
		this.manga = manga;
		super.setNomeTabella("ProdottoOrdine");
	}
	@Override
	public String toString() {
		return "ProdottoOrdine [id=" + id + ", cover_manga=" + Arrays.toString(cover_manga) + ", titolo_manga="
				+ titolo_manga + ", manga=" + manga + "]";
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
