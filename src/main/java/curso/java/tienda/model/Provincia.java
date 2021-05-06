package curso.java.tienda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provincia {
	@SerializedName("idProvincia")
    @Expose
	String id;
	
	@SerializedName("nombreProvincia")
    @Expose
	String nombre;
	
	public Provincia() {
		
	}
	
	public Provincia(String id, String nombre) {
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
