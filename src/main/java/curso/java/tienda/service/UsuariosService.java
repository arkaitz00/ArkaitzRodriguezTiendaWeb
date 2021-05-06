package curso.java.tienda.service;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.model.Usuarios;
import curso.java.tienda.repository.UsuariosRepository;

@Service
public class UsuariosService {

	static final Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	UsuariosRepository ur;
	
	public boolean crearUsuario(Usuarios u) {
		if(existeUsuario(u.getId())) {
			ur.save(u);
			return true;
		}
		return false;
	}
	
	public boolean existeUsuario(int id) {
		if(ur.findById(id) != null) {
			return true;
		}
		return false;
	}
	
	public void borrarUsuario(int id) {
		if(existeUsuario(id)) {
			ur.deleteById(id);
			logger.info("El usuario ha sido borrado correctamente");
		}else {
			logger.warn("El usuario no se ha podido borrar, porque no existe");
		}
	}
}
