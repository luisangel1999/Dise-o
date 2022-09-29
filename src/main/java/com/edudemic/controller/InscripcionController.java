package com.edudemic.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.sql.Insert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.pattern.Converter;

import java.util.ArrayList;
import java.util.List;

import com.edudemic.entities.Estudiante;
import com.edudemic.entities.Inscripcion;
import com.edudemic.entities.Mentoria;
import com.edudemic.service.EstudianteService;
import com.edudemic.service.InscripcionService;
import com.edudemic.service.MentoriaService;

@Controller
public class InscripcionController 
{
	//editado x joao
	private InscripcionService inscripcionService;
	private EstudianteService estudianteService;
	private List<Estudiante> listaEstudiantes = new ArrayList<>();
	private MentoriaService mentoriaService;
	private List<Mentoria> listaMentorias=new ArrayList<>();
	public Long uxment;
	
	public InscripcionController(InscripcionService inscripcionService, EstudianteService estudianteService, MentoriaService mentoriaService) 
	{
		this.inscripcionService=inscripcionService;
		this.estudianteService=estudianteService;
		this.mentoriaService=mentoriaService;
	}
	
	
	// WHERE: nabvarAdmin.html(58)
	// se usa?
	@GetMapping("/lista/inscripcion")
	public String listarInscripciones(Model model) 
	{
		model.addAttribute("inscripciones",inscripcionService.listarInscripcion());
		return "inscripcion/listaI";
	}
	
	
	// WHERE: ??
	@GetMapping("/lista/inscripcion/estudiante/{id}")
	public String listarNotas(@PathVariable Long id,Model model) 
	{
		model.addAttribute("inscripcionE",inscripcionService.listaIns(id));
		return "estudiante/notasE";
	}
	
	
	// WHERE: listaH.html(45)
	@GetMapping("/registro/inscripciones/{id}")
	public String registrarInscripcionesForm(@PathVariable Long id,Model model, RedirectAttributes attribute) 
	{
		model.addAttribute("mentoria", mentoriaService.getMentoriaById(id));
		model.addAttribute("inscripciones",new Inscripcion());
		model.addAttribute("listaEstudiantes",estudianteService.listaEstudiantes());
		model.addAttribute("listaMentorias",mentoriaService.listarMentoria());
		
		System.out.println("Error: El id del cliente no existe");
		attribute.addFlashAttribute("error", "Error: El id del cliente no existe");
		
		return "inscripcion/registroI";
	}
	
	
	// WHERE: registroI.html
	@PostMapping("/inscripciones/{idEstudiante}")
	public String registrarInscripcion(@PathVariable Long idEstudiante, @RequestParam(value="idMentoria") Long idMentoria, @ModelAttribute("mentoria") Mentoria mentoria,@ModelAttribute("inscripcion") Inscripcion inscripcion, RedirectAttributes attribute, Model model) 
	{
		inscripcion.setEstudiante(estudianteService.buscarPorId(idEstudiante));
		inscripcion.setMentoria(mentoriaService.getMentoriaById(idMentoria));
		if(inscripcionService.validarInscripción(inscripcion)==0)
		{
			inscripcionService.registrarInscripcion(inscripcion);
			model.addAttribute("inscripcionE",inscripcionService.listaIns(idEstudiante));
			return "inscripcion/listaIE";
		}
		else
		{
			model.addAttribute("mensaje", "En esta mentoria ya se ha inscrito 1 estudiante");
			return "inscripcion/registroI";
		}
	}


	// WHERE: navbar.html(68)
	@GetMapping("/lista/inscripcion/estudiante2/{id}")
    public String listar(@PathVariable Long id,Model model) 
	{
        model.addAttribute("inscripcionE",inscripcionService.listaIns(id));
        return "inscripcion/listaIE";
    }
}
