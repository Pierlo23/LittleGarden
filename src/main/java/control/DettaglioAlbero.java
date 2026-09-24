package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.AlberoDao;
import dao.AlberoDaoImpl;
import model.Albero;

@WebServlet("/dettaglioAlbero")
public class DettaglioAlbero extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlberoDao alberoDao;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    	super.init(servletConfig);
    	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
    	if (ds==null) {
    		throw new ServletException("DataSource non disponibile");
    	}
    	alberoDao = new AlberoDaoImpl(ds);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int idAlbero = Integer.parseInt(request.getParameter("id"));
            Albero albero = alberoDao.doRetrieveByKey(idAlbero); 
            
            if (albero == null) {
                response.sendRedirect(request.getContextPath() + "/Catalogo");
                return; 
            }
            
            request.setAttribute("albero", albero);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dettaglioAlbero.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Errore caricamento dettaglio prodotto: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/Catalogo");
        }
	}
     
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
