package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DataSource ds = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // "jdbc/storage" deve corrispondere a quello che abbiamo scritto in context.xml e web.xml
            ds = (DataSource) envCtx.lookup("jdbc/storage"); 
        } catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
        
        // Salva il DataSource nel server, così riga 40 di Login.java lo troverà!
        context.setAttribute("DataSource", ds);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}