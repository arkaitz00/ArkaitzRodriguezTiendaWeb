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
import main.java.curso.java.tienda.utils.MetodosUtiles;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuariosService us;
	
	@GetMapping("/alta")
	public String alta(Model modelo){
		modelo.addAttribute("usuario", new Usuarios());
		return "/usuario/login";
	}
	
	@PostMapping("/altaUsuario")
	public String altaUsuario(HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		session.setAttribute("usuario", usuario);
		Usuarios u = us.devolverUsuarioEmail(usuario.getEmail());
		if(usuario != null){			
			if(Cifrado.comprobarCifrado(usuario.getClave(), u.getClave())) {
				return "redirect:/usuario/bienvenido";
			}
		}
		return "/usuario/login";
	}
	
	@GetMapping("/registro")
	public String registro(Model modelo){
		modelo.addAttribute("usuario", new Usuarios());
		return "/usuario/registro";
	}
	
	@PostMapping("/registroUsuario")
	public String registroUsuario(HttpServletRequest request, HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("clave");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String direccion = request.getParameter("direccion");
		String municipio = request.getParameter("municipio");
		String provincia = request.getParameter("provincia");
		String telefono = request.getParameter("telefono");
		String dni = request.getParameter("dni");
		
		String cifrado = Cifrado.cifrar(password);
		usuario = us.crearUsuario(3, email, cifrado, nombre, apellido1, apellido2, direccion, municipio, provincia, telefono, dni);
		if(usuario != null) {
			session.setAttribute("usuario", usuario);
			return "redirect:/usuario/bienvenido";
		}
		modelo.addAttribute("usuario", usuario);
		return "/usuario/registro";
	}
	
	@GetMapping("/editar")
	public String editar(HttpSession session){
		session.getAttribute("usuario");
		return "/usuario/editar";
	}
	
	@PostMapping("/editarPerfil")
	public String editarPerfil(HttpServletRequest request, HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String telefono = request.getParameter("telefono");
		
		us.editarUsuario(usuario, email, nombre, apellido1, apellido2, telefono);
		
		return "redirect:/usuario/bienvenido";
	}
	
	@GetMapping("/bienvenido")
	public String bienvenido(Model modelo, HttpSession session) {
		
		Usuarios u = (Usuarios) session.getAttribute("usuario");
		Usuarios usuario = us.devolverUsuarioEmail(u.getEmail());
		modelo.addAttribute("usuario", usuario);
		
		return "composicion/bienvenido";
	}
}
