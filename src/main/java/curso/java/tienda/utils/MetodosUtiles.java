package curso.java.tienda.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class MetodosUtiles {
	
	private static Logger logger = LogManager.getLogger(MetodosUtiles.class);
	
	public static String nombreRol(int idRol){
		String nombreRol = "";
		switch(idRol){
			case 1:
				nombreRol = "Admin";
			break;
			case 2:
				nombreRol = "Empleado";
			break;
			case 3:
				nombreRol = "Cliente";
			break;
			case 4:
				nombreRol = "Gerente";
			break;
		}
		return nombreRol;
	}
	
	public static String mes(int numeroMes){
		String mes = "";
		switch(numeroMes){
			case 1 :
				mes = "Enero";
			break;
			case 2 :
				mes = "Febrero";
			break;
			case 3 :
				mes = "Marzo";
			break;
			case 4 :
				mes = "Abril";
			break;
			case 5 :
				mes = "Mayo";
			break;
			case 6 :
				mes = "Junio";
			break;
			case 7 :
				mes = "julio";
			break;
			case 8 :
				mes = "Agosto";
			break;
			case 9 :
				mes = "Septiembre";
			break;
			case 10 :
				mes = "Octubre";
			break;
			case 11 :
				mes = "Noviembre";
			break;
			case 12 :
				mes = "Diciembre";
			break;
		}
		return mes;
	}
	
	public static List<String> leerFichero(String ficheroUrl){
		List<String> lista = new ArrayList<>();
		
		return lista;
	}
}

