package Beans;

public class CategoriaBean extends General implements InterfaceACE{
	private int id;
	private String nome;
	private String descrizione;
	
	
	
	public CategoriaBean() {
		super.setNomeTabella("Categoria");
		// TODO Auto-generated constructor stub
	}
	public CategoriaBean(String nome, String descrizione) {
		super();
		
		this.nome = nome;
		this.descrizione = descrizione;
		super.setNomeTabella("Categoria");
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + "]";
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
