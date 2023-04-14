package org.ana.rivero.service;
import java.util.List;

import org.ana.rivero.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceCategorias {
	
	public List<Categoria> obtenerCategorias();
	public void guardar(Categoria categoria);
	public Categoria buscarPorId(Integer idCategoria);
	public void eliminar(Integer idCategoria);
	public int numeroCategorias();
	Page<Categoria>buscarTodas(Pageable page);
		
}