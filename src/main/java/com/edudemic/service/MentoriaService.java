package com.edudemic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edudemic.entities.Mentoria;

import com.edudemic.repository.MentoriaRepository;


@Service
public class MentoriaService 
{
	//editado x joao
	private MentoriaRepository mentoriaRepository;
	private InscripcionService inscripcionService;
	
	public MentoriaService(MentoriaRepository mentoriaRepository,InscripcionService inscripcionService) 
	{
		this.mentoriaRepository=mentoriaRepository;
		this.inscripcionService=inscripcionService;
	}
	
	public Mentoria registrarMentoria(Mentoria m) 
	{
		return mentoriaRepository.save(m);
	}
	
	public List<Mentoria> listarMentoria() 
	{
		return mentoriaRepository.findAll();
	}
	
	public void borrarMentoria(Long id)
	{
		for(int i=0; i<inscripcionService.listarInscripcion().size();i++)
		{
			if(inscripcionService.listarInscripcion().get(i).getMentoria().getId()==id)
			{
				inscripcionService.borrarInscripcion(inscripcionService.listarInscripcion().get(i).getId());
			}
		}
		mentoriaRepository.deleteById(id);
	}
	
	public Mentoria mentoriaObjeto(Long id) 
	{
		return mentoriaRepository.objetoM(id);
	}
	
	public Mentoria getMentoriaById(Long id) 
	{
		return mentoriaRepository.findById(id).get();
	}
	
	public List<Mentoria> listaMentoriaxProfesor(Long id)
	{
		return mentoriaRepository.listaMentoriasxProfesor(id);
	}
	
	//SHOW EVERY MENTORIA WHICH DOES NOT BELONG TO THE INSCRIPCIONES TABLE 
	public List<Mentoria> mentoriasInscripciones() 
	{
		List<Mentoria> listaM = new ArrayList<>();
		List<Mentoria> listaO = mentoriaRepository.findAll();
		for(int j=0;j<inscripcionService.listarInscripcion().size();j++) 
		{
			
			if(inscripcionService.listarInscripcion().get(j).getMentoria().getId()!=mentoriaRepository.findAll().get(j).getId()) 
			{
				
			}else 
			{
				listaM.add(mentoriaRepository.findAll().get(j));
			}
			
		}
		listaO.removeAll(listaM);
		return listaO;
	}
}
