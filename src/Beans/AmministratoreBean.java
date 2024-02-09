package Beans;

public class AmministratoreBean extends General implements Profilo {
/*email varchar(40) primary key,
password varchar(30) not null,
telefono varchar(15) not null,
nome varchar(20) not null,
cognome varchar(30) not null*/
	private String email;
	private String password;

	private String nome;
	private String cognome;
	
	public AmministratoreBean()  {
		super.setNomeTabella("Amministratore");
	}
	
	public AmministratoreBean(String email, String password, String nome, String cognome) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		super.setNomeTabella("Amministratore");
	}
	@Override
	public String toString() {
		return "AmministratoreBean [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome="
				+ cognome + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
