package com.salesianostriana.proyectofinal.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.proyectofinal.model.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

	public List<Voluntario> findByNombreIgnoreCaseStartingWith(String nombre);
	
	public List<Voluntario> findTop5By(Sort sort);
	
	public Voluntario findFirstByMailAndPassword(String mail, String password);

}
