package main.java.curso.java.tienda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.model.Roles;
import main.java.curso.java.tienda.repository.RolesRepository;

@Service
public class RolesService {
	
	static final Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	RolesRepository rr;
	
	public boolean crearRol(Roles r) {
		if(existeRol(r.getId())) {
			rr.save(r);
			logger.info("El rol se ha creado correctamente");
			return true;
		}
		logger.warn("El rol no se ha podido crear correctamente");
		return false;
	}
	
	public boolean existeRol(int id) {
		if(rr.findById(id) != null) {
			logger.info("El rol existe");
			return true;
		}
		logger.warn("El rol no existe");
		return false;
	}
	
	public void borrarRol(int id) {
		if(existeRol(id)) {
			rr.deleteById(id);
			logger.info("El rol ha sido borrado correctamente");
		}else {
			logger.warn("El rol no se ha podido borrar, porque no existe");
		}
	}
}
