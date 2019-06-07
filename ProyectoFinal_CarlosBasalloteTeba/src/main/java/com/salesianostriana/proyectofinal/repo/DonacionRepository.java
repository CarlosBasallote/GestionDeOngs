package com.salesianostriana.proyectofinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.proyectofinal.model.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

}
