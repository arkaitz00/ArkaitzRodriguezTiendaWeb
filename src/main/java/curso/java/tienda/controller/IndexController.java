package main.java.curso.java.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

	@GetMapping("")
	public String entrada() {
		return "index";
	}
	
	@GetMapping("login.html")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("registro.html")
	public String registrarse() {
		return "registro";
	}
}
