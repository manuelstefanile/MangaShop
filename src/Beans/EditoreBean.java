package Beans;

public class EditoreBean extends General implements InterfaceACE{
	private int id;
	private String nome;
	
	
	
	public EditoreBean() {
		super.setNomeTabella("Editore");
		// TODO Auto-generated constructor stub
	}

	public EditoreBean( String nome) {
		super();
		
		this.nome = nome;
		super.setNomeTabella("Editore");
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
