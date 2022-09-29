package com.edudemic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edudemic.entities.Curso;
import com.edudemic.repository.CursoRepository;
@Service
public class CursoService {
	private CursoRepository cursoRepository;
	public CursoService(CursoRepository cursoRepository) 
	{
		this.cursoRepository=cursoRepository;
	}
	public List<Curso> listarCurso() 
	{
		return cursoRepository.findAll();
	}
	public Curso registrarCurso(Curso c) 
	{
		return cursoRepository.save(c);
	}
	public Optional <Curso> buscarPorId(Long id) 
	{
		return cursoRepository.findById(id);
	}
	
	public void deleteCursoById(Long id) {
		cursoRepository.deleteById(id);
	}
	
	public Curso modificarCurso(Curso curso) 
    {
        return cursoRepository.save(curso);
    }
	
}