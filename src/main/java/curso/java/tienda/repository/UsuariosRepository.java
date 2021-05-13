package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.java.curso.java.tienda.model.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	Usuarios findByEmail(String email);
	Usuarios findById(int id);
	Usuarios findByDni(String dni);
	List<Usuarios> findByIdRol(Integer idRol);
	Usuarios deleteByDni(String dni);
	List<Usuarios> findAll();
}
