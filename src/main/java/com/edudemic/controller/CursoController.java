package com.edudemic.controller;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edudemic.entities.Curso;
import com.edudemic.service.CursoService;

@Controller
public class CursoController {
	private CursoService cursoService;
	public CursoController(CursoService cursoService) 
	{
		this.cursoService=cursoService;
	}

	@GetMapping("/registro/curso")
	public String registrarForm(Model model) 
	{
		Curso curso = new Curso();
		model.addAttribute("curso",curso);
		return "curso/registroC";
	}
	@PostMapping("/cursos/guardar/registrar")
	public String registrarCurso (@Validated @ModelAttribute Curso curso, BindingResult result, Model model, SessionStatus status)
	{
		
		try {
			if (result.hasErrors()) {

				return "curso/registroC";
			}
			cursoService.registrarCurso(curso);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/lista/curso";
	
	}
	
	@PostMapping("/cursos/guardar/modificar")
	public String modificarCurso (@Validated @ModelAttribute Curso curso, BindingResult result, Model model, SessionStatus status)
	{
		
		try {
			if (result.hasErrors()) {

				return "curso/modificarC";
			}
			cursoService.registrarCurso(curso);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "curso/modificarC";
	
	}
	
	@GetMapping("/lista/curso")
	public String listarEstudiante(Model model) {
		model.addAttribute("cursos",cursoService.listarCurso());
		return "curso/listaC";
	}
	
	@GetMapping("/cursos/delete/{id}")
	public String deleteCurso(@PathVariable Long id) {
		cursoService.deleteCursoById(id);
		return "redirect:/lista/curso";
	}
	
	@RequestMapping("/cursos/edit/{id}")
	public String editCursoForm(@PathVariable Long id,Model model, RedirectAttributes redirect) {

		Optional<Curso> curso = cursoService.buscarPorId(id);
		if (curso == null) 
		{
			redirect.addFlashAttribute("mensaje", "Ocurrio un error");
            return "curso/modificarC";
        } 
		else 
        {
            model.addAttribute("curso", curso);
            return "curso/modificarC";
        }

	}
}
