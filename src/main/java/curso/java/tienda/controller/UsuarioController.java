package curso.java.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.model.Usuarios;
import curso.java.tienda.service.UsuariosService;
import curso.java.tienda.utils.Cifrado;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuariosService us;
	
	@GetMapping("/alta")
	public String alta(Model model){
		model.addAttribute("usuario", new Usuarios());
		return "login";
	}
	
	@PostMapping("/altaUsuario")
	public String altaUsuario(HttpSession session, Model modelo,  @ModelAttribute Usuarios usuario) {
		
		String user, password;
		user = (String) session.getAttribute("email");
		password = (String) session.getAttribute("password");
		
		if(usuario != null) {
			if(Cifrado.comprobarCifrado(password, usuario.getClave())) {
				return "redirect:/usuario/altaUsuario/bienvenido";
			}
		}
		modelo.addAttribute("usuario", usuario);
		return "login";
	}
	
	@PostMapping("/altaUsuario/bienvenido")
	public String bienvenido(Model modelo, @ModelAttribute Usuarios usuario) {
		return "bienvenido";
	}
}
