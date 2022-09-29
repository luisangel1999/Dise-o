package com.edudemic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edudemic.entities.Estudiante;
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
}
