package main.java.curso.java.tienda.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.model.Pedidos;
import main.java.curso.java.tienda.model.Usuarios;
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
	
	public void opcionesPedido(Pedidos p) {
		pr.save(p);
	}
	
	public List<Pedidos> totalPedidosUsuario(Usuarios u){
		List<Pedidos> listado = pr.findAll();
		List<Pedidos> listadoUsuario = new ArrayList<>();
		for (Pedidos pedidos : listado) {
			if(pedidos.getIdUsuario() == u.getId()) {
				listadoUsuario.add(pedidos);
			}
			
		}
		
		return listadoUsuario;
	}
	
	public Pedidos buscarId(int id) {
		return pr.findById(id);
	}
	
	public List<Pedidos> pedidosPendientesCancelo(){
		return pr.findByEstado("En espera de cancelar");		
	}
	
	public List<Pedidos> pedidosPendientesEnvio(){
		return pr.findByEstado("pendiente");		
	}
}
