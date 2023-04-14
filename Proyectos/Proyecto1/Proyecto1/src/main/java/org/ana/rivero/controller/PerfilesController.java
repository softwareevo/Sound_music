package org.ana.rivero.controller;

import org.ana.rivero.service.IntServicePerfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/perfiles")
public class PerfilesController {

	@Autowired
	private IntServicePerfiles servicePerfiles;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("perfiles", servicePerfiles.obtnerPerfiles());
		return "perfiles/listaPerfiles";
	}
}
