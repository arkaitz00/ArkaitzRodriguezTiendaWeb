package main.java.curso.java.tienda.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.curso.java.tienda.service.MetodoPagoService;
import main.java.curso.java.tienda.service.PedidoService;
import main.java.curso.java.tienda.model.MetodosPago;
import main.java.curso.java.tienda.model.Pedidos;
import main.java.curso.java.tienda.model.Usuarios;

import java.sql.Timestamp;

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
	public String inicioPago(@RequestParam("metodoPago") String metodoPago, HttpSession session) {
		session.setAttribute("metodoPago", metodoPago);
		return "pedido/aceptarPago";
	}

	@PostMapping("/pagoAceptado")
	public String pagoAceptado(HttpSession session) {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String metodo = (String) session.getAttribute("metodoPago");
		Usuarios usuario = (Usuarios) session.getAttribute("usuario");
		double total = (double) session.getAttribute("precioTotal");
		Pedidos pedido = ps.crearPedido(usuario.getId(), date, metodo, "pendiente", null, total);
		session.setAttribute("pedido", pedido);
		return "pedido/pagoAceptado";
	}
}
