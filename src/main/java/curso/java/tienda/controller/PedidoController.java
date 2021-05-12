package main.java.curso.java.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.curso.java.tienda.service.PedidoService;

@Controller
@RequestMapping("/compra")
public class PedidoController {
	
	@Autowired
	PedidoService ps;
	
	@GetMapping("")
	public String validarCompra() {
		return null;
	}
}
