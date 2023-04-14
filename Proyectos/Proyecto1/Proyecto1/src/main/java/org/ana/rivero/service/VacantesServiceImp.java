package org.ana.rivero.service;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.time.DateTimeException;

import org.ana.rivero.entity.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class VacantesServiceImp implements IntServiceVacantes {
	
	
	private List<Vacante> vacantes;
	
	public VacantesServiceImp(){
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			
		vacantes = new LinkedList<>();
		Vacante v1=new Vacante();
		v1.setId(1);
		v1.setNombre("Programador web");
		v1.setDescripcion("Desarrollo de apps");
		v1.setFecha(LocalDate.parse("06/02/2023", formato));
		v1.setSalario(12000.0);
		v1.setEstatus("Aprobada");
		v1.setDestacado(1);
		v1.setDetalles("<h2>Requisitos</h2><ul><li>Amblia Experiencia</li><li>Ingles</li></ul>");
		v1.setImagen("logo10.png");
		
		
		Vacante v2=new Vacante();
		v2.setId(2);
		v2.setNombre("Analista ");
		v2.setDescripcion("Analisis de sistemas");
		v2.setFecha(LocalDate.parse("06/02/2023", formato));
		v2.setSalario(6000.0);
		v2.setEstatus("Aprobada");
		v2.setDestacado(1);
		v2.setDetalles("<h2>Requisitos</h2><ul><li>Amblia Experiencia</li><li>Ingles</li></ul>");
		v2.setImagen("logo12.png");
		
		vacantes.add(v1);
		vacantes.add(v2);
		}catch(DateTimeException ex){
			System.out.println(ex.getMessage());
			
		}
	}

	@Override
	public List<Vacante> obtenerVacantes() {
		return vacantes;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantes.add(vacante);

	}

	@Override
	public void eliminar(Integer idVacante) {
		vacantes.remove(buscarPorId(idVacante));

	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for(Vacante v : vacantes) {
			if(v.getId() == idVacante) {
				return v;
			}
		}
		return null;
	}

	@Override
	public long totalVacantes() {
		// TODO Auto-generated method stub
		return vacantes.size();
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacante> buscarTodasCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

}
