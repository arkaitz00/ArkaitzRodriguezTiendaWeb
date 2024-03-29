package main.java.curso.java.tienda.model;

// default package
// Generated 04-may-2021 10:46:52 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Valoraciones generated by hbm2java
 */
@Entity
@Table(name = "valoraciones", catalog = "tienda_arkaitz_rodriguez_martinez")
public class Valoraciones implements java.io.Serializable {

	private Integer id;
	private Integer idProducto;
	private Integer idUsuario;
	private Integer valoracion;
	private String comentario;

	public Valoraciones() {
	}

	public Valoraciones(Integer idProducto, Integer idUsuario, Integer valoracion, String comentario) {
		this.idProducto = idProducto;
		this.idUsuario = idUsuario;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "id_producto")
	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	@Column(name = "id_usuario")
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "valoracion")
	public Integer getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	@Column(name = "comentario")
	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
