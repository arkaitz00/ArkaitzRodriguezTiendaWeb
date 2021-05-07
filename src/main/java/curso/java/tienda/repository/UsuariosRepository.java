package main.java.curso.java.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.curso.java.tienda.model.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	Usuarios findByEmail(String email);
	Usuarios findByDni(String dni);
	Usuarios deleteByDni(String dni);
}
