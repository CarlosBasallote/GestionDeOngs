package com.salesianostriana.proyectofinal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.proyectofinal.service.OngService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private HttpSession session;

	@Autowired
	private OngService ongSer;

	@GetMapping({ "/", "/index" })
	public String welcome(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("listaOngs", ongSer.findAllOngs());
		return "admin/index";
	}

	@GetMapping("/comentarios")
	public String comentario(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("listaOngs", ongSer.findAllOngs());

		return "admin/comentarios";
	}

}
