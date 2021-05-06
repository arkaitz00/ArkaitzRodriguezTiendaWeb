package curso.java.tienda.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import curso.java.tienda.repository.RolesRepository;
import curso.java.tienda.utils.HibernateUtil;

/**
 * Servlet implementation class CrearRol
 */
@WebServlet("/CrearRol")
public class CrearRol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(AltaUsuario.class);
	Session s;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearRol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		s = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int idRol = Integer.parseInt(request.getParameter("idRol"));
		String nombre = request.getParameter("nombreRol");
		
		if(RolesRepository.insertarRol(s, idRol, nombre)) {
			response.sendRedirect("Bienvenido.jsp");
		}else {
			response.sendRedirect("VistaRol.jsp");
		}
	}

}
