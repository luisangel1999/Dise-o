package com.edudemic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edudemic.entities.Estudiante;
import com.edudemic.entities.Profesor;
import com.edudemic.repository.ProfesorRepository;

@Service
public class ProfesorService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private ProfesorRepository profesorRepository;
	public ProfesorService(ProfesorRepository profesorRepository) 
	{
		this.profesorRepository=profesorRepository;
		}
	public Profesor registrarProfesor(Profesor p) 
	{
		p.setPassword(passwordEncoder.encode(p.getPassword()));

		return profesorRepository.save(p);
	}
	public List<Profesor> listarProfesor()
	{
		return profesorRepository.findAll();
	}
	
	//lo agregue, no borres chicho
	public Profesor findById(Long id)
	{
		return profesorRepository.findById(id).get();
	}
	
	 public int ValidarCamposVacios(Profesor profesor)
	    {
	    	int validar=0;
	    	
	    	if(profesor.getNombres()=="" || profesor.getApellidos()=="" || 
	    			profesor.getDni()==""|| profesor.getPassword()=="")
	    		validar=1;

	    	return validar;
	    }
	 
	 public int ValidarEdad(Profesor profesor)
	    {
	    	int validar=0;
	    	
	    	if(profesor.getEdad()<25||profesor.getEdad()>60)
	    		validar=1;
	    	
	    	return validar;
	    }
	    public int ValidarContra(Profesor profesor)
		{
			int validar=0;
			
			if(profesor.getPassword().length()!=6)
				validar=1;
			
			
			return validar;
		}
}
