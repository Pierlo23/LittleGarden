package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import model.Albero;
import model.Giardino;

public class GiardinoDaoImpl implements GiardinoDao {

	private static final String TABLE_NAME = "giardini";
	private DataSource ds = null;
	
	public GiardinoDaoImpl (DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public synchronized List<Giardino> doRetrieveByUtente(int idUtente) throws SQLException {
		List<Giardino> alberiPosseduti = new LinkedList<>();
		String selectSQL = "SELECT g.id_utente, g.id_albero, g.quantita, "
				+ "a.nome, a.path_immagine, a.frutto "
				+ "FROM " + TABLE_NAME + " g "
				+ "JOIN alberi a ON g.id_albero = a.id "
				+ "WHERE g.id_utente = ? ";
 
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSQL)) {
			ps.setInt(1, idUtente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Giardino beanGiardino = new Giardino();
					beanGiardino.setIdUtente(rs.getInt("id_utente"));
					beanGiardino.setIdAlbero(rs.getInt("id_albero"));
					beanGiardino.setQuantita(rs.getInt("quantita"));
					Albero beanAlbero = new Albero();
					beanAlbero.setIdAlbero(rs.getInt("id_albero"));
				    beanAlbero.setNome(rs.getString("nome"));
				    beanAlbero.setPathImmagine(rs.getString("path_immagine"));
				    beanAlbero.setFrutto(rs.getBoolean("frutto"));
				    beanGiardino.setAlbero(beanAlbero);
				    alberiPosseduti.add(beanGiardino);
				}
			}
		}
		return alberiPosseduti;
		
	}
	
}
