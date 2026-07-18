package dao;

import java.sql.SQLException;
import java.util.*;
import model.Giardino;
import model.Albero;

public interface GiardinoDao {
	void aggiungiAlbero(int idUtente, int idAlbero, int quantita) throws SQLException;
    List<Albero> getGiardino(int idUtente) throws SQLException;
}
