package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.Utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.OrdineDao;
import dao.OrdineDaoImpl;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdineDao ordineDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        
    }

    @Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile");
		}
		ordineDao = new OrdineDaoImpl(ds);
	}
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/Carrello");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if(carrello == null || carrello.getAlberi().isEmpty()) {
			request.setAttribute("errore", "carrello vuoto");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/erroreCheckout.jsp");
			dispatcher.forward(request, response);
		}
		try {
			int idOrdine = ordineDao.checkout(utente.getIdUtente(), carrello);
			carrello.svuota();
			request.setAttribute("idOrdine", idOrdine);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/confermaOrdine.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			System.err.println("Errore" + e.getMessage());
			request.setAttribute("errore", "Checkout non riuscito: quantita' non piu' disponibile per uno o piu' articoli.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/erroreCheckout.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
