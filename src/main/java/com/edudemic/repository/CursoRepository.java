package com.edudemic.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.edudemic.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}
	