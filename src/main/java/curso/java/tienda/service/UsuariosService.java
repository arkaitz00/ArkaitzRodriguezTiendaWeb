package main.java.curso.java.tienda.service;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.model.Usuarios;
import main.java.curso.java.tienda.repository.UsuariosRepository;


@Service
public class UsuariosService {

	static final Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	UsuariosRepository ur;
	
	public Usuarios crearUsuario(int idRol, String email, String clave, String nombre, String apellido1, String apellido2, String direccion, String municipio, String provincia, String telefono, String dni) {		
		if(!existeUsuarioDni(dni)) {
			Usuarios u = new Usuarios(idRol, email, clave, nombre, apellido1, apellido2, direccion, municipio, provincia, telefono, dni);
			logger.info("Usuario creado");
			ur.save(u);
			return u;
		}
		logger.warn("El usuario ya existe");
		return null;
	}
	
	public boolean existeUsuarioDni(String dni) {
		if(ur.findByDni(dni) != null) {
			logger.warn("El usuario existe");
			return true;
		}
		logger.warn("El usuario no existe");
		return false;
	}
	
	public Usuarios devolverUsuarioEmail(String email) {
		Usuarios u = ur.findByEmail(email);
		if(u != null) {
			logger.info("El usuario con el correo indicado existe");
			return u;
		}
		logger.warn("El usuario no existe");
		return null;
	}
	
	public void borrarUsuario(String dni) {
		if(existeUsuarioDni(dni)) {
			ur.deleteByDni(dni);
			logger.info("El usuario ha sido borrado correctamente");
		}else {
			logger.warn("El usuario no se ha podido borrar, porque no existe");
		}
	}
	
	public void editarUsuario(Usuarios u, String email, String nombre, String apellido1, String apellido2, String telefono) {
		u.setEmail(email);
		u.setNombre(nombre);
		u.setApellido1(apellido1);
		u.setApellido2(apellido2);
		u.setTelefono(telefono);
	}
}
