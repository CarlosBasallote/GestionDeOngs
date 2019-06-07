package com.salesianostriana.proyectofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Voluntario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@NotNull
	@NotEmpty
	private String nombre;
	@NotNull
	@NotEmpty
	private String apellidos;
	@NotNull
	@NotEmpty
	private String mail;
	private boolean isAdmin;
	@NotNull
	@NotEmpty
	private String password;
	private double donado;
	private String foto;
	@ManyToOne
	private Ong ong;
	/*
	 * @ManyToMany(mappedBy = "voluntarios") private List<Excursion> excursiones =
	 * new ArrayList<>();
	 */

	public Voluntario(String nombre, String apellidos, boolean isAdmin, String mail, String password, String foto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.isAdmin = false;
		this.mail = mail;
		this.password = password;
		this.foto = foto;
	}

	public Voluntario() {

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getDonado() {
		return donado;
	}

	public void setDonado(double donado) {
		this.donado = donado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	/*
	 * public List<Excursion> getExcursiones() { return excursiones; }
	 * 
	 * public void setExcursiones(List<Excursion> excursiones) { this.excursiones =
	 * excursiones; }
	 */

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		long temp;
		temp = Double.doubleToLongBits(donado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		// result = prime * result + ((excursiones == null) ? 0 :
		// excursiones.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((ong == null) ? 0 : ong.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Voluntario other = (Voluntario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (Double.doubleToLongBits(donado) != Double.doubleToLongBits(other.donado))
			return false;
		/*
		 * if (excursiones == null) { if (other.excursiones != null) return false; }
		 * else if (!excursiones.equals(other.excursiones)) return false;
		 */
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (ong == null) {
			if (other.ong != null)
				return false;
		} else if (!ong.equals(other.ong))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/* HELPERS */
	/*
	 * public void addExcursion(Excursion excursion) {
	 * this.excursiones.add(excursion); }
	 */

	public void addOng(Ong ong) {
		ong.getVoluntarios().add(this);
		this.ong = ong;
	}

}
