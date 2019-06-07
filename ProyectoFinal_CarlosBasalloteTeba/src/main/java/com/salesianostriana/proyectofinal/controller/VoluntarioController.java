package com.salesianostriana.proyectofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.proyectofinal.formbean.SearchBean;
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.service.OngService;
import com.salesianostriana.proyectofinal.service.VoluntarioService;

@Controller
@RequestMapping("/admin")
public class VoluntarioController {

	@Autowired
	VoluntarioService volSer;

	@Autowired
	OngService ongSer;

	String mailAntiguo;

	@GetMapping("/voluntarios")
	public String ListaVoluntarios(Model model) {
		List<Voluntario> result = volSer.findAllvoluntarios();
		for (Voluntario voluntario : volSer.findAllvoluntarios()) {
			if (voluntario.isAdmin() == true) {
				model.addAttribute("admin", voluntario);
			}
		}
		model.addAttribute("list", result);
		model.addAttribute("searchForm", new SearchBean());
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/voluntarios";
	}

	@PostMapping("/searchVol")
	public String searchVoluntario(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {
		model.addAttribute("list", volSer.findByNombre(searchBean.getSearchVol()));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		return "admin/voluntarios";
	}

	@GetMapping("/addVoluntario")
	public String formularioAnadir(Model model) {

		Voluntario voluntario = new Voluntario();
		model.addAttribute("voluntarioForm", voluntario);
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		return "admin/addVoluntario";
	}

	@PostMapping("/verificarVoluntario")
	public String submit(@Valid @ModelAttribute("voluntarioForm") Voluntario voluntario, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, Model model) {
		boolean repetido = false;
		for (Voluntario vol : volSer.findAllvoluntarios()) {
			if (vol.getMail().equals(voluntario.getMail())) {
				repetido = true;
			}
		}

		if (bindingResult.hasErrors()) {
			return "redirect:/admin/addVoluntario";
		}
		if (repetido == true) {
			model.addAttribute("uploadError", 1);
			model.addAttribute("voluntarioForm", voluntario);
			model.addAttribute("listaOngs", ongSer.findAllOngs());
			return "admin/addVoluntario";
		} else {
			model.addAttribute("voluntario", voluntario);
			volSer.addVoluntario(voluntario);
			if (volSer.saveAndUpload(voluntario, file)) {
				return "redirect:/admin/voluntarios";
			} else {
				model.addAttribute("uploadError", "Error en la subida de fichero");

				return "redirect:/admin/voluntarios";
			}
		}
	}

	@GetMapping("/borrarVoluntario/{idVoluntario}")
	public String borrar(@PathVariable("idVoluntario") Long id, Model model) {
		Voluntario vol = volSer.findById(id);
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		if (vol != null) {
			volSer.delete(vol);
			return "redirect:/admin/voluntarios";
		} else {
			return "error";
		}

	}

	@GetMapping("/editVoluntario/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("volForm", volSer.findById(id));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		this.mailAntiguo = volSer.findById(id).getMail();

		if (ongSer.findAllOngs().isEmpty() == false) {
			model.addAttribute("listaOngs", ongSer.findAllOngs());
		}
		return "admin/editVoluntario";
	}

	@PostMapping("/{id}/voluntarioEditado")
	public String editarObra(@Valid @ModelAttribute("voluntarioForm") Voluntario voluntario,
			BindingResult bindingResult, @RequestParam("file") MultipartFile file, Model model) {
		boolean repetido = false;
		for (Voluntario vol : volSer.findAllvoluntarios()) {
			if (vol.getMail().equals(voluntario.getMail()) && !voluntario.getMail().equals(mailAntiguo)) {
				repetido = true;
			}
		}

		if (bindingResult.hasErrors()) {
			return "redirect:/admin/editVoluntario/" + voluntario.getId();
		}
		if (repetido == true) {
			model.addAttribute("uploadError", 1);
			model.addAttribute("voluntarioForm", voluntario);
			model.addAttribute("listaOngs", ongSer.findAllOngs());
			model.addAttribute("volForm", volSer.findById(voluntario.getId()));
			return "admin/editVoluntario";
		} else {

			if (volSer.saveAndUpload(voluntario, file)) {
				volSer.addVoluntario(voluntario);
				return "redirect:/admin/voluntarios";
			} else {
				model.addAttribute("uploadError", "Error en la subida de fichero");

				return "redirect:/admin/voluntarios";
			}
		}
	}

	@GetMapping("/viewVoluntario/{id}")
	public String viewById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		model.addAttribute("voluntario", volSer.findById(id));
		model.addAttribute("admin", volSer.findById(id).isAdmin());

		return "admin/voluntarioView";
	}

	@GetMapping("/topVoluntarios")
	public String ListaTopVoluntarios(Model model) {

		for (Voluntario voluntario : volSer.findAllvoluntarios()) {
			if (voluntario.isAdmin() == true) {
				model.addAttribute("admin", voluntario);
			}
		}

		model.addAttribute("listop", volSer.findTopVolunteers());
		model.addAttribute("searchForm", new SearchBean());
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/topVoluntarios";
	}

}
