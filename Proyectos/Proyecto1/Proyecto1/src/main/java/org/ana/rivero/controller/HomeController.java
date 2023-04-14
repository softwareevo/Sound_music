package org.ana.rivero.controller;

import java.time.LocalDate;
import java.util.List;

import org.ana.rivero.entity.Perfil;
import org.ana.rivero.entity.Usuario;
import org.ana.rivero.entity.Vacante;
import org.ana.rivero.service.IntServiceUsuarios;
import org.ana.rivero.service.IntServiceVacantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceVacantes serviceVacantes;
	
	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/acerca")																																
	public String acerca() {
		return "acerca";
	}
	
	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setFechaRegistro(LocalDate.now());
		usuario.setEstatus(1);
		String texto = usuario.getPassword();
		String encriptado = passwordEncoder.encode(texto);
		usuario.setPassword(encriptado);
		//usuario.setPassword("{noop}" + usuario.getPassword());
		Perfil per = new Perfil();
		per.setId(3);
		usuario.agregar(per);
		serviceUsuarios.agregar(usuario);
		System.out.println(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "formRegistro";
	}
	
	@GetMapping("/login")
	public String login() {
		return "formLogin";	
	}
	
	@GetMapping("/logout")
	public String logout (HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}

	@GetMapping(value="/")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarDestacadas();
		model.addAttribute("vacantes", lista);
		return "home";
	}
	
	@PostMapping("/buscar")
	public String buscar(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodasCategoria();
		model.addAttribute("vacantes", lista);
		return "home";
	}

}
