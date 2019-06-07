package com.salesianostriana.proyectofinal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.proyectofinal.formbean.SearchBean;
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.service.OngService;
import com.salesianostriana.proyectofinal.service.VoluntarioService;

@Controller
@RequestMapping("/app")
public class MainController {

	@Autowired
	private HttpSession session;

	@Autowired
	VoluntarioService volSer;

	@Autowired
	private OngService ongSer;

	@GetMapping({ "/", "/index" })
	public String welcome(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		return "app/index";
	}

	@GetMapping("/viewOng/{id}")
	public String viewById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("listVoluntarios", ongSer.findById(id).getVoluntarios());
		model.addAttribute("ong", ongSer.findById(id));

		return "app/ongView";
	}

	@GetMapping("/topOngs")
	public String ListaTopOngs(Model model) {

		model.addAttribute("listop", ongSer.findTopOngs());
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "app/topOngs";
	}

	@GetMapping("/voluntarioDona/{id}")
	public String voluntarioDona(@PathVariable("id") Long id, Model model) {
		double donacion = 100;
		ongSer.dona(ongSer.findById(volSer.findById(id).getOng().getId()), donacion);
		volSer.donado(volSer.findById(id), donacion);
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("usuario", volSer.findById(id));
		model.addAttribute("admin", volSer.findById(id).isAdmin());

		return "redirect:/app/index";
	}

	@GetMapping("/voluntarioDona2/{id}")
	public String voluntarioDona2(@PathVariable("id") Long id, Model model) {
		double donacion = 200;
		ongSer.dona(ongSer.findById(volSer.findById(id).getOng().getId()), donacion);
		volSer.donado(volSer.findById(id), donacion);
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("usuario", volSer.findById(id));
		model.addAttribute("admin", volSer.findById(id).isAdmin());

		return "redirect:/app/index";
	}

	@GetMapping("/voluntarioDona3/{id}")
	public String voluntarioDona3(@PathVariable("id") Long id, Model model) {
		double donacion = 50;
		ongSer.dona(ongSer.findById(volSer.findById(id).getOng().getId()), donacion);
		volSer.donado(volSer.findById(id), donacion);
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("usuario", volSer.findById(id));
		model.addAttribute("admin", volSer.findById(id).isAdmin());

		return "redirect:/app/index";
	}

	@GetMapping("/voluntarioDona4/{id}")
	public String voluntarioDona4(@PathVariable("id") Long id, Model model) {
		double donacion = 25;
		ongSer.dona(ongSer.findById(volSer.findById(id).getOng().getId()), donacion);
		volSer.donado(volSer.findById(id), donacion);
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		model.addAttribute("usuario", volSer.findById(id));
		model.addAttribute("admin", volSer.findById(id).isAdmin());

		return "redirect:/app/index";
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

		return "app/topVoluntarios";
	}

	@GetMapping("/comentarios")
	public String comentario(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "app/comentarios";
	}

}
