package curso.java.tienda.controller;

import java.io.IOException;

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
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

import curso.java.tienda.model.Usuarios;
import curso.java.tienda.repository.UsuariosRepository;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.utils.Cifrado;
import curso.java.tienda.utils.HibernateUtil;
import curso.java.tienda.utils.MetodosUtiles;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(Login.class);
	Session s;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String usuario, password;
		
		usuario = request.getParameter("usuario");
		password = request.getParameter("password");
		
		
		
		Usuarios u = UsuariosRepository.devolverUsuarioEmail(s, usuario);
		if(u != null) {
			if(Cifrado.comprobarCifrado(password, u.getClave())){
				HttpSession session = request.getSession(true);
				session.setAttribute("rolUsuario", MetodosUtiles.nombreRol(u.getIdRol()));
				session.setAttribute("nombreUsuario", u.getNombre());
				response.sendRedirect("Bienvenido.jsp");
			}else {
				response.sendRedirect("Login.jsp");
			}
		}else {
			logger.warn("El usuario no existe");
			response.sendRedirect("Login.jsp");
		}
	}	
}
