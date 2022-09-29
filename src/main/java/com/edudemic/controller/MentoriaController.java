package com.edudemic.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.edudemic.entities.Profesor;
import com.edudemic.entities.Mentoria;
import com.edudemic.service.ProfesorService;
import com.edudemic.service.MentoriaService;

@Controller
public class MentoriaController 
{
	//editado x joao
	private MentoriaService mentoriaService;
	private ProfesorService profesorService;
	private List<Profesor> listaProfesores =new ArrayList<>();
	
	
	public MentoriaController(MentoriaService mentoriaService,ProfesorService profesorService) 
	{
		this.mentoriaService=mentoriaService;
		this.profesorService = profesorService;
	}
	
	
	// WHERE: navbar.html(95) & navbarAdmin.html(54)
	@GetMapping("/registro/mentoria")
	public String registrarMentoriaForm(Model model) 
	{
		model.addAttribute("mentoria",new Mentoria());
		return "mentoria/registroM";
	}
	
	
	// WHERE: registro.html(11)
	@PostMapping("/guardar/mentorias")
	public String registrarMentoria(@RequestParam(value="id") Long id, @Valid Mentoria mentoria, BindingResult result, Model model, SessionStatus status) throws Exception
	{
		if (result.hasErrors()) 
		{
			return "mentoria/registroM";
		}
		else
		{
			mentoria.setProfesor(profesorService.findById(id));
			mentoriaService.registrarMentoria(mentoria);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("mentoria",new Mentoria());
		return "redirect:/listar/mentoria?id="+id;
	}
	
	
	// WHERE: navbar.html(97)
	@GetMapping("/listar/mentoria")
	public String listaMentoria(@RequestParam(value="id") Long id,Model model) 
	{
		try
		{
			model.addAttribute("mentoria",new Mentoria());
			model.addAttribute("mentorias",mentoriaService.listaMentoriaxProfesor(id));
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
		}
		return "mentoria/listaM";
	}
	
	
	// WHERE: listaM.html(32)
	@RequestMapping("/borrar/mentoria/{idProfesor}")
	public String borrarMentoria(Model model, @RequestParam(value="idMentoria") Long idMentoria, @PathVariable Long idProfesor)
	{
		mentoriaService.borrarMentoria(idMentoria);
		model.addAttribute("mentorias",mentoriaService.listaMentoriaxProfesor(idProfesor));
		return "mentoria/listaM";
	}
	
	
	// WHERE: listaM.html(33)
	@RequestMapping("/modificar/mentoria")
	public String modificarMentoria(@RequestParam(value="id") Long id,Model model)
	{
		model.addAttribute("mentoria",mentoriaService.getMentoriaById(id));
		return "mentoria/modificaM";
	}


	// WHERE: ??
	@GetMapping("/listar/mentoria2")
	public String listaMentoria2(Model model) 
	{
		model.addAttribute("mentorias",mentoriaService.listarMentoria());
		return "mentoria/listaME";
	}


	// WHERE: ??
	@GetMapping("/mentoria/estudiante/{id}")
	public String objetoMentoria(@PathVariable Long id,Model model) 
	{
		model.addAttribute("mentoriaE",mentoriaService.mentoriaObjeto(id));
		return "estudiante/mentoriaE";
	}
	
	
	// WHERE: navbar.html(23)
	@GetMapping("/listar/mentoria/estudiante")
	public String listaHorario(Model model) 
	{
		Mentoria mentoria = new Mentoria();
		model.addAttribute("mentoria", mentoria);
		model.addAttribute("mentorias", mentoriaService.mentoriasInscripciones());
		return "mentoria/listaH";
	}
}
