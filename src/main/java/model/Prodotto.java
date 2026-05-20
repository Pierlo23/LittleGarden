package model;

import java.io.Serializable;

public class Prodotto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String immagineUrl;
    private boolean isCancellato;
	
    public Prodotto () {
    	
    }
    

	public Prodotto(int idProdotto, String nome, String descrizione, double prezzo, String immagineUrl,
			boolean isCancellato) {
		super();
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.immagineUrl = immagineUrl;
		this.isCancellato = isCancellato;
	}
    
    
    public int getIdProdotto() {
		return idProdotto;
	}



	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
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



	public double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	public String getImmagineUrl() {
		return immagineUrl;
	}



	public void setImmagineUrl(String immagineUrl) {
		this.immagineUrl = immagineUrl;
	}



	public boolean isCancellato() {
		return isCancellato;
	}



	public void setCancellato(boolean isCancellato) {
		this.isCancellato = isCancellato;
	}
    
}
