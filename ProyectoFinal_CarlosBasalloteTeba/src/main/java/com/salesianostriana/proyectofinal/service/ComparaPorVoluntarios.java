package com.salesianostriana.proyectofinal.service;

import java.util.Comparator;

import com.salesianostriana.proyectofinal.model.Ong;

public class ComparaPorVoluntarios implements Comparator<Ong> {

	public int compare(Ong ong1, Ong ong2) {

		int tamanyo1 = ong1.getVoluntarios().size();
		int tamanyo2 = ong2.getVoluntarios().size();

		if (tamanyo1 < tamanyo2) {
			return 1;
		} else {
			if (tamanyo1 > tamanyo2) {
				return -1;
			}
			return 0;
		}
	}

}