package model;

import java.io.Serializable;

public class DettaglioOrdine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idDettaglio;
	private int idOrdine;
	private int idAlbero;
	private int quantita;
	
	public DettaglioOrdine () {
		
	}

	public DettaglioOrdine(int idDettaglio, int idOrdine, int idAlbero, int quantita) {
		super();
		this.idDettaglio = idDettaglio;
		this.idOrdine = idOrdine;
		this.idAlbero = idAlbero;
		this.quantita = quantita;
	}

	public int getIdDettaglio() {
		return idDettaglio;
	}

	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
