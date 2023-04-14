package org.ana.rivero.controller;

import java.util.List;

import org.ana.rivero.entity.Categoria;
import org.ana.rivero.service.IntServiceCategorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@RequestMapping("/categorias")
@Controller
public class CategoriasController {
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	/*@PostMapping("/guardar")
	public String guardarCategoria(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion) {
		Categoria cat = new Categoria();
		cat.setId(serviceCategorias.obtenerCategorias().size()+1);
		cat.setNombre(nombre);
		cat.setDescripcion(descripcion);
		serviceCategorias.guardar(cat);
		return "redirect:/categorias/index";
	}*/
	
	@PostMapping("/guardar")
	public String  guardar(Categoria categoria,RedirectAttributes model) {
		serviceCategorias.guardar(categoria);
		/*if(categoria.getId() == null) {
			categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
			
			model.addFlashAttribute("msg", "Categoria agregada con exito!");
		}else {
			
			int index=serviceCategorias.obtenerCategorias().size()-1;
			System.out.println("index = " + index);
			serviceCategorias.obtenerCategorias().set(index, categoria);
			model.addFlashAttribute("msg", "Categoria modificada con exito!");
			
			
			}
		
		*/
		
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/nueva")
	public String nuevaCategoria(Categoria categoria) {
		return"categorias/formCategoria";
	}
	
	@GetMapping("/eliminar")
	public String eliminarCategoria(@RequestParam("id") int idCategoria, RedirectAttributes model) {
		serviceCategorias.eliminar(idCategoria);
		model.addFlashAttribute("msg", "Categoria Eliminada");
		return "redirect:/categorias/index";
		
	}
	@GetMapping("/buscar")
	public String modificarCategoria(@RequestParam("id")int idCategoria,Model model) {
		Categoria categoria=serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
		
	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> categorias = serviceCategorias.obtenerCategorias();
		model.addAttribute("categorias", categorias);
		model.addAttribute("total", serviceCategorias.numeroCategorias());
		return"categorias/listaCategorias";
	}
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Categoria> lista = serviceCategorias.buscarTodas(page); 
	model.addAttribute("categorias", lista);
	model.addAttribute("total", serviceCategorias.numeroCategorias());
	return "categorias/listaCategorias";
	}
}
