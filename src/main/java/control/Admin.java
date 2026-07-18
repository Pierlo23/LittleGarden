package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Albero;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.AlberoDao;
import dao.AlberoDaoImpl;


 
@WebServlet("/admin/GestioneCatalogo")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlberoDao alberoDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    	super.init(servletConfig);
    	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds==null) throw new ServletException("DataSource");
    	alberoDao = new AlberoDaoImpl(ds);
    }
    
    
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/views/admin/Console.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if (action != null) {
				if(action.equalsIgnoreCase("insert")) {
					insertAlbero(request);
				} else if (action.equalsIgnoreCase("delete")) {
					deleteAlbero(request);
				}
			}
		} catch (SQLException e ) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void deleteAlbero(HttpServletRequest request) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		alberoDao.doDelete(id);
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

}
