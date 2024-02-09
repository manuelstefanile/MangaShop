package Beans;

public class UtenteBean extends General implements Profilo {

	private String email;
	private String password;
	private String telefono;
	private String nome;
	private String cognome;
	
	public UtenteBean() {
		super.setNomeTabella("Utente");
	}
	
	public UtenteBean(String email, String password, String telefono, String nome, String cognome) {
		super();
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.nome = nome;
		this.cognome = cognome;
		super.setNomeTabella("Utente");
		
	}
	@Override
	public String toString() {
		return "UtenteBeans [email=" + email + ", password=" + password + ", telefono=" + telefono + ", nome=" + nome
				+ ", cognome=" + cognome + "]";
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
