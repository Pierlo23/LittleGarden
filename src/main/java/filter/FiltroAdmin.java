package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Utente;

@WebFilter("/admin/*")
public class FiltroAdmin extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
			HttpSession session = request.getSession(false);
			//verifico l'autenticazione
			if (session != null && session.getAttribute("utenteLoggato")!=null) {
				Utente utente = (Utente) session.getAttribute("utenteLoggato");
				//verifico admin
				if (utente.isAdmin()) {
					chain.doFilter(request, response);
				} else {
					response.sendRedirect(request.getContextPath()+"/common");
				}
			} else {
				//utente non autenticato, torna al login
				response.sendRedirect(request.getContextPath()+"/login");
			}
}
}