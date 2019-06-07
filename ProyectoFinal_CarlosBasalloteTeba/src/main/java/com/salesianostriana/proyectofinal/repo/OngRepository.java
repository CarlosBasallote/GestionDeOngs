package com.salesianostriana.proyectofinal.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.proyectofinal.model.Ong;

public interface OngRepository extends JpaRepository<Ong, Long> {

	public List<Ong> findByNombreContainingIgnoreCase(String nombre);

	public List<Ong> findTop5By(Sort sort);

	public List<Ong> findByContinente(String continente);

}
