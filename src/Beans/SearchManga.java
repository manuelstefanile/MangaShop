package Beans;

public class SearchManga extends General implements InterfaceACE{
	private int id;
	private String nome;
	private String cover ;
	
	
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public SearchManga() {


	}


	
	@Override
	public String toString() {
		return "Editore [id=" + id + ", nome=" + nome + "]";
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
	
	
	

}
