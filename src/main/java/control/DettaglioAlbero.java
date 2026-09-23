package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.AlberoDao;
import model.Albero;

@WebServlet("/dettaglioAlbero")
public class DettaglioAlbero extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlberoDao alberoDao;

    public DettaglioAlbero() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            int idAlbero = Integer.parseInt(request.getParameter("id"));
            Albero albero = alberoDao.doRetrieveByKey(idAlbero); 
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
