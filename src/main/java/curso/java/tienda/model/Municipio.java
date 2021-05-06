package curso.java.tienda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Municipio {
	@SerializedName("idMunicipio")
    @Expose
	String id;
	
	@SerializedName("nombreMunicipio")
    @Expose
	String nombre;
	
	public Municipio() {
		
	}
	
	public Municipio(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
