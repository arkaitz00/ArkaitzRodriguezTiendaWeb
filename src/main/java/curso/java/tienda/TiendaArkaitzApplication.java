package main.java.curso.java.tienda;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.java.curso.java.tienda.utils.DatosJson;

@SpringBootApplication
public class TiendaArkaitzApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaArkaitzApplication.class, args);
		ArrayList<String> listadoProvincias = DatosJson.obtenerProvincias();
		System.out.println(listadoProvincias);
	}

}
