package com.salesianostriana.proyectofinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import com.salesianostriana.proyectofinal.model.Ong;
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.service.OngService;
import com.salesianostriana.proyectofinal.service.VoluntarioService;

@Controller
@RequestMapping("/admin")
public class OngController {

	@Autowired
	OngService ongSer;

	VoluntarioService volSer;

	String[] countryCodes = Locale.getISOCountries();
	List<String> paises = new ArrayList<>();

	@GetMapping("/ongs")
	public String ListaOngs(Model model) {
		List<Ong> result = ongSer.findAllOngs();
		model.addAttribute("list", result);
		model.addAttribute("searchForm", new SearchBean());
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/ongs";
	}

	@PostMapping("/searchOng")
	public String searchOng(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {
		model.addAttribute("list", ongSer.findByNombre(searchBean.getSearchOng()));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		return "admin/ongs";
	}

	@GetMapping("/addOng")
	public String formularioAnadir(Model model) {

		for (String countryCode : countryCodes) {

			Locale obj = new Locale("", countryCode);

			paises.add(obj.getDisplayCountry());

		}

		Ong ong = new Ong();
		model.addAttribute("paises", paises);
		model.addAttribute("ongForm", ong);
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/addOng";
	}

	@PostMapping("/verificarOng")
	public String submit(@Valid @ModelAttribute("ongForm") Ong ong, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, Model model) {

		if (bindingResult.hasErrors()) {
			return "redirect:/admin/addOng";
		} else {

			ongSer.addOng(ong);
			if (ongSer.saveAndUpload(ong, file)) {
				return "redirect:/admin/ongs";
			} else {
				model.addAttribute("uploadError", "Error en la subida de fichero");

				return "redirect:/admin/ongs";
			}

		}

	}

	@GetMapping("/borrarOng/{id}")
	public String borrar(@PathVariable("id") Long id, Model model) {
		Ong ong = ongSer.findById(id);
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		/* Recorre lista de voluntarios y poner ong a null */
		if (ong != null) {
			for (Voluntario vol : ong.getVoluntarios()) {
				vol.setOng(null);

			}
			ongSer.delete(ong);
			return "redirect:/admin/ongs";
		} else {
			return "error";
		}

	}

	@GetMapping("/editOng/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {

		for (String countryCode : countryCodes) {

			Locale obj = new Locale("", countryCode);

			paises.add(obj.getDisplayCountry());

		}

		model.addAttribute("ongForm", ongSer.findById(id));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("paises", paises);

		return "admin/editOng";
	}

	@PostMapping("/{id}/ongEditado")
	public String editarObra(@Valid @ModelAttribute("ongForm") Ong ong, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, Model model) {

		if (bindingResult.hasErrors()) {
			return "redirect:/admin/editOng/" + ong.getId();
		} else {
			ongSer.addOng(ong);
			if (ongSer.saveAndUpload(ong, file)) {
				return "redirect:/admin/ongs";
			} else {
				model.addAttribute("uploadError", "Error en la subida de fichero");

				return "redirect:/admin/ongs";
			}

		}

	}

	@GetMapping("/viewOng/{id}")
	public String viewById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("listVoluntarios", ongSer.findById(id).getVoluntarios());
		model.addAttribute("ong", ongSer.findById(id));
		System.out.println(ongSer.findById(id).getPresupuesto());
		return "admin/ongView";
	}

	@GetMapping("/topOngs")
	public String ListaTopOngs(Model model) {

		model.addAttribute("listop", ongSer.findTopOngs());
		model.addAttribute("searchForm", new SearchBean());
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/topOngs";
	}

	@GetMapping("/{continente}")
	public String viewById(@PathVariable("continente") String continente, Model model) {
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("continente", continente);
		model.addAttribute("ongsContinentes", ongSer.findByContinente(continente));

		return "admin/ongsContinente";
	}

}
