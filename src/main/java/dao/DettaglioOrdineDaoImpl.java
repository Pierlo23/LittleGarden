package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import model.DettaglioOrdine;

public class DettaglioOrdineDaoImpl implements DettaglioOrdineDao{
	
	private static final String TABLE_NAME = "ordine_dettaglio";
	private DataSource ds = null;
	
	public DettaglioOrdineDaoImpl (DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<DettaglioOrdine> doRetrieveByOrdine(int idOrdine) throws SQLException {
		
		List<DettaglioOrdine> dettagli = new LinkedList<>();
		String selectSQL = "SELECT od.id, od.id_ordine, od.id_albero, od.quantita, od.prezzo_unitario, "
				+ "a.nome, a.path_immagine "
				+ "FROM " + TABLE_NAME + " od "
				+ "JOIN alberi a ON od.id_albero = a.id "
				+ "WHERE od.id_ordine = ?";
 
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			ps.setInt(1, idOrdine);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					DettaglioOrdine bean = new DettaglioOrdine();
					bean.setIdDettaglio(rs.getInt("id"));
					bean.setIdOrdine(rs.getInt("id_ordine"));
					bean.setIdAlbero(rs.getInt("id_albero"));
					bean.setQuantita(rs.getInt("quantita"));
					bean.setPrezzoPagato(rs.getDouble("prezzo_pagato"));
					bean.setNomeAlbero(rs.getString("nome"));
					bean.setPathImmagine(rs.getString("path_immagine"));
					dettagli.add(bean);
				}
			}
		}
		return null;
	}
	
}
 