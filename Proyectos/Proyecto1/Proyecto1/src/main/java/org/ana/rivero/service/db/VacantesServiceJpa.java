package org.ana.rivero.service.db;

import java.util.List;
import java.util.Optional;

import org.ana.rivero.entity.Vacante;
import org.ana.rivero.repository.VacantesRepository;
import org.ana.rivero.service.IntServiceVacantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VacantesServiceJpa implements IntServiceVacantes {
	@Autowired
	private VacantesRepository repoVacantes;

	@Override
	public List<Vacante> obtenerVacantes() {
		// TODO Auto-generated method stub
		return repoVacantes.findAll();
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		repoVacantes.save(vacante);
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		repoVacantes.deleteById(idVacante);
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		// TODO Auto-generated method stub
		Optional<Vacante> optional=repoVacantes.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalVacantes() {
		// TODO Auto-generated method stub
		return repoVacantes.numeroElementos();
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoVacantes.findAll(page);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return repoVacantes.findByEstatusAndDestacadoOrderByIdDesc("Aprobada", 1);
	}

	@Override
	public List<Vacante> buscarTodasCategoria() {
		// TODO Auto-generated method stub
		return repoVacantes.buscarPorTodosYPorCategoria();
	}

	

}
