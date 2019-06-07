package com.salesianostriana.proyectofinal.service;

import java.util.Comparator;

import com.salesianostriana.proyectofinal.model.Voluntario;

public class ComparaPorDonado implements Comparator<Voluntario> {

	public int compare(Voluntario vol1, Voluntario vol2) {

		double donado1 = vol1.getDonado();
		double donado2 = vol2.getDonado();

		if (donado1 < donado2) {
			return 1;
		} else {
			if (donado1 > donado2) {
				return -1;
			}
			return 0;
		}
	}

}
