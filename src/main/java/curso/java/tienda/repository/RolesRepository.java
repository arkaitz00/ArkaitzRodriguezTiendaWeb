package curso.java.tienda.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import curso.java.tienda.model.Roles;


public interface RolesRepository extends CrudRepository<Roles, Integer>{
	
}