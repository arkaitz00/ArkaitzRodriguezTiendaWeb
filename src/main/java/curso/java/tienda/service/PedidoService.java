package main.java.curso.java.tienda.service;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.model.Pedidos;
import main.java.curso.java.tienda.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pr;
	
	static final Logger logger = LogManager.getLogger(PedidoService.class);
	
	public Pedidos crearPedido(int idUsuario, Timestamp fecha, String metodo_pago, String estado, String num_factura, double total) {	
		Pedidos p = new Pedidos(idUsuario, fecha, metodo_pago, estado, num_factura, total);
		pr.save(p);
		logger.info("Pedido creado");
		return p;
	}	
}
