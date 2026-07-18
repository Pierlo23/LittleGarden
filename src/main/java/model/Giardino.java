package model;

import java.io.Serializable;

public class Giardino implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idUtente;
	private int idAlbero;
	private int quantita;
	
	public Giardino() {
		
	}

	public Giardino(int idUtente, int idAlbero, int quantita) {
		super();
		this.idUtente = idUtente;
		this.idAlbero = idAlbero;
		this.quantita = quantita;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdAlbero() {
		return idAlbero;
	}

	public void setIdAlbero(int idAlbero) {
		this.idAlbero = idAlbero;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

}
