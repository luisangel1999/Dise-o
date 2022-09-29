package com.edudemic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edudemic.entities.Estudiante;
import com.edudemic.repository.EstudianteRepository;

@Service
public class EstudianteService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private EstudianteRepository estudianteRepository;
	public EstudianteService(EstudianteRepository estudianteRepository) 
	{
		this.estudianteRepository=estudianteRepository;
	}
	public List<Estudiante> listaEstudiantes()
	{
		return estudianteRepository.findAll();
	}
	public Estudiante registrarEstudiante(Estudiante e) 
	{
		e.setPassword(passwordEncoder.encode(e.getPassword()));

		return estudianteRepository.save(e);
	}
	public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id).get();
    }
    public Estudiante editarEstudiante(Estudiante e) 
    {
        return estudianteRepository.save(e);
    }
    
    public int ValidarCamposVacios(Estudiante estudiante)
    {
    	int validar=0;
    	
    	if(estudiante.getNombres()=="" || estudiante.getApellidos()=="" || 
    			estudiante.getDni()==""|| estudiante.getPassword()=="" || estudiante.getGrado()=="")
    		validar=1;

    	return validar;
    }
    
    public int ValidarNombres(Estudiante estudiante)
    {
    	int validar=0;
    	int auxiliar=0;
    	int auxiliar2=0;
    	int auxiliar3=0;
    	int auxiliar4=0;
    	
    	for(int i=0; i<estudiante.getNombres().length();i++)
    	{
    		for(int j=0;j<10;j++)
    		{ if( estudiante.getNombres().charAt(i)>=(char)48 && estudiante.getNombres().charAt(i)<=(char)57)
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
    	for(int m=0; m<estudiante.getNombres().length();m++)
    	{
    		
    		if(((int)estudiante.getNombres().charAt(m)>=65&&(int)estudiante.getNombres().charAt(m)<=90)
    				|| ((int)estudiante.getNombres().charAt(m)>=97||(int)estudiante.getNombres().charAt(m)<=122))
    			{
    			auxiliar2=0;
    			}
    		else {
    			auxiliar2=1;
    			break;
    		}
    	}
    	
    	
    	
    	if(estudiante.getNombres().length()>40 )
    	{
    		validar=1;
    		
    	}
    
    	return validar;
    }
    
    
    public int ValidarEdad(Estudiante estudiante)
    {
    	int validar=0;
    	
    	if(estudiante.getEdad()<11||estudiante.getEdad()>22)
    		validar=1;
    	
    	return validar;
    }
    public int ValidarContra(Estudiante estudiante)
	{
		int validar=0;
		
		if(estudiante.getPassword().length()!=6)
			validar=1;
		
		
		return validar;
	}
    
}
