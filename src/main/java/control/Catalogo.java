package control;

import dao.AlberoDao;
import dao.AlberoDaoImpl;
import model.Albero;
import model.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Servlet implementation class AlberoControl
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AlberoDao alberoDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    	super.init(servletConfig);
    	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
    	if (ds==null) {
    		throw new ServletException("DataSource non disponibile");
    	}
    	alberoDao = new AlberoDaoImpl(ds);
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if (carrello == null) {
			carrello = new Carrello();
			request.getSession().setAttribute("carrello", carrello);
		}
		processAction(request,carrello);
		request.getSession().setAttribute("carrello", carrello);
		//carica la lista di prodotti nella richiesta per la vista
		loadListaAlbero(request);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AlberiView.jsp");
		dispatcher.forward(request, response);
	}

	private void processAction(HttpServletRequest request, Carrello carrello) {
		String action = request.getParameter("action");
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
					aggiungiAlCarrello(request, carrello);
				} else if (action.equalsIgnoreCase("deleteC")) {
					rimuoviAlberoCarrello(request, carrello);
				} else if (action.equalsIgnoreCase("read")) {
					leggiSingoloAlbero(request);
				} else if (action.equalsIgnoreCase("delete")) {
					deleteAlbero(request);
				} else if (action.equalsIgnoreCase("insert")) {
					insertAlbero(request);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}
	
	private void insertAlbero(HttpServletRequest request) throws SQLException {
		//lettura parametri
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		boolean frutto = request.getParameter("frutto") != null;
		//creazione oggetto
		Albero albero = new Albero();
		albero.setNome(nome);
		albero.setDescrizione(descrizione);
		albero.setPrezzo(prezzo);
		albero.setQuantita(quantita);
		albero.setFrutto(frutto);
		albero.setSoftDelete(false);
		alberoDao.doSave(albero);
	}

	private void deleteAlbero(HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		alberoDao.doDelete(id);
	}
	
	private void leggiSingoloAlbero(HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("albero", alberoDao.doRetrieveByKey(id));
	}
	
	private void rimuoviAlberoCarrello(HttpServletRequest request, Carrello carrello) 
			throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		carrello.deleteAlbero(alberoDao.doRetrieveByKey(id));
	}
	
	private void aggiungiAlCarrello(HttpServletRequest request, Carrello carrello) 
			throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		carrello.addAlbero(alberoDao.doRetrieveByKey(id));
	}
	
	private void loadListaAlbero(HttpServletRequest request) {
		String sort = request.getParameter("sort");
		try {
			request.setAttribute("alberi", alberoDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
