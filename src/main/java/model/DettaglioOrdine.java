package model;

import java.io.Serializable;

public class DettaglioOrdine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idDettaglio;
	private int idOrdine;
	private int idAlbero;
	private int quantita;
	private double prezzoPagato;
	private String nomeAlbero;
	private String pathImmagine;
	
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

	public double getPrezzoPagato() {
		return prezzoPagato;
	}

	public void setPrezzoPagato(double prezzoPagato) {
		this.prezzoPagato = prezzoPagato;
	}

	public String getNomeAlbero() {
		return nomeAlbero;
	}

	public void setNomeAlbero(String nomeAlbero) {
		this.nomeAlbero = nomeAlbero;
	}

	public String getPathImmagine() {
		return pathImmagine;
	}

	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}
	
	
	
	
}
