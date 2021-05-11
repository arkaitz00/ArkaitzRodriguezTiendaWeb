package main.java.curso.java.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.curso.java.tienda.model.Productos;
import main.java.curso.java.tienda.model.Usuarios;
import main.java.curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService ps;
	double totalPrecio = 0;

	@GetMapping("/listaProductos")
	public String listaProductos(Model modelo, HttpSession session) {
		List<Productos> listaProductos = ps.listado();
		List<Productos> carrito = new ArrayList<>();
		session.setAttribute("carrito", carrito);
		modelo.addAttribute("listaProductos", listaProductos);
		return "/producto/listarProductos";
	}
	
	@GetMapping("/carrito/{id}")
	public String verCarritoId(@PathVariable("id") int id, HttpSession session) {		
		List<Productos> listaCarrito = (List<Productos>) session.getAttribute("carrito");		
		Productos producto = ps.buscarPorId(id);
		listaCarrito.add(producto);
		if(listaCarrito.size()==1) {
			totalPrecio = producto.getPrecio();
		}else {
			totalPrecio += producto.getPrecio();
		}
		session.setAttribute("listaCarrito", listaCarrito);
		session.setAttribute("totalPrecio", totalPrecio);
		return "redirect:/producto/carrito";
	}

	@GetMapping("/carrito")
	public String verCarrito(HttpSession session) {
		return "/producto/listarProductosCarrito";
	}
}
