package curso.java.tienda.repository;

import org.springframework.data.repository.CrudRepository;

import curso.java.tienda.model.Usuarios;


public interface UsuariosRepository extends CrudRepository<Usuarios, Integer>{
	Usuarios findByEmail(String email);
}
