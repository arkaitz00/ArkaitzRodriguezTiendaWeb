package main.java.curso.java.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.curso.java.tienda.model.Usuarios;
import main.java.curso.java.tienda.repository.UsuariosRepository;
import main.java.curso.java.tienda.service.UsuariosService;
import main.java.curso.java.tienda.utils.Cifrado;
import main.java.curso.java.tienda.utils.DatosJson;
import main.java.curso.java.tienda.utils.MetodosUtiles;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuariosService us;
	
	@GetMapping("/alta")
	public String alta(Model modelo){
		modelo.addAttribute("usuario", new Usuarios());
		return "login";
	}
	
	@PostMapping("/altaUsuario")
	public String altaUsuario(HttpServletRequest request, HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("clave");
		usuario = us.devolverUsuarioEmail(email);
		if(usuario != null){			
			if(Cifrado.comprobarCifrado(password, usuario.getClave())) {
				return "redirect:/usuario/bienvenido";
			}
		}
		modelo.addAttribute("oUsuario", usuario);
		return "login";
	}
	
	@GetMapping("/registro")
	public String registro(Model modelo){
		modelo.addAttribute("usuario", new Usuarios());
		return "registro";
	}
	
	@PostMapping("/registroUsuario")
	public String registroUsuario(HttpServletRequest request, HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("clave");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String direccion = request.getParameter("direccion");
		String localidad = request.getParameter("municipio");
		String provincia = request.getParameter("provincia");
		String telefono = request.getParameter("telefono");
		String dni = request.getParameter("dni");
		
		String cifrado = Cifrado.cifrar(password);
		usuario = new Usuarios(3, email, cifrado, nombre, apellido1, apellido2, direccion, localidad, provincia, telefono, dni);
		if(us.crearUsuario(usuario)) {
			return "redirect:/usuario/bienvenido";
		}
		modelo.addAttribute("usuario", usuario);
		return "registro";
	}
	
	@GetMapping("/bienvenido")
	public String bienvenido(HttpSession session, HttpServletRequest request, Model modelo) {
		String email = request.getParameter("email");
		Usuarios usuario = us.devolverUsuarioEmail(email);
		modelo.addAttribute("nombreUsuario", session.getAttribute("nombre"));
		//String nombreRol = MetodosUtiles.nombreRol(usuario.getIdRol());
		modelo.addAttribute("nombreRol", session.getAttribute("idRol"));
		return "bienvenido";
	}
}
