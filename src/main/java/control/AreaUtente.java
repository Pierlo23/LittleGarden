package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;
import model.Giardino;
import model.Ordine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.GiardinoDao;
/**
 * Servlet implementation class AreaUtente
 */
@WebServlet("/common/Giardino")
public class AreaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdineDao ordineDao;
	GiardinoDao giardinoDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaUtente() {
        super();
        
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (javax.sql.DataSource) getServletContext().getAttribute("DataSource");
        ordineDao = new dao.OrdineDaoImpl(ds);
        giardinoDao = new dao.GiardinoDaoImpl(ds);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
	
		try {
			List<Ordine> storicoOrdini = ordineDao.doRetrieveByUtente(utente.getIdUtente());
			List<Giardino> giardino = giardinoDao.doRetrieveByUtente(utente.getIdUtente());
			
			request.setAttribute("ordini", storicoOrdini);
			request.setAttribute("giardino", giardino);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/giardino.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			System.err.println("Errore caricamento area utente: " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
