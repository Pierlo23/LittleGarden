package dao;

import java.sql.SQLException;
import model.Utente;

public interface UtenteDao {
	
	public Utente login(String email, String password) throws SQLException;
	public void registrazione(Utente utente) throws SQLException;
	
}
