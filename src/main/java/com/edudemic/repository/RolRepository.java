package com.edudemic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edudemic.entities.Rol;


public interface RolRepository extends JpaRepository<Rol, Long>{

	List<Rol> findByAuthority(String authority);
}
