package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.DettaglioOrdine;


public interface DettaglioOrdineDao {
void doSaveAll(List<DettaglioOrdine> dettagli, Connection con) throws SQLException;
    List<DettaglioOrdine> doRetrieveByOrdine(int idOrdine) throws SQLException;
}
