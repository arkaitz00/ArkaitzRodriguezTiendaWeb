package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.MetodosPago;

public interface MetodoPagoRepository extends JpaRepository<MetodosPago, Integer>{
	MetodosPago findById(int id);
	List<MetodosPago> findAll();
}
