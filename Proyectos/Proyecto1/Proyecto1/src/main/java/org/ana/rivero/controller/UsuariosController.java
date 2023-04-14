package org.ana.rivero.controller;

import org.ana.rivero.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("usuarios", serviceUsuarios.obtnerUsuarios());
		System.out.println(serviceUsuarios.obtnerUsuarios());
		return "usuarios/listaUsuarios";
	}

}
