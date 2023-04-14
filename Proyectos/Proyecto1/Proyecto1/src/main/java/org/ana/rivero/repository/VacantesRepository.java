package org.ana.rivero.repository;

import java.util.List;

import org.ana.rivero.entity.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	List<Vacante> findByEstatusAndDestacadoOrderByIdDesc(String estatus, int destacado);
	
	/*Consultas utilizando el sql nativo*/
	@Query(value = "select * from vacantes where idCategoria = 2",nativeQuery = true)
	List<Vacante> buscarPorTodosYPorCategoria();
	
	@Query(value="select count(*) from vacantes", nativeQuery = true)
	long numeroElementos();
}
