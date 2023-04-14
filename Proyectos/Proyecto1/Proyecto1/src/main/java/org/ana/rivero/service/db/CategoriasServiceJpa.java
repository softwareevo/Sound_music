package org.ana.rivero.service.db;

import java.util.List;
import java.util.Optional;

import org.ana.rivero.entity.Categoria;
import org.ana.rivero.repository.CategoriasRepository;
import org.ana.rivero.service.IntServiceCategorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary

public class CategoriasServiceJpa implements IntServiceCategorias {
	
	@Autowired      
	private CategoriasRepository repoCategorias;

	@Override
	public List<Categoria> obtenerCategorias() {
		return repoCategorias.findAll();
	}

	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);

	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if(optional.isPresent()) {
		return optional.get();
	}
	return null;
		
	}
	
	

	@Override
	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);

	}

	@Override
	public int numeroCategorias() {
		return (int) repoCategorias.count();
		}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoCategorias.findAll(page);
	}

}
