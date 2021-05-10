package main.java.curso.java.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.java.curso.java.tienda.model.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	Usuarios findByEmail(String email);
	Usuarios findById(int id);
	Usuarios findByDni(String dni);
	Usuarios deleteByDni(String dni);
	List<Usuarios> findAll();
	@Query("select u from Usuarios u where u.nombre like %?1% or u.apellido1 like %?1% or u.apellido2 like %?1%")
	List<Usuarios> buscarPorNombreOApellidos(String cadena);
}
