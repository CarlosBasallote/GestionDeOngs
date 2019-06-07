package com.salesianostriana.proyectofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.proyectofinal.model.Donacion;
import com.salesianostriana.proyectofinal.repo.DonacionRepository;

@Service
public class DonacionService {

	@Autowired
	private DonacionRepository repo;

	public List<Donacion> findAlldonaciones() {
		return repo.findAll();
	}

	public Donacion findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Donacion addDonacion(Donacion donacion) {
		return repo.save(donacion);

	}

	public void delete(Donacion donacion) {

		repo.delete(donacion);
	}

}
