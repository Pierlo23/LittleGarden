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

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.AlberoDao;
import dao.AlberoDaoImpl;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AlberoDao alberoDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void init (ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) throw new ServletException("DataSource non disponibile");
        alberoDao = new AlberoDaoImpl(ds);
	}
     
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/carrello.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        synchronized (session) {
		Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.equalsIgnoreCase("addC")) {
                    aggiungiAlCarrello(request, carrello);
                } else if (action.equalsIgnoreCase("deleteC")) {
                   rimuoviDalCarrello(request, carrello);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error:" + e.getMessage());
        }
        }

        response.sendRedirect(request.getContextPath() + "/Catalogo");
	}

	private void aggiungiAlCarrello(HttpServletRequest request, Carrello carrello) 
			throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		carrello.addAlbero(alberoDao.doRetrieveByKey(id));
	}



	private void rimuoviDalCarrello(HttpServletRequest request, Carrello carrello) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		carrello.deleteAlbero(alberoDao.doRetrieveByKey(id));
	}

}