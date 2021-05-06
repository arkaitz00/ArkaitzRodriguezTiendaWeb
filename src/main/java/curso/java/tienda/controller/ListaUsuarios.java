package curso.java.tienda.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import curso.java.tienda.repository.UsuariosRepository;
import curso.java.tienda.utils.HibernateUtil;

/**
 * Servlet implementation class ListaUsuarios
 */
@WebServlet("/ListaUsuarios")
public class ListaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(Login.class);
	Session s = HibernateUtil.getSessionFactory().openSession();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
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
		List lista = UsuariosRepository.listaUsuarios(s);
		if(lista.size() != 0) {
			logger.info("La lista contiene usuarios");
			HttpSession session = request.getSession(true);
			session.setAttribute("listaUsuarios", lista);
			response.sendRedirect("ListaUsuarios.jsp");
		}else {
			logger.warn("La lista esta vacia");
			response.sendRedirect("Bienvenido.jsp");
		}
	}

}
