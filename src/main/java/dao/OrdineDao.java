package dao;

import java.sql.SQLException;
import java.util.*;
import model.Ordine;

public interface OrdineDao {
	void doSave(Ordine ordine) throws SQLException;
    List<Ordine> doRetrieveByUtente(int idUtente) throws SQLException;
}
