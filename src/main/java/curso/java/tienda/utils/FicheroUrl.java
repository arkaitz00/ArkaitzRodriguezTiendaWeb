package main.java.curso.java.tienda.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FicheroUrl {
	public static String fichero(String fichero) {
		String output = "";
		
		try {
			FileReader f = new FileReader(fichero);
	
	        BufferedReader b = new BufferedReader(f);

	        String cadenaLeida;
	        while ((cadenaLeida = b.readLine()) != null) {
	        	output += cadenaLeida;
	        }
	        b.close();
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return output;		
	}
	
	public static String url(String sUrl) {
		String output = "";
		
		try {
			URL url = new URL(sUrl);
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        //conn.setRequestProperty("Accept", "application/json");
	        
	        if (conn.getResponseCode() != 200) {
	            //si la respuesta del servidor es distinta al codigo 200 lanzaremos una Exception
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	        }
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        //creamos un StringBuilder para almacenar la respuesta del web service
	        StringBuilder sb = new StringBuilder();
	        int cp;
	        while ((cp = br.read()) != -1) {
	          sb.append((char) cp);
	        }
	        //en la cadena output almacenamos toda la respuesta del servidor
	        output = sb.toString();
	        //System.out.println(output);	        
        
	        conn.disconnect();
		} catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
		
		return output;
	}
}
