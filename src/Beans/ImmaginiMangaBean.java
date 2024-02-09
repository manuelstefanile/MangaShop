package Beans;

import java.util.Arrays;

public class ImmaginiMangaBean extends General{
	private int id;
	 private byte[] cover;
	 private byte[] titolo;
	 private byte[] personaggio;
	 
	 
	public ImmaginiMangaBean () {
		super.setNomeTabella("ImmaginiManga");
	}
	public ImmaginiMangaBean( byte[] cover, byte[] titolo, byte[] personaggio) {
		super();
		
		this.cover = cover;
		this.titolo = titolo;
		this.personaggio = personaggio;
		super.setNomeTabella("ImmaginiManga");
	}
	
	@Override
	public String toString() {
		return "ImmaginiManga [id=" + id + ", cover=" + Arrays.toString(cover) + ", titolo=" + Arrays.toString(titolo)
				+ ", personaggio=" + Arrays.toString(personaggio) + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getCover() {
		return cover;
	}
	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	public byte[] getTitolo() {
		return titolo;
	}
	public void setTitolo(byte[] titolo) {
		this.titolo = titolo;
	}
	public byte[] getPersonaggio() {
		return personaggio;
	}
	public void setPersonaggio(byte[] personaggio) {
		this.personaggio = personaggio;
	}
	 
	 
}
