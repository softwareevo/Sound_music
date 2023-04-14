package org.ana.rivero.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.ana.rivero.entity.Vacante;
import org.ana.rivero.service.IntServiceCategorias;
import org.ana.rivero.service.IntServiceVacantes;
import org.ana.rivero.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/vacantes")

public class VacantesController {
	@Value("${proyecto1.ruta.imagenes}")
	private String ruta;
	@Autowired
	private IntServiceVacantes serviceVacantes;
	
	@GetMapping("/buscar")
	public String buscar (@RequestParam("id") int idVacante,Model model) {
		Vacante vacante= serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
		
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idVacante, RedirectAttributes model ){
		serviceVacantes.eliminar(idVacante);
		model.addFlashAttribute("msg","Vacante eliminada");
		return "redirect:/vacantes/indexPaginado";
		
	}
	
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idVacante, Model model) {
		Vacante vacante=serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("vacante",vacante);
		return "vacantes/detalle";
	}
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	@GetMapping("/nueva")
	public String nuevaVacante(Vacante vacante,Model model) {
		model.addAttribute("categorias",serviceCategorias.obtenerCategorias());
		return"vacantes/formVacante";
	}
	
	
	
	@PostMapping("/guardar")
	public String  guardar(Vacante vacante,BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(vacante);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("categorias",serviceCategorias.obtenerCategorias());
			return"vacantes/formVacante";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			vacante.setImagen(nombreImagen); 
			}

		}
		if(vacante.getId()==null)
			model.addFlashAttribute("msg","Vacante agregada");
		else model.addFlashAttribute("msg","Vacante modificada");
		serviceVacantes.guardar(vacante);
		return "redirect:/vacantes/indexPaginado";
		
		
	}
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("vacantes", serviceVacantes.obtenerVacantes());
		return"vacantes/listaVacantes";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante> lista = serviceVacantes.buscarTodas(page); 
	model.addAttribute("vacantes", lista);
	model.addAttribute("total", serviceVacantes.totalVacantes());
	return "vacantes/listaVacantes";
	}
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }  
    
      });
  }
}


