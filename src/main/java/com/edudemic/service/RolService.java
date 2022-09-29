package com.edudemic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edudemic.entities.Rol;
import com.edudemic.repository.RolRepository;


@Service
public class RolService {
	@Autowired
	private RolRepository rolRepository;
	public List<Rol> listarRoles(){
		return rolRepository.findAll();
	}
	public List<Rol> listarStudent(String n) 
	{
		return rolRepository.findByAuthority(n);
	}
}
