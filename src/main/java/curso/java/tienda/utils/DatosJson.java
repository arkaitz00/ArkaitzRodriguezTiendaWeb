package main.java.curso.java.tienda.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

public class DatosJson {
	private static final String url_provincias = "https://raw.githubusercontent.com/IagoLast/pselect/master/data/provincias.json";
	private static final String url_municipios = "https://raw.githubusercontent.com/IagoLast/pselect/master/data/municipios.json";
	
	private static TreeMap<String, String> listadoProvinciasOrdenado;
	
	public static TreeMap<String, String> getListadoProvinciasOrdenado() {
		return listadoProvinciasOrdenado;
	}
	
	public static void setListadoProvinciasOrdenado(TreeMap<String, String> listadoProvinciasOrdenado) {
		DatosJson.listadoProvinciasOrdenado = listadoProvinciasOrdenado;
	}
	
	public static ArrayList<String> obtenerProvincias() {
		ArrayList<String> listadoProvincias = new ArrayList<String>();
		
		HashMap<String, String> provincias = DatosJson.obtenerProvinciasX();
		
		
		listadoProvinciasOrdenado = new TreeMap<>(provincias);
		
		for (Map.Entry<String, String> entry : listadoProvinciasOrdenado.entrySet()) {
			String p = entry.getKey();
			listadoProvincias.add(p); //se forma el listado de provincias
		}

        return listadoProvincias;
	}

	public static ArrayList<String> obtenerProvinciasPrueba() {
		ArrayList<String> listado = new ArrayList<String>();
		
		String cadenaJson = FicheroUrl.url(url_provincias);
		ClaseJson[] listadoProvincias = new Gson().fromJson(cadenaJson, ClaseJson[].class);
		
        for (ClaseJson provincia : listadoProvincias) {
            //System.out.println(provincia.getNm());
        	listado.add(provincia.getNm());
        }
        
		return listado;
	}
	
	private static HashMap<String, String> obtenerProvinciasX() {
		HashMap<String, String> listado = new HashMap<String, String>();
		
		String cadenaJson = FicheroUrl.url(url_provincias);
		ClaseJson[] listadoProvincias = new Gson().fromJson(cadenaJson, ClaseJson[].class);
		
        for (ClaseJson provincia : listadoProvincias) {
        	listado.put(provincia.getNm(), provincia.getId());
        }
        
		return listado;
	}
		
	public static ArrayList<String> obtenerMunicipiosXProvincia(String nombre) {
		ArrayList<String> listado = new ArrayList<String>();
		
		String codProvincia = listadoProvinciasOrdenado.get(nombre);
		
		String cadenaJson = FicheroUrl.url(url_municipios);
		ClaseJson[] listadoMunicipios = new Gson().fromJson(cadenaJson, ClaseJson[].class);
		
        for (ClaseJson municipio : listadoMunicipios) {
        	//Comprobar que municipios tienen como comienzo de cÃ³digo el cÃ³digo de provincia recibido
        	String codMunicipio = municipio.getId();
        	if(codMunicipio.substring(0, 2).equals(codProvincia)) {
        		listado.add( municipio.getNm());
        	}
        		
        }
        
		return listado;
	} 
}
