package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.java.curso.java.tienda.model.Productos;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {
	Productos findById(int id);

	List<Productos> findAll();
	
	Productos deleteById(int id);

	@Query("select p from Productos p")
	List<Productos> listarProductos();
	
	@Query("select p from Productos p where id_categoria = ?1")
	List<Productos> listarProductosCategoria(int id);
}
