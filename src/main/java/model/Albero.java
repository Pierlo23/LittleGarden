package model;

import java.io.Serializable;

public class Albero implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAlbero;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private String pathImmagine;
    private String mimeType;
    private boolean frutto;
    private boolean softDelete;
	
    public Albero () {
    	
    }
    

	public Albero(int idAlbero, String nome, String descrizione, double prezzo, int quantita ,String pathImmagine, String mimeType, boolean frutto,
			boolean softDelete) {
		super();
		this.idAlbero = idAlbero;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.pathImmagine = pathImmagine;
		this.mimeType = mimeType;
		this.frutto = frutto;
		this.softDelete = softDelete;
	}


	public int getIdAlbero() {
		return idAlbero;
	}


	public void setIdAlbero(int idAlbero) {
		this.idAlbero = idAlbero;
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


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public String getPathImmagine() {
		return pathImmagine;
	}


	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}


	public String getMimeType() {
		return mimeType;
	}


	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


	public boolean isFrutto() {
		return frutto;
	}


	public void setFrutto(boolean frutto) {
		this.frutto = frutto;
	}


	public boolean isSoftDelete() {
		return softDelete;
	}


	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}


}
    
