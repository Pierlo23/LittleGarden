package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import model.Utente;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private UtenteDao utenteDao;
	
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	
		if (ds==null) {
			throw new ServletException("DataSource non disponibile");
		}
		
		utenteDao = new UtenteDaoImpl(ds);
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String emailInput = request.getParameter("email");
		String passwordInput = request.getParameter("password");
		
		emailInput = validateField(emailInput,"email",errors);
		passwordInput = validateField(passwordInput, "password", errors);
		
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		
		try {
			String digest = Login.toDigest(passwordInput);
			Utente utenteTrovato = utenteDao.login(emailInput, passwordInput);
			if (utenteTrovato != null) {
				HttpSession session = request.getSession();
				session.setAttribute ("utenteLoggato", utenteTrovato);
				String ruolo = utenteTrovato.isAdmin()? "admin" : "user";
				session.setAttribute("role", ruolo);
				
				if (utenteTrovato.isAdmin()) {
					response.sendRedirect("admin");
				} else {
					response.sendRedirect("commmon");
				}
			
			} else {
				errors.add("Username o password non validi");
                request.setAttribute("errors", errors); 
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
		}catch (SQLException e) {
			throw new ServletException("Errore durante il login",e);
		}
	}
	
	private String validateField(String value, String fieldName, List<String> errors) {
        if (value == null || value.trim().isEmpty()) {
            errors.add("Il campo " + fieldName + " non può essere vuoto");
            return "";
        }
        return value.trim();
	}

}
