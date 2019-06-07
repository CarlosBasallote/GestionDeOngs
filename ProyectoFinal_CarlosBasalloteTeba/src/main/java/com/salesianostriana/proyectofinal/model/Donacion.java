package com.salesianostriana.proyectofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Donacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idDonacion")
	private Long idDonacion;

	private String objetivo;
	private double precio;
	private boolean administrado;
	@ManyToOne
	private Ong ong;

	public Donacion(String objetivo, double precio) {
		this.objetivo = objetivo;
		this.precio = precio;
	}

	public Donacion() {

	}

	public Long getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Long idDonacion) {
		this.idDonacion = idDonacion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isAdministrado() {
		return administrado;
	}

	public void setAdministrado(boolean administrado) {
		this.administrado = administrado;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrado ? 1231 : 1237);
		result = prime * result + ((idDonacion == null) ? 0 : idDonacion.hashCode());
		result = prime * result + ((objetivo == null) ? 0 : objetivo.hashCode());
		result = prime * result + ((ong == null) ? 0 : ong.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Donacion other = (Donacion) obj;
		if (administrado != other.administrado)
			return false;
		if (idDonacion == null) {
			if (other.idDonacion != null)
				return false;
		} else if (!idDonacion.equals(other.idDonacion))
			return false;
		if (objetivo == null) {
			if (other.objetivo != null)
				return false;
		} else if (!objetivo.equals(other.objetivo))
			return false;
		if (ong == null) {
			if (other.ong != null)
				return false;
		} else if (!ong.equals(other.ong))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Donacion [idDonacion=" + idDonacion + ", objetivo=" + objetivo + ", precio=" + precio
				+ ", administrado=" + administrado + ", ong=" + ong + "]";
	}

}
