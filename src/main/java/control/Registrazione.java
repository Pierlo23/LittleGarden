package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletConfig;

import dao.UtenteDao;
import dao.UtenteDaoImpl;
import javax.sql.DataSource;
import model.Utente;


@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtenteDao utenteDao;   
    
    @Override
    public void init(ServletConfig servletconfig) throws ServletException {
    	try {
    		super.init(servletconfig);
    		DataSource ds = (DataSource) getServletContext().getAttribute("DataSorce");
    		if (ds==null) {
    			throw new ServletException("DataSource non disponibile");
    		}
    		utenteDao = new UtenteDaoImpl(ds);
    	} catch (ServletException e) {
    		throw e;
    	}
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pagina del form
		request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		
		//parametri del form
		String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        
        //validazione dei parametri
        nome = validateField(nome, "Nome", errors);
        cognome = validateField(cognome, "Cognome", errors);
        email = validateField(email, "Email", errors);
        password = validateField(password, "Password", errors);
        indirizzo = validateField(indirizzo, "Indirizzo", errors);
	
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("nome", nome);
            request.setAttribute("cognome", cognome);
            request.setAttribute("email", email);
            request.setAttribute("indirizzo", indirizzo);
            
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        try {
        	String passwordCriptata = Login.toDigest(password); 

        	//Javabean dell'utente
        	Utente nuovoUtente = new Utente();
        	nuovoUtente.setNome(nome);
        	nuovoUtente.setCognome(cognome);
        	nuovoUtente.setEmail(email);
        	nuovoUtente.setPassword(passwordCriptata);
        	nuovoUtente.setIndirizzo(indirizzo);
        	nuovoUtente.setAdmin(false);

        	utenteDao.registrazione(nuovoUtente);

        	response.sendRedirect(request.getContextPath() + "/login");
        } catch (SQLException e) {
        	errors.add("Errore durante la registrazione");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
	}
	
	private String validateField(String value, String fieldName, List<String> errors) {
        if (value == null || value.trim().isEmpty()) {
            errors.add("Il campo '" + fieldName + "' è obbligatorio.");
            return "";
        }
        return value.trim();
    }

}
