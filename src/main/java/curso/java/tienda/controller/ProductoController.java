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
import org.springframework.web.bind.annotation.RequestParam;

import main.java.curso.java.tienda.model.Categorias;
import main.java.curso.java.tienda.model.Productos;
import main.java.curso.java.tienda.model.Usuarios;
import main.java.curso.java.tienda.service.CategoriasService;
import main.java.curso.java.tienda.service.ProductoService;
import main.java.curso.java.tienda.service.UsuariosService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService ps;

	@Autowired
	UsuariosService us;

	@Autowired
	CategoriasService cs;

	double totalPrecio = 0.0;
	int contador = 0;

	@GetMapping("/inicio")
	public String inicio(Model modelo, HttpSession session) {
		List<Productos> listaCarrito = new ArrayList<>();
		session.setAttribute("listaCarrito", listaCarrito);
		session.setAttribute("precioTotal", 0.0);
		return "redirect:/producto/listaProductos";
	}

	@GetMapping("/listaProductos")
	public String listaProductos(@RequestParam(name="categorias", required = false) int id, Model modelo, HttpSession session) {
		List<Categorias> categorias = cs.listarCategorias();
		modelo.addAttribute("listadoCategorias",categorias);
		List<Productos> listaProductos;
		if(id==0) {
			listaProductos = ps.listado();
		}else {
			listaProductos = ps.listadoCategoria(id);
		}
		Usuarios usuario = (Usuarios) session.getAttribute("usuario");
		modelo.addAttribute("listaProductos", listaProductos);
		List<Productos> carrito = (List<Productos>) session.getAttribute("listaCarrito");
		session.setAttribute("usuario", usuario);
		if (carrito == null) {
			List<Productos> listaCarrito = new ArrayList<>();
			session.setAttribute("listaCarrito", listaCarrito);
		}
		return "/producto/listarProductos";
	}

	@GetMapping("/carrito/{id}")
	public String verCarritoId(@PathVariable("id") int id, HttpSession session) {
		List<Productos> listaCarrito = (List<Productos>) session.getAttribute("listaCarrito");
		Productos producto = ps.buscarPorId(id);

		listaCarrito.add(producto);
		if (listaCarrito.size() == 1) {
			totalPrecio = producto.getPrecio();
		} else {
			totalPrecio += producto.getPrecio();
		}
		contador++;
		double precioTotal = Math.round(totalPrecio * 100.0) / 100.0;
		if (precioTotal == 0) {
			precioTotal = 0.0;
		}
		session.setAttribute("listaCarrito", listaCarrito);
		session.setAttribute("precioTotal", precioTotal);
		session.setAttribute("numeroProducto", contador);
		return "redirect:/producto/carrito";
	}

	@GetMapping("/carrito/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") int id, HttpSession session) {
		List<Productos> listaCarrito = (List<Productos>) session.getAttribute("listaCarrito");
		Productos producto = ps.buscarPorId(id);
		ps.borrarElementoCarrito(listaCarrito, id);
		session.setAttribute("listaCarrito", listaCarrito);
		session.setAttribute("precioTotal", totalPrecio - producto.getPrecio());
		return "redirect:/producto/carrito";
	}

	@GetMapping("/carrito/vaciarCarrito")
	public String vaciarCarrito(HttpSession session) {
		List<Productos> listaCarrito = (List<Productos>) session.getAttribute("listaCarrito");
		// ps.vaciarCarrito(listaCarrito);
		session.setAttribute("listaCarrito", null);
		session.setAttribute("precioTotal", 0.0);
		return "redirect:/producto/carrito";
	}

	@GetMapping("/detalleProducto")
	public String detalleProducto(HttpSession session) {
		return "/producto/detalleProducto";
	}

	@GetMapping("/detalleProducto/{id}")
	public String detalleProductoId(@PathVariable("id") int id, HttpSession session) {
		Productos producto = ps.buscarPorId(id);
		session.setAttribute("producto", producto);
		return "redirect:/producto/detalleProducto";
	}

	@GetMapping("/carrito")
	public String verCarrito(HttpSession session) {
		return "/producto/listarProductosCarrito";
	}
}
