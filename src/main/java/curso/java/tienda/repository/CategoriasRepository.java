package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.java.curso.java.tienda.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
	
	List<Categorias> findAll();
	
	@Query("select c from Categorias c")
	List<Categorias> listarCategorias();
}
