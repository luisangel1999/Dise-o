package com.edudemic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edudemic.entities.Inscripcion;


public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> 
{
	//editado x joao
	@Query("SELECT i FROM Inscripcion i WHERE i.estudiante.id=?1")
	List<Inscripcion> listaIns(Long id);

}
