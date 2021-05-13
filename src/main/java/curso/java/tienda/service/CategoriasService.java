package main.java.curso.java.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.repository.CategoriasRepository;
import java.util.List;
import main.java.curso.java.tienda.model.Categorias;

@Service
public class CategoriasService {
	
	@Autowired
	CategoriasRepository cr;
	
	public List<Categorias> listarCategorias(){
		return cr.listarCategorias();
	}
}
