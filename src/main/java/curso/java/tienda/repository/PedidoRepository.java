package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.Pedidos;


public interface PedidoRepository extends JpaRepository<Pedidos, Integer>  {
	Pedidos findById(int id);
	
	List<Pedidos> findAll();
	
	List<Pedidos> findByEstado(String estado);
}
