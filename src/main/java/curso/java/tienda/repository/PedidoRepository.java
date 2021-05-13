package main.java.curso.java.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.Pedidos;


public interface PedidoRepository extends JpaRepository<Pedidos, Integer>  {
	Pedidos findById(int id);
}
