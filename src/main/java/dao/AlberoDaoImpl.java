package dao;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.Albero;

public class AlberoDaoImpl implements AlberoDao {

	private static final String TABLE_NAME = "alberi";
	private DataSource ds = null;
	
	public AlberoDaoImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override 
	public synchronized void doSave(Albero albero) throws SQLException {
		String insertSQL = "INSERT INTO " + TABLE_NAME + "(nome, descrizione, prezzo, quantita, path_immagine, mime_type, frutto, soft_delete) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSQL)) {
			ps.setString(1, albero.getNome());
			ps.setString(2, albero.getDescrizione());
			ps.setDouble(3, albero.getPrezzo());
			ps.setInt(4, albero.getQuantita());
			ps.setString(5, null); 
	        ps.setString(6, null);
			ps.setBoolean(7, albero.isFrutto());
			ps.setBoolean(8, false);
		
			ps.executeUpdate();
		}
				
	}
	
	@Override
    public synchronized boolean doUpdateImage(Albero albero) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET path_immagine = ?, mime_type = ? WHERE id = ?";
        try (Connection conn = ds.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, albero.getPathImmagine());
            ps.setString(2, albero.getMimeType());
            ps.setInt(3, albero.getIdAlbero());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated != 0;
        }
    }
	
	
	@Override
	public synchronized List<Albero> doRetrieveAll(String order) throws SQLException {
		List<Albero> alberi = new LinkedList<>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME+" WHERE soft_delete = FALSE";
		if (order != null && !order.isEmpty()) {
			selectSQL += " ORDER BY " + order; //devo aggiungere whitelist per SQLInjection
		}
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Albero bean = new Albero();
				bean.setIdAlbero(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPathImmagine(rs.getString("path_immagine"));
				bean.setMimeType(rs.getString("mime_type"));
				bean.setFrutto(rs.getBoolean("frutto"));
				bean.setSoftDelete(rs.getBoolean("soft_delete"));
				alberi.add(bean);
			}
		}
		return alberi;
	}
	
	@Override
	 public synchronized Albero doRetrieveByKey(int id) throws SQLException {
	        Albero bean = new Albero();
	        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? AND soft_delete = FALSE";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement ps = connection.prepareStatement(selectSQL)) {
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	bean.setIdAlbero(rs.getInt("id"));
	    				bean.setNome(rs.getString("nome"));
	    				bean.setDescrizione(rs.getString("descrizione"));
	    				bean.setPrezzo(rs.getDouble("prezzo"));
	    				bean.setQuantita(rs.getInt("quantita"));
	    				bean.setPathImmagine(rs.getString("path_immagine"));
	    				bean.setMimeType(rs.getString("mime_type"));
	    				bean.setFrutto(rs.getBoolean("frutto"));
	    				bean.setSoftDelete(rs.getBoolean("soft_delete"));
	                }
	            }
	        }
	        return bean;
	    }
	
	  @Override
	    public synchronized boolean doDelete(int id) throws SQLException {
	        String deleteSQL = "UPDATE " + TABLE_NAME + " SET soft_delete = TRUE WHERE ID  = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setInt(1, id);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
	    }
	
}