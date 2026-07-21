package dao;

import java.sql.SQLException;
import java.util.*;
import model.Giardino;

public interface GiardinoDao {
	public List<Giardino> doRetrieveByUtente(int idUtente) throws SQLException;
}
