package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import model.Utente;

public class UtenteDaoImpl  implements UtenteDao {

		private static final String TABLE_NAME = "utente";
		private DataSource ds = null;
		
		public UtenteDaoImpl(DataSource ds) {
			this.ds = ds;
		}
		
		@Override
		public synchronized Utente login(String email, String password) throws SQLException {
			Utente utente = null;
			String query = "SELECT * FROM utenti WHERE email = ? AND password = ?";
			try (Connection con = ds.getConnection();
				 PreparedStatement ps = con.prepareStatement(query)) {
					ps.setString(1, email);
					ps.setString(2, password);
				//mando la query al db
					try (ResultSet rs = ps.executeQuery()) {
						if (rs.next()) {
							utente = new Utente();
							utente.setIdUtente(rs.getInt("id_utente"));
							utente.setNome(rs.getString("nome"));
							utente.setCognome(rs.getString("cognome"));
							utente.setEmail(rs.getString("email"));
							utente.setPassword(rs.getString("password"));
							utente.setIndirizzo(rs.getString("indirizzo"));
							utente.setAdmin(rs.getBoolean("is_admin"));
							}
						}
				}
		return utente;
		}
		
		@Override
		public synchronized void registrazione(Utente utente) throws SQLException {
			String insertSQL = "INSERT INTO " + TABLE_NAME + " (nome, cognome, email, password, indirizzo, is_admin) VALUES (?, ?, ?, ?, ?, ?)";
			
			try (Connection connection = ds.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
				preparedStatement.setString(1, utente.getNome());
				preparedStatement.setString(2, utente.getCognome());
				preparedStatement.setString(3, utente.getEmail());
				preparedStatement.setString(4, utente.getPassword());
				preparedStatement.setString(5, utente.getIndirizzo());
				preparedStatement.setBoolean(6, utente.isAdmin());
				
				//aggiotnamento sul database
				preparedStatement.executeUpdate();
			}
		}
}
