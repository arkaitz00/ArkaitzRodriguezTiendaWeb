package main.java.curso.java.tienda.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import main.java.curso.java.tienda.model.Roles;

public interface RolesRepository extends CrudRepository<Roles, Integer>{
	
}