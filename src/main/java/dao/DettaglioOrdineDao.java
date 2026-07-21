package dao;


import java.sql.SQLException;
import java.util.List;
import model.DettaglioOrdine;


public interface DettaglioOrdineDao {
	public List<DettaglioOrdine> doRetrieveByOrdine(int idOrdine) throws SQLException;
}
