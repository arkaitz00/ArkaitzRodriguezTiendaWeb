package main.java.curso.java.tienda.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.curso.java.tienda.service.DetallesPedidoService;
import main.java.curso.java.tienda.service.MetodoPagoService;
import main.java.curso.java.tienda.service.PedidoService;
import main.java.curso.java.tienda.utils.MetodosUtiles;
import main.java.curso.java.tienda.model.DetallesPedido;
import main.java.curso.java.tienda.model.MetodosPago;
import main.java.curso.java.tienda.model.Pedidos;
import main.java.curso.java.tienda.model.Productos;
import main.java.curso.java.tienda.model.Usuarios;

import java.sql.Timestamp;

@Controller
@RequestMapping("/compra")
public class PedidoController {

	@Autowired
	PedidoService ps;

	@Autowired
	MetodoPagoService mps;
	
	@Autowired
	DetallesPedidoService dps;

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
		List<Productos> listaCarrito = (List<Productos>) session.getAttribute("listaCarrito");
		for(int i=0; i<listaCarrito.size(); i++) {
			DetallesPedido dp = dps.crearDetallePedido(pedido.getId(), listaCarrito.get(i).getId(), listaCarrito.get(i).getPrecio(),1,0.0f,listaCarrito.get(i).getPrecio());
		}
		session.setAttribute("pedido", pedido);
		return "pedido/pagoAceptado";
	}
	
	@GetMapping("/misPedidos")
	public String misPedidos(Model modelo, HttpSession session) {
		Usuarios usuario = (Usuarios) session.getAttribute("usuario");
		List<Pedidos> listaPedidos = ps.totalPedidosUsuario(usuario);
		int tamanyo = listaPedidos.size();
		modelo.addAttribute("tamanyo", tamanyo);
		modelo.addAttribute("listaPedidos", listaPedidos);
		return "pedido/misPedidos";
	}
	
	@GetMapping("/detalles/{id}")
	public String detallePedido(@PathVariable("id") int id, Model modelo) {
		List<DetallesPedido> detalles = dps.detallesPedido(id);		
		modelo.addAttribute("detalles", detalles);
		return "pedido/detallesPedido";
	}
	
	@GetMapping("/cancelarPedido/{id}")
	public String cancelarPedido(@PathVariable("id") int id, Model modelo) {
		Pedidos pedido = ps.buscarId(id);
		pedido.setEstado("En espera de cancelar");
		ps.opcionesPedido(pedido);
		return "redirect:/compra/misPedidos";
	}
	
	@GetMapping("/activarPedido/{id}")
	public String activarPedido(@PathVariable("id") int id, Model modelo) {
		Pedidos pedido = ps.buscarId(id);
		pedido.setEstado("pendiente");
		ps.opcionesPedido(pedido);
		return "redirect:/compra/misPedidos";
	}
	
	@GetMapping("/pedidosPendientes")
	public String cancelarPedido(Model modelo) {
		List<Pedidos> pedidosPendientes = ps.pedidosPendientesCancelo();
		modelo.addAttribute("pedidosPendientes", pedidosPendientes);
		return "pedido/pedidosPendientes";
	}
	
	@GetMapping("/pendienteCanceloPedido/{id}")
	public String pendienteCanceloPedido(@PathVariable("id") int id, Model modelo) {
		Pedidos pedido = ps.buscarId(id);
		pedido.setEstado("cancelado");
		ps.opcionesPedido(pedido);
		return "redirect:/compra/pedidosPendientes";
	}
	
	@GetMapping("/pedidosEnEnvio")
	public String pedidosEnEnvio(Model modelo) {
		List<Pedidos> pedidosPendientes = ps.pedidosPendientesEnvio();
		modelo.addAttribute("pedidosPendientes", pedidosPendientes);
		return "pedido/pedidosPendientes";
	}
	
	@GetMapping("/pendienteEnvioPedido/{id}")
	public String pendienteEnvioPedido(@PathVariable("id") int id, Model modelo) {
		Pedidos pedido = ps.buscarId(id);
		pedido.setEstado("enviado");
		ps.opcionesPedido(pedido);
		return "redirect:/compra/pedidosEnEnvio";
	}
}
