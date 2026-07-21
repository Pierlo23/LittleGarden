package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import model.Albero;
import model.Carrello;
import model.Ordine;

public class OrdineDaoImpl implements OrdineDao {

	private static final String TABLE_ORDINI = "ordini";
	private static final String TABLE_DETTAGLIO = "dattaglio_ordine";
	private static final String TABLE_ALBERI = "alberi";
	private static final String TABLE_GIARDINO = "giardini";
	
	private DataSource ds = null;
	
	public OrdineDaoImpl(DataSource ds) {
		this.ds = ds; 	
		}
	
	@Override
	public List<Ordine> doRetrieveByUtente(int idUtente) throws SQLException {
		List <Ordine> ordini = new LinkedList <>();
		String selectSQL = "SELECT * FROM "+TABLE_ORDINI+" WHERE id_utente = ? ORDER BY data_ordine DESC ";
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			ps.setInt(1, idUtente);
			try(ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Ordine bean = new Ordine();
					bean.setIdOrdine(rs.getInt("id"));
					bean.setIdUtente(rs.getInt("id_utente"));
					bean.setDataOrdine(rs.getTimestamp("data_ordine"));
					bean.setTotale(rs.getDouble("totale"));
					ordini.add(bean);
				}
			}
		}
		return ordini;
	}

	@Override
	public int checkout(int idUtente, Carrello carrello) throws SQLException {
		String insertOrdineSQL = "INSERT INTO " + TABLE_ORDINI + " (id_utente, data_ordine, totale) VALUES (?, NOW(), ?)";
		String insertDettaglioSQL = "INSERT INTO " + TABLE_DETTAGLIO + " (id_ordine, id_albero, quantita, prezzo_pagato) VALUES (?, ?, ?, ?)";
		String scaricoSQL = "UPDATE " + TABLE_ALBERI + " SET quantita = quantita - ? WHERE id = ? AND quantita >= ?";
		String insertGiardinoSQL = "INSERT INTO " + TABLE_GIARDINO + " (id_utente, id_albero, quantita) VALUES (?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE quantita = quantita + ?";
		 try (Connection connection = ds.getConnection()) {
			 connection.setAutoCommit(false);
			 try {
				 //creo l'ordine nel db
				 int idOrdine;
				 try (PreparedStatement ps = connection.prepareStatement(insertOrdineSQL, Statement.RETURN_GENERATED_KEYS)) {
					 ps.setInt(1, idUtente);
					 ps.setDouble(2, carrello.getPrezzoTotale());
					 ps.executeUpdate();
				 
					 try(ResultSet rs = ps.getGeneratedKeys()) {
						 if(rs.next()) {
							 idOrdine=rs.getInt(1);
						 } else {
							 throw new SQLException("id ordine non trovato");
						 }
					 }
				 }
			 
				 try (PreparedStatement psDettaglio = connection.prepareStatement(insertDettaglioSQL);
						 PreparedStatement psScarico = connection.prepareStatement(scaricoSQL);
						 PreparedStatement psGiardino = connection.prepareStatement(insertGiardinoSQL)) {
					for (Albero albero : carrello.getAlberi()) {
						psDettaglio.setInt(1, idOrdine);
						psDettaglio.setInt(2, albero.getIdAlbero());
						psDettaglio.setInt(3, 1);
						psDettaglio.setDouble(4, albero.getPrezzo());
						psDettaglio.executeUpdate();
						psScarico.setInt(1, albero.getIdAlbero());
						psGiardino.setInt(1, idUtente);
						psGiardino.setInt(2, albero.getIdAlbero());
						psGiardino.executeUpdate();
					}
				 }
			 connection.commit();
			 return idOrdine;
			 } catch (SQLException e) {
				 connection.rollback();
				 throw e;
			 }
		 }
	}

	@Override
	public Ordine doRetrieveByKey(int id) throws SQLException {
		Ordine bean = new Ordine();
		String selectSQL = "SELECT * FROM " + TABLE_ORDINI + " WHERE id = ?";
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					bean.setIdOrdine(rs.getInt("id"));
					bean.setIdUtente(rs.getInt("id_utente"));
					bean.setDataOrdine(rs.getTimestamp("data_ordine"));
					bean.setTotale(rs.getDouble("totale"));
				}
			}
		}
		return bean;
	}
	
}
