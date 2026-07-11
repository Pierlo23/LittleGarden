package dao;

import java.sql.SQLException;
import java.util.*;
import model.Albero;

public interface AlberoDao {

	void doSave(Albero albero) throws SQLException;
	List<Albero> doRetrieveAll(String order) throws SQLException;
	Albero doRetrieveByKey(int id) throws SQLException;
	boolean doDelete(int id) throws SQLException;
	boolean doUpdateImage(Albero albero) throws SQLException;
}
