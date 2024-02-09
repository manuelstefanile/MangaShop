package Beans;

public class AutoreBean extends General implements InterfaceACE{
	private int id;
	private String nome;
	private String cognome;
	
	public AutoreBean() {
		super.setNomeTabella("Autore");
	}
	
	public AutoreBean( String nome, String cognome) {
		super();
		
		this.nome = nome;
		this.cognome = cognome;
		super.setNomeTabella("Autore");
	}
	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	


}
