package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	private UtenteDao utenteDao;
    
	public Login() {
        super();
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
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		email = validateField(email,"email",errors);
		password = validateField(password, "password", errors);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			dispatcher.forward(request, response);
			return;
		}
		try {
			String digest = Login.toDigest(password);
			Utente utenteTrovato = utenteDao.login(email, digest);
			if (utenteTrovato != null) {
				HttpSession session = request.getSession();
				session.setAttribute ("utenteLoggato", utenteTrovato);
				String ruolo = utenteTrovato.isAdmin()? "admin" : "user";
				//token
				session.setAttribute("role", ruolo);
				if (utenteTrovato.isAdmin()) {
					response.sendRedirect("admin/welcome");
				} else {
					response.sendRedirect("common/welcome"); 
					}
			} else {
				errors.add("Username o password non validi");
                request.setAttribute("errors", errors); 
                dispatcher.forward(request, response);
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
	
	public static String toDigest(String password) {
        try {
        		//Definisco la funzione di hash SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Calcolo il digest della password
            byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            //Converto il digest in stringa esadecimale
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-512 non disponibile", e);
        }
    }

}
