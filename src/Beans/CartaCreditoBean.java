package Beans;

import java.sql.Date;

public class CartaCreditoBean extends General{
	private int id;
	private String utente;
	private String codice;
	private Date data_scadenza;
	private int cvc;
	private String nome;
	
	
	public CartaCreditoBean() {
		super.setNomeTabella("CartaCredito");
	}
	
	public CartaCreditoBean( String utente, String codice, Date data_scadenza, int cvc, String nome) {
		
		
		this.utente = utente;
		this.codice = codice;
		
		this.data_scadenza = data_scadenza;
		this.cvc = cvc;
		this.nome = nome;
		super.setNomeTabella("CartaCredito");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente= utente;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getData_scadenza() {
		return data_scadenza;
	}
	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
	public int getCvc() {
		return cvc;
	}
	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "CartaCredito [id=" + id + ", utente=" + utente + ", codice=" + codice 
				+ " data_scadenza=" + data_scadenza + ", cvc=" + cvc + ", nome=" + nome + "]";
	}
	
	
	
	
	
	
}
