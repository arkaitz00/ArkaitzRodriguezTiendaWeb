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
	
	public List<Productos> listado(){
		List<Productos> listado = pr.listarProductos();
		return listado;
	}
}
