package main.java.curso.java.tienda.repository;

import org.springframework.data.repository.CrudRepository;

import main.java.curso.java.tienda.model.Usuarios;


public interface UsuariosRepository extends CrudRepository<Usuarios, Integer>{
	Usuarios findByEmail(String email);
	Usuarios findByDni(String dni);
	Usuarios deleteByDni(String dni);
}
