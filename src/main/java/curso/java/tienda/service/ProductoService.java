package main.java.curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.repository.ProductoRepository;
import main.java.curso.java.tienda.model.Productos;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository pr;
	
	public Productos buscarPorId(int id){
		return pr.findById(id);
	}
	
	public List<Productos> listadoCategoria(int id){
		return pr.listarProductosCategoria(id);
	}
	
	public List<Productos> listado(){
		List<Productos> listado = pr.listarProductos();
		return listado;
	}
	
	public List<Productos> vaciarCarrito(List<Productos> carrito){
		for (int i = 0; i < carrito.size(); i++) {
			carrito.remove(i);
		}
		return carrito;
	}
	
	public List<Productos> borrarElementoCarrito(List<Productos> carrito, int id){
		Productos producto = buscarPorId(id);
		for (int i = 0; i < carrito.size(); i++) {
			if(carrito.get(i).getId() == id) {
				carrito.remove(i);
			}
		}
		return carrito;
	}
}
