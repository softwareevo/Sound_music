package org.ana.rivero.service;

import java.util.LinkedList;
import java.util.List;

import org.ana.rivero.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriasServiceImp implements IntServiceCategorias {
	
	private List<Categoria> categorias = null;
	
	public CategoriasServiceImp() {
		categorias = new LinkedList<>();
		
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setNombre("Ingenieros");
		c1.setDescripcion("Relacionado con las diferentes areas de Ingeneria");
		
		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setNombre("Programadores");
		c2.setDescripcion("Relacionado con desarrollo web");
		
		Categoria c3 = new Categoria();
		c3.setId(3);
		c3.setNombre("Contadores");
		c3.setDescripcion("Relacionado con nomina y auditorias");
		
		categorias.add(c1);
		categorias.add(c2);
		categorias.add(c3);
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		return categorias;
	}

	@Override
	public void guardar(Categoria categoria) {
		categorias.add(categoria);

	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria cat : categorias) {
			if(cat.getId().compareTo(idCategoria) == 0) {
				return cat;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categorias.remove(buscarPorId(idCategoria));

	}

	
	@Override
	public int numeroCategorias(){
		return categorias.size();
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return null;
	}
}