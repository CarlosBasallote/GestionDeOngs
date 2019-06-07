package com.salesianostriana.proyectofinal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.proyectofinal.formbean.LoginUser;
import com.salesianostriana.proyectofinal.model.Voluntario;
import com.salesianostriana.proyectofinal.service.VoluntarioService;

@Controller
public class LoginController {

	@Autowired
	private VoluntarioService volSer;

	@Autowired
	private HttpSession session;

	@GetMapping({ "/", "/login" })
	public String showLogin(Model model) {
		model.addAttribute("loginUser", new LoginUser());
		return "login";

	}

	@PostMapping("/checkLogin")
	public String doLogin(@ModelAttribute("loginUser") LoginUser loginUser, BindingResult bindingResult, Model model) {

		Voluntario user = volSer.login(loginUser.getMail(), loginUser.getPassword());

		if (user != null && user.isAdmin()) {

			session.setAttribute("usuarioActual", user);
			return "redirect:/admin/index";
		} else if (user != null) {
			session.setAttribute("usuarioActual", user);
			return "redirect:/app/index";
		} else {
			model.addAttribute("loginError", "El usuario o contraseña no es válido");
			return "login";
		}

	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		session.setAttribute("usuarioActual", null);
		return "redirect:/";
	}

}
