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
	
	public boolean crearUsuario(Usuarios u) {
		if(!existeUsuarioDni(u.getDni())) {
			logger.info("Usuario creado");
			ur.save(u);
			return true;
		}
		logger.warn("El usuario ya existe");
		return false;
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
	
	/*public boolean existeUsuario(Usuarios u) {
		if(ur.findByUsuarios(u)) {
			logger.info("El objeto usuario existe");
			return true;
		}
		logger.warn("El objeo usuario no existe");
		return false;
	}*/
	
	public void borrarUsuario(String dni) {
		if(existeUsuarioDni(dni)) {
			ur.deleteByDni(dni);
			logger.info("El usuario ha sido borrado correctamente");
		}else {
			logger.warn("El usuario no se ha podido borrar, porque no existe");
		}
	}
}
