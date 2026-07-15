package control;

import dao.AlberoDao;
import dao.AlberoDaoImpl;
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
		String sort = request.getParameter("sort");
		try {
			request.setAttribute("alberi", alberoDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.err.println("Error:" + e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/catalogo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
