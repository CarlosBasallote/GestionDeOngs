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
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.repo.VoluntarioRepository;

@Service
public class VoluntarioService {

	@Autowired
	ImgurService imgurService;

	@Autowired
	private VoluntarioRepository repo;

	public Voluntario login(String mail, String password) {
		return repo.findFirstByMailAndPassword(mail, password);
	}

	public List<Voluntario> findAllvoluntarios() {
		return repo.findAll();
	}

	public Voluntario findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Voluntario addVoluntario(Voluntario voluntario) {
		return repo.save(voluntario);

	}

	public void delete(Voluntario voluntario) {

		repo.delete(voluntario);
	}

	public void donado(Voluntario vol, double donacion) {
		vol.setDonado(vol.getDonado() + donacion);
		repo.save(vol);
	}

	public List<Voluntario> findByNombre(String nombre) {
		return repo.findByNombreIgnoreCaseStartingWith(nombre);
	}

	public boolean saveAndUpload(Voluntario vol, MultipartFile file) {

		boolean success = false;
		// TODO Mejorar la gestión de errores, usando el mecanismo de Spring

		if (file.isEmpty()) {
			repo.save(vol);
		} else {

			try {
				RespuestaImagen r = imgurService.uploadToImgur(
						new Imagen(Base64.encodeBase64String(file.getBytes()), file.getOriginalFilename()));
				if (r != null) {
					if (r.getStatus() == 200) {
						vol.setFoto(r.getData().getLink());
						repo.save(vol);
						success = true;
					}
				}
			} catch (IOException e) {
				success = false;
			}
		}
		return success;

	}

	public List<Voluntario> findTopVolunteers() {

		List<Voluntario> volOrden = new ArrayList<Voluntario>();
		volOrden = repo.findAll();

		Collections.sort(volOrden, new ComparaPorDonado());

		return volOrden;
	}

}