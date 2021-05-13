package main.java.curso.java.tienda.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.curso.java.tienda.model.Productos;
import main.java.curso.java.tienda.model.Usuarios;
import main.java.curso.java.tienda.service.ProductoService;
import main.java.curso.java.tienda.service.UsuariosService;
import main.java.curso.java.tienda.utils.Cifrado;
import main.java.curso.java.tienda.utils.MetodosUtiles;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuariosService us;
	
	@Autowired
	ProductoService ps;
	
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
				String nombreRol = MetodosUtiles.nombreRol(u.getIdRol());
				session.setAttribute("usuario", u);
				session.setAttribute("nombreRol", nombreRol);
				return "redirect:/";
			}
		}
		modelo.addAttribute("usuario", new Usuarios());
		return "usuario/login";
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
	
	@GetMapping("/editar/{id}")
	public String editarID(@PathVariable("id") int id, HttpSession session, Model modelo){
 		Usuarios usuario = us.devolverUsuarioId(id);

		modelo.addAttribute("usuario", usuario);
		return "usuario/editar";
	}
	
	@GetMapping("/editarPerfil")
	public String editarPerfil(@ModelAttribute Usuarios usuario) {
		
		us.editarUsuario(usuario);
		return "redirect:/usuario/bienvenido";
	}
	
	@GetMapping("/eliminar/{id}")
	public String editar(@PathVariable("id") int id, HttpSession session, Model modelo){
		Usuarios usuario = us.devolverUsuarioId(id);
		us.borrarUsuario(usuario.getDni());
		return "/usuario/editar";
	}
	
	@GetMapping("/bienvenido")
	public String bienvenido(Model modelo, HttpSession session) {
		
		Usuarios u = (Usuarios) session.getAttribute("usuario");
		if(u != null) {
		
			Usuarios usuario = us.devolverUsuarioEmail(u.getEmail());
			modelo.addAttribute("usuario", usuario);
			
			return "redirect:/";
		
		}else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/listarProductos")
	public String listarProductos(HttpSession session){
		List<Productos> listaProductos = ps.listado();
		session.setAttribute("listaProductos", listaProductos);
		return "/usuario/listarProductos";
	}
	
	@GetMapping("/listarClientes")
	public String listarClientes(HttpSession session){
		List<Usuarios> listaClientes = us.listarRol(3);
		session.setAttribute("listaClientes", listaClientes);
		return "/usuario/listarClientes";
	}
	
	@GetMapping("/listarEmpleados")
	public String listarEmpleados(Model modelo, HttpSession session){
		List<Usuarios> listaEmpleados = us.listarRol(2);
		int tamanyo = listaEmpleados.size();
		modelo.addAttribute("tamanyo", tamanyo);
		session.setAttribute("listaEmpleados", listaEmpleados);
		return "/usuario/listarEmpleados";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session){
		session.setAttribute("usuario", null);
		return "redirect:/";
	}
}
