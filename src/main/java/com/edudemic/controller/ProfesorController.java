package com.edudemic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.edudemic.entities.Curso;
import com.edudemic.entities.Estudiante;
import com.edudemic.entities.Profesor;
import com.edudemic.entities.Rol;
import com.edudemic.service.CursoService;
import com.edudemic.service.ProfesorService;
import com.edudemic.service.RolService;
import com.edudemic.service.UsuarioService;


@Controller
public class ProfesorController {
	private ProfesorService profesorService;
	private CursoService cursoService;
	private RolService rolService;
	private Profesor profesorSeleccionado;
	@Autowired 
	private UsuarioService usuarioService;
	private List<Curso> listaCursos =new ArrayList<>();
	public ProfesorController(ProfesorService profesorService,CursoService cursoService,RolService rolService) 
	{
		this.profesorService=profesorService;
		this.cursoService = cursoService;
		this.rolService = rolService;
		this.profesorSeleccionado=new Profesor();
	}
	
	@GetMapping("/registro/profesor")
	public String registrarProfesorForm(Model model) 
	{
		Profesor profesor = new Profesor();
		this.listaCursos = cursoService.listarCurso();

		model.addAttribute("profesor",profesor);
		model.addAttribute("listaCursos",listaCursos);
		model.addAttribute("roles", rolService.listarRoles());
		return "profesor/registroP";
	}
	@PostMapping("/profesores")
	public String registrarProfesor(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult result, Model model) 
	{
		/*if(result.hasErrors() || usuarioService.validar(profesor.getDni())) 
		{
			return "redirect:/registro/profesor";
		}*/
		

		if(profesorService.ValidarCamposVacios(profesor)==1||result.hasErrors()|| usuarioService.validar(profesor.getDni()))
		{
			if(profesorService.ValidarCamposVacios(profesor)==1)
				model.addAttribute("error", "Debe completar todos los campos");
				
				if(profesorService.ValidarContra(profesor)==1)
					model.addAttribute("error2", "Contraseña no es válida");
				
				if(profesorService.ValidarEdad(profesor)==1)
					model.addAttribute("error3", "Edad inválida");
				
				if(usuarioService.validar(profesor.getDni()))
				{
					model.addAttribute("error4", "El usuario ya existe");
				}
			
				this.listaCursos = cursoService.listarCurso();

				model.addAttribute("profesor",profesor);
				model.addAttribute("listaCursos",listaCursos);
				model.addAttribute("roles", rolService.listarRoles());
				return "profesor/registroP";
		}
		else 
		{
			List<Rol> nuevoList = rolService.listarStudent("ROLE_PROFESOR");
			profesor.setRol(nuevoList.get(0));
		profesorService.registrarProfesor(profesor);
		return "redirect:/lista/profesor";
		}
	}
	@GetMapping("/lista/profesor")
	public String listarEstudiante(Model model) {
		model.addAttribute("profesores",profesorService.listarProfesor());
		return "profesor/listaP";
	}
	
	@GetMapping("/profesor/edit/{id}")
	public String editProfesorForm( @PathVariable("id") Long id, Profesor profesor,Model model) {

		try {
			this.listaCursos = cursoService.listarCurso(); //quitar si no sale
			profesor = profesorService.findById(id);
			model.addAttribute("profesor", profesor);
			model.addAttribute("listaCursos",listaCursos);
			model.addAttribute("roles", rolService.listarRoles());
			profesorSeleccionado=profesor;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "profesor/editarP";
	}
	
	
	@PostMapping("/profesor/guardar")
	public String guardarProfesor(@Validated @ModelAttribute("profesor") Profesor profesor,BindingResult result,Model model,SessionStatus status) {
		{
		
		
		try {
		if (result.hasErrors()||profesorService.ValidarContra(profesor)==1
				||profesorService.ValidarEdad(profesor)==1|| profesorService.ValidarContra(profesor)==1
				||profesorService.ValidarCamposVacios(profesor)==1)
		{
			
			if(profesorService.ValidarEdad(profesor)==1)
				model.addAttribute("error3", "Edad inválida");
			if(profesorService.ValidarContra(profesor)==1)
				model.addAttribute("error2", "Contraseña no es válida");
			if(profesorService.ValidarCamposVacios(profesor)==1)
				model.addAttribute("error", "Debe completar todos los campos");
			if(profesorService.ValidarCamposVacios(profesor)==1)
				model.addAttribute("error", "Debe completar todos los campos");
		
			
			this.listaCursos = cursoService.listarCurso();
			model.addAttribute("listaCursos",listaCursos);
			

			return "profesor/editarP";
		}
		profesorService.editarProfesor(profesor);
		status.setComplete();
	} catch (Exception e) {
		// TODO: handle exception
	}
		this.listaCursos = cursoService.listarCurso();
		model.addAttribute("listaCursos",listaCursos);
		

		return "profesor/editarP";}
	
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/profesor/delete/{id}")
	public String deleteProfesor(@PathVariable Long id) {
		profesorService.deleteProfesorById(id);
		return "profesor/listaP";
	}
	
}