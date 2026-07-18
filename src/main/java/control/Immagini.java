package control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Albero;
import model.Utente;
import jakarta.servlet.http.Part;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import dao.AlberoDao;
import dao.AlberoDaoImpl;




/**
 * Servlet implementation class Immagini
 */
@WebServlet("/Immagini")
@jakarta.servlet.annotation.MultipartConfig(maxFileSize = 5 * 1024 * 1024, // max 5 MB per file
maxRequestSize = 10 * 1024 * 1024, // max 10 MB per request
fileSizeThreshold = 2* 1024 * 1024)
public class Immagini extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String UPLOAD_DIR = "uploads";

	private AlberoDao alberoDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Immagini() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		if (ds == null) {
			throw new ServletException("DataSource non disponibile nel contesto applicativo.");
		}
		alberoDao = new AlberoDaoImpl(ds);
		// Crea la cartella uploads
		String uploadPath = getServletContext().getRealPath(File.separator + UPLOAD_DIR);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
	}
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("show")){
        	int alberoId = Integer.parseInt(request.getParameter("id"));
        	try {
        		Albero bean = alberoDao.doRetrieveByKey(alberoId);
        		String mimeType = bean.getMimeType();
        		String path = bean.getPathImmagine();
        		response.setContentType(mimeType);
        		try (InputStream is = new FileInputStream(path)) {
        			OutputStream os = response.getOutputStream();
        			is.transferTo(os);
        		} catch (IOException ioe) {
    				System.err.println("Error:" + ioe.getMessage());
    			}
  
        	} catch (SQLException e) {
				System.err.println("Error:" + e.getMessage());
			}
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		
		if(utente==null || !utente.isAdmin()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Accesso negato");
			return;
		}
		
		if ("upload".equalsIgnoreCase(action)) {
			int idAlbero = Integer.parseInt(request.getParameter("idAlbero"));
			Part part = request.getPart("immagine");
			if (part != null) {
				String originalFileName = part.getSubmittedFileName();
				if (originalFileName != null && !originalFileName.isEmpty() && part.getSize() > 0) {
					String mimeType = part.getContentType();
					String uniqueFileName = buildUniqueFileName(part);
					String uploadPath = getServletContext().getRealPath(File.separator + UPLOAD_DIR + File.separator + uniqueFileName);
					Albero bean = new Albero();
					bean.setIdAlbero(idAlbero);
					bean.setMimeType(mimeType);
					bean.setPathImmagine(uploadPath);
					try {
						part.write(uploadPath);
						alberoDao.doUpdateImage(bean);
						System.out.println(uploadPath);
					} catch (SQLException e) {
						System.err.println("Error:" + e.getMessage());
					}
				}
			}
		}
		response.sendRedirect("admin/GestioneCatalogo");
	}

	private String buildUniqueFileName(Part part) {
		String originalName = part.getSubmittedFileName();
		String extension;
		if (originalName.contains(".")) {
		    extension = originalName.substring(originalName.lastIndexOf("."));
		} else {
		    extension = "";
		}
		return UUID.randomUUID() + extension;
	}
	
}
