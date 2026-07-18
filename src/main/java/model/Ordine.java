package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOrdine;
	private Date dataOrdine;
	private double totale;
	private int idUtente;
	private List<DettaglioOrdine> dettagli;
	
	public Ordine() {
		this.dettagli = new ArrayList<>();
	}

	
	
	public Ordine(int idOrdine, Date dataOrdine, double totale, int idUtente, List<DettaglioOrdine> dettagli) {
		super();
		this.idOrdine = idOrdine;
		this.dataOrdine = dataOrdine;
		this.totale = totale;
		this.idUtente = idUtente;
		this.dettagli = dettagli;
	}



	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public List<DettaglioOrdine> getDettagli() {
		return dettagli;
	}

	public void setDettagli(List<DettaglioOrdine> dettagli) {
		this.dettagli = dettagli;
	}


	
	
	
}
