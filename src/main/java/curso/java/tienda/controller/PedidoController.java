package main.java.curso.java.tienda.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.curso.java.tienda.service.MetodoPagoService;
import main.java.curso.java.tienda.service.PedidoService;
import main.java.curso.java.tienda.model.MetodosPago;
import main.java.curso.java.tienda.model.Pedidos;

@Controller
@RequestMapping("/compra")
public class PedidoController {
	
	@Autowired
	PedidoService ps;
	
	@Autowired
	MetodoPagoService mps;
	
	@GetMapping("/tipoDePago")
	public String validarCompra(HttpSession session, @ModelAttribute MetodosPago metodoPago) {
		List<MetodosPago> listado = mps.metodosPago();
		session.setAttribute("pago", listado);
		session.setAttribute("metodoPago", metodoPago);
		return "pedido/tipoDePago";
	}
	
	@PostMapping("/inicioPago")
	public String inicioPago(HttpSession session) {
		MetodosPago metodo = (MetodosPago) session.getAttribute("metodoPago");
		return "pedido/aceptarPago";
	}
	
	@PostMapping("/pagoAceptado")
	public String pagoAceptado(HttpSession session) {
		Pedidos pedido = new Pedidos();
		MetodosPago metodo = (MetodosPago) session.getAttribute("metodoPago");
		return "pedido/pagoAceptado";
	}
}
