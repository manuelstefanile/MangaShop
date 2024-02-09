package Beans;

import java.util.Arrays;
/*classe che contiene i dati essenziali come nome... e img*/
public class Manga_Img {
	private int id;
	private String nome;
	private float prezzo;
	private byte[] cover;
	private byte[] titolo;
	private byte[] personaggio;
	
	public Manga_Img() {}
	
	public Manga_Img(int id, String nome, float prezzo, byte[] cover, byte[] titolo, byte[] personaggio) {
		super();
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.cover = cover;
		this.titolo = titolo;
		this.personaggio = personaggio;
	}
	@Override
	public String toString() {
		return "Manga_Img [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + ", cover=" + Arrays.toString(cover)
				+ ", titolo=" + Arrays.toString(titolo) + ", personaggio=" + Arrays.toString(personaggio) + "]";
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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
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
