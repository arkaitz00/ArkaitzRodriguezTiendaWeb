package main.java.curso.java.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
}