package com.salesianostriana.proyectofinal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Excursion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idExcursion")
	private Long idExcursion;

	private String nombre;
	private String descripcion;
	private double precio;
	private String urlImagen;
	@ManyToOne
	private Ong ong;
	@ManyToMany
	private List<Voluntario> voluntarios = new ArrayList<>();

	public Excursion() {

	}

	public Excursion(String nombre, String descripcion, double precio, String urlImagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.urlImagen = urlImagen;
	}

	public Long getIdExcursion() {
		return idExcursion;
	}

	public void setIdExcursion(Long idExcursion) {
		this.idExcursion = idExcursion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((idExcursion == null) ? 0 : idExcursion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ong == null) ? 0 : ong.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((urlImagen == null) ? 0 : urlImagen.hashCode());
		result = prime * result + ((voluntarios == null) ? 0 : voluntarios.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Excursion other = (Excursion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idExcursion == null) {
			if (other.idExcursion != null)
				return false;
		} else if (!idExcursion.equals(other.idExcursion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ong == null) {
			if (other.ong != null)
				return false;
		} else if (!ong.equals(other.ong))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (urlImagen == null) {
			if (other.urlImagen != null)
				return false;
		} else if (!urlImagen.equals(other.urlImagen))
			return false;
		if (voluntarios == null) {
			if (other.voluntarios != null)
				return false;
		} else if (!voluntarios.equals(other.voluntarios))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Excursion [idExcursion=" + idExcursion + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", urlImagen=" + urlImagen + ", ong=" + ong + ", voluntarios=" + voluntarios
				+ "]";
	}

	/* HELPERS */
	/*
	 * public void addVoluntario(Voluntario voluntario) {
	 * this.voluntarios.add(voluntario); voluntario.addExcursion(this); }
	 */

}
