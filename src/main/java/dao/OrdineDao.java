package dao;

import java.sql.SQLException;
import java.util.*;

import model.Carrello;
import model.Ordine;

public interface OrdineDao {
	public int checkout(int idUtente, Carrello carrello) throws SQLException;
	public Ordine doRetrieveByKey(int id) throws SQLException;
	public List<Ordine> doRetrieveByUtente(int idUtente) throws SQLException;
}
