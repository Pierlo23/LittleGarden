package dao;

import java.sql.SQLException;
import java.util.*;
import model.Albero;

public interface AlberoDao {

	void doSave(Albero albero) throws SQLException;
	List<Albero> doRetrieveAll(String order) throws SQLException;
}
