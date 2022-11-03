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
	
	 public Profesor editarProfesor(Profesor e) 
	    {
	        return profesorRepository.save(e);
	    }
	
	 public int ValidarCamposVacios(Profesor profesor)
	    {
	    	int validar=0;
	    	
	    	if(profesor.getNombres()=="" || profesor.getApellidos()=="" || 
	    			profesor.getDni()==""|| profesor.getPassword()=="")
	    		validar=1;

	    	return validar;
	    }
	 
	 
	 public int ValidarNombres(Profesor profesor)
	    {
	    	int validar=0;
	    	int auxiliar=0;
	    	int auxiliar2=0;
	    	int auxiliar3=0;
	    	int auxiliar4=0;
	    	
	    	for(int i=0; i<profesor.getNombres().length();i++)
	    	{
	    		for(int j=0;j<10;j++)
	    		{ if( profesor.getNombres().charAt(i)>=(char)48 && profesor.getNombres().charAt(i)<=(char)57)
	    			{
	    			auxiliar=0;
	    			
	    			}
	    		  else
	    		  {
	    			  auxiliar=1;
	    			  break;
	    		  }
	    		}
	    		
	    		
	    	}
	    	for(int m=0; m<profesor.getNombres().length();m++)
	    	{
	    		
	    		if(((int)profesor.getNombres().charAt(m)>=65&&(int)profesor.getNombres().charAt(m)<=90)
	    				|| ((int)profesor.getNombres().charAt(m)>=97||(int)profesor.getNombres().charAt(m)<=122))
	    			{
	    			auxiliar2=0;
	    			}
	    		else {
	    			auxiliar2=1;
	    			break;
	    		}
	    	}
	    	
	    	
	    	
	    	if(profesor.getNombres().length()>40 )
	    	{
	    		validar=1;
	    		
	    	}
	    
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
	    
	    
	    public void deleteProfesorById(Long id) {
			profesorRepository.deleteById(id);
		}
}