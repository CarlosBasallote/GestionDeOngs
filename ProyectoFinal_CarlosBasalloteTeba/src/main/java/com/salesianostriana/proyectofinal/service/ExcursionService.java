package com.salesianostriana.proyectofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.proyectofinal.model.Excursion;
import com.salesianostriana.proyectofinal.repo.ExcursionRepository;

@Service
public class ExcursionService {

	@Autowired
	private ExcursionRepository repo;

	public List<Excursion> findAllexcursiones() {
		return repo.findAll();
	}

	public Excursion findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Excursion addVoluntario(Excursion excursion) {
		return repo.save(excursion);

	}

	public void delete(Excursion excursion) {

		repo.delete(excursion);
	}

}