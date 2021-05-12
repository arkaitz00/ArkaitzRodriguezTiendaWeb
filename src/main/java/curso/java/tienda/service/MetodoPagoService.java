package main.java.curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.repository.MetodoPagoRepository;
import main.java.curso.java.tienda.model.MetodosPago;

@Service
public class MetodoPagoService {

	@Autowired
	MetodoPagoRepository mpr;
	
	public List<MetodosPago> metodosPago(){
		return mpr.findAll();
	}
	
	public MetodosPago pago(int id) {
		return mpr.findById(id);
	}
}
