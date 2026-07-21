package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/common/*")
public class FiltroCommon extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("utenteLoggato")!=null) {
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath()+"/login");
			}
			
	}
}
