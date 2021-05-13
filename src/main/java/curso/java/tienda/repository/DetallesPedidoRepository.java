package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.DetallesPedido;

public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido, Integer>{
	
	List<DetallesPedido> findByIdPedido(int id);

}
