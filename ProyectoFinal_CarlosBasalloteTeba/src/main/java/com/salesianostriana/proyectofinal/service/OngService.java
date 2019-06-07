package com.salesianostriana.proyectofinal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.salesianostriana.proyectofinal.hello.imgur.Imagen;
import com.salesianostriana.proyectofinal.hello.imgur.ImgurService;
import com.salesianostriana.proyectofinal.hello.imgur.RespuestaImagen;
import com.salesianostriana.proyectofinal.model.Ong;
import com.salesianostriana.proyectofinal.repo.OngRepository;

@Service
public class OngService {

	@Autowired
	ImgurService imgurService;

	@Autowired
	private OngRepository repo;

	public List<Ong> findAllOngs() {
		return repo.findAll();
	}

	public Ong findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Ong addOng(Ong ong) {
		return repo.save(ong);

	}

	public void delete(Ong ong) {
		repo.delete(ong);
	}

	public void dona(Ong ong, double donacion) {
		ong.setPresupuesto(ong.getPresupuesto() + donacion);
		repo.save(ong);
	}

	public List<Ong> findByNombre(String nombre) {
		return repo.findByNombreContainingIgnoreCase(nombre);
	}

	public List<Ong> findTopOngs() {

		List<Ong> ongsOrden = new ArrayList<Ong>();
		ongsOrden = repo.findAll();

		Collections.sort(ongsOrden, new ComparaPorVoluntarios());

		return ongsOrden;
	}

	public List<Ong> findByContinente(String continente) {

		List<Ong> results = new ArrayList<Ong>();
		for (Ong ong : repo.findAll()) {
			if (ong.getContinente().equals(continente)) {
				results.add(ong);
			}
		}
		return results;
	}

	public boolean saveAndUpload(Ong ong, MultipartFile file) {

		boolean success = false;
		// TODO Mejorar la gestión de errores, usando el mecanismo de Spring

		if (file.isEmpty()) {
			repo.save(ong);
		} else {

			try {
				RespuestaImagen r = imgurService.uploadToImgur(
						new Imagen(Base64.encodeBase64String(file.getBytes()), file.getOriginalFilename()));
				if (r != null) {
					if (r.getStatus() == 200) {
						ong.setUrlImagen(r.getData().getLink());
						repo.save(ong);
						success = true;
					}
				}
			} catch (IOException e) {
				success = false;
			}
		}
		return success;

	}

}
