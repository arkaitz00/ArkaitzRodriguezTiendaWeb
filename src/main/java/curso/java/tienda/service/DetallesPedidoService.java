package main.java.curso.java.tienda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.curso.java.tienda.model.DetallesPedido;
import main.java.curso.java.tienda.repository.DetallesPedidoRepository;

import java.util.List;

@Service
public class DetallesPedidoService {

	@Autowired
	DetallesPedidoRepository dpr;
	
	static Logger logger = LogManager.getLogger(DetallesPedidoService.class);
	
	public List<DetallesPedido> totalDetallesPedido(){
		return dpr.findAll();
	}
	
	public DetallesPedido crearDetallePedido(int idPedido, int idProducto, double precioUnidad, int unidades, float impuesto, double total) {
		DetallesPedido dp = new DetallesPedido(idPedido, idProducto, precioUnidad, unidades, impuesto, total);
		dpr.save(dp);
		return dp;
	}
	
	public void eliminarDetallePedido(DetallesPedido dp) {
		if(dpr.existsById(dp.getId())) {
			dpr.delete(dp);
			logger.info("El detalle de pedido de ha eliminado correctamente");
		}
		
		logger.info("El detalle de pedido no existe, y no se ha podido eliminar");
	}
	
	public List<DetallesPedido> detallesPedido(int id){
		return dpr.findByIdPedido(id);
	}
}
