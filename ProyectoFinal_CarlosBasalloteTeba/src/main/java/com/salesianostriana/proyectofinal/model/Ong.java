package com.salesianostriana.proyectofinal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Ong implements Comparable<Ong> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@NotNull
	@NotEmpty
	private String nombre;
	@NotNull
	@NotEmpty
	private String pais;
	@NotNull
	@NotEmpty
	private String continente;
	private double presupuesto;
	private String urlImagen;
	@Lob
	private String descripcion;
	/*
	 * @OneToMany(mappedBy = "ong") private List<Donacion> donaciones = new
	 * ArrayList<>();
	 */
	@OneToMany(mappedBy = "ong")
	private List<Voluntario> voluntarios = new ArrayList<>();
	/*
	 * @OneToMany(mappedBy = "ong") private List<Excursion> excursiones = new
	 * ArrayList<>();
	 */

	public Ong(String nombre, String pais, String continente, double presupuesto, String descripcion) {
		this.nombre = nombre;
		this.pais = pais;
		this.continente = continente;
		this.presupuesto = presupuesto;
		this.descripcion = descripcion;
	}

	public Ong() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	/*
	 * public List<Donacion> getDonaciones() { return donaciones; }
	 * 
	 * public void setDonaciones(List<Donacion> donaciones) { this.donaciones =
	 * donaciones; }
	 */

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	/*
	 * public List<Excursion> getExcursiones() { return excursiones; }
	 * 
	 * public void setExcursiones(List<Excursion> excursiones) { this.excursiones =
	 * excursiones; }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continente == null) ? 0 : continente.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		long temp;
		temp = Double.doubleToLongBits(presupuesto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((urlImagen == null) ? 0 : urlImagen.hashCode());
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
		Ong other = (Ong) obj;
		if (continente == null) {
			if (other.continente != null)
				return false;
		} else if (!continente.equals(other.continente))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (Double.doubleToLongBits(presupuesto) != Double.doubleToLongBits(other.presupuesto))
			return false;
		if (urlImagen == null) {
			if (other.urlImagen != null)
				return false;
		} else if (!urlImagen.equals(other.urlImagen))
			return false;
		return true;
	}

	/* HELPERS */

	/*
	 * public void addExcursion(Excursion excursion) { excursiones.add(excursion);
	 * excursion.setOng(this); }
	 */

	public void addVoluntarios(Voluntario voluntario) {
		voluntarios.add(voluntario);
		voluntario.setOng(this);
	}

	/*
	 * public void addDonacion(Donacion donacion) { donaciones.add(donacion);
	 * donacion.setOng(this); }
	 */

	/*
	 * public void removeExcursion(Excursion excursion) {
	 * excursiones.remove(excursion); excursion.setOng(null); }
	 */

	public void removeVoluntarios(Voluntario voluntario) {
		voluntarios.remove(voluntario);
		voluntario.setOng(null);
	}

	/*
	 * public void removeDonacion(Donacion donacion) { donaciones.remove(donacion);
	 * donacion.setOng(null); }
	 */

	public void addVoluntario(Voluntario voluntario) {
		this.voluntarios.add(voluntario);
	}

	@Override
	public int compareTo(Ong o) {
		if (voluntarios.size() < o.getVoluntarios().size()) {
			return 1;
		}
		if (voluntarios.size() > o.getVoluntarios().size()) {
			return -1;
		}
		return 0;
	}

}
