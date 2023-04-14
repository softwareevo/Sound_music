package org.ana.rivero.service;

import java.util.List;

import org.ana.rivero.entity.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceVacantes {
	public List<Vacante> obtenerVacantes();
	public void guardar(Vacante vacante);
	public void eliminar(Integer idVacante);
	public Vacante buscarPorId(Integer idVacante);
	public long totalVacantes();
	public Page<Vacante>buscarTodas(Pageable page);
	public List<Vacante> buscarDestacadas();
	public List<Vacante> buscarTodasCategoria();

}
