package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Resultadocuestionario;

public interface IResultadocuestionarioDAO extends JpaRepository<Resultadocuestionario, Integer>{
	/*@Query(value = "select * from farmacia f where f.id_medicamento =  (select m.id_medicamento from medicamento m where lower (m.nombre_medicamento) = lower(:nombre))", nativeQuery = true)
	List<Farmacia> getFarmaciaMedicamento(@Param("nombre") String nombre);*/
	@Query(value="from Resultadocuestionario r")
	List<Resultadocuestionario> getResultados();
}
