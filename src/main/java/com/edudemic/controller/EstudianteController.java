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

import com.edudemic.entities.Estudiante;
import com.edudemic.entities.Rol;
import com.edudemic.service.EstudianteService;
import com.edudemic.service.RolService;
import com.edudemic.service.UsuarioService;

@Controller
public class EstudianteController {
	private EstudianteService estudianteService;
	private RolService rolService;
	private Estudiante estudianteSeleccionado;
	@Autowired 
	private UsuarioService usuarioService;
	public EstudianteController(EstudianteService estudianteService,RolService rolService) 
	{
		this.estudianteService = estudianteService;
		this.rolService=rolService;
		this.estudianteSeleccionado=new Estudiante();
	}
	@GetMapping("/lista")
	public String lista(){
		return "estudiante/listaE";
	}
	@GetMapping("/registro/estudiante")
	public String registrarEstudianteForm(Model model) 
	{
		List<String>listagrado=new ArrayList<String>();
		listagrado.add("1");
		listagrado.add("2");
		listagrado.add("3");
		listagrado.add("4");
		listagrado.add("5");
		
		
		Estudiante estudiante = new Estudiante();
		model.addAttribute("estudiante",estudiante);
		model.addAttribute("roles", rolService.listarRoles());
		model.addAttribute("listagrado", listagrado);

		return "estudiante/registroE";
	}
	@PostMapping("/estudiantes")
	public String registrarEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante, BindingResult result, Model model) 
	{
			
		
		if(estudianteService.ValidarCamposVacios(estudiante)==1||result.hasErrors()||usuarioService.validar(estudiante.getDni()))
	     {
			if(estudianteService.ValidarCamposVacios(estudiante)==1)
			model.addAttribute("error", "Debe completar todos los campos");
			
			if(estudianteService.ValidarContra(estudiante)==1)
				model.addAttribute("error2", "Contraseña no es válida");
			
			if(estudianteService.ValidarEdad(estudiante)==1)
				model.addAttribute("error3", "Edad inválida");
			
			if(usuarioService.validar(estudiante.getDni()))
			{
				model.addAttribute("error4", "El usuario ya existe");
			}
			
			
			List<String>listagrado=new ArrayList<String>();
			listagrado.add("1");
			listagrado.add("2");
			listagrado.add("3");
			listagrado.add("4");
			listagrado.add("5");
			//Estudiante estudiante2 = new Estudiante();
			model.addAttribute("estudiante",estudiante);
			model.addAttribute("roles", rolService.listarRoles());
			model.addAttribute("listagrado", listagrado);
			
			return "estudiante/registroE";
		}
		
		
		
	 else 
		{
			List<Rol> nuevoList = rolService.listarStudent("ROLE_STUDENT");
			estudiante.setRol(nuevoList.get(0));
			estudianteService.registrarEstudiante(estudiante);
			return "redirect:/lista/estudiante";
	 	}
	}
	@GetMapping("/lista/estudiante")
	public String listarEstudiante(Model model) {
		model.addAttribute("estudiantes",estudianteService.listaEstudiantes());
		return "estudiante/listaE";
	}
	@GetMapping("/estudiante/edit/{id}")
	public String editEstudianteForm( @PathVariable("id") Long id, Estudiante estudiante,Model model) {

		try {
			estudiante = estudianteService.buscarPorId(id);
			model.addAttribute("estudiante", estudiante);
			estudianteSeleccionado=estudiante;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "estudiante/editarE";
	}
	
	@PostMapping("/estudiante/guardar")
	public String guardarEstudiante(@Validated @ModelAttribute("estudiante") Estudiante estudiante,BindingResult result,Model model) {

		if(estudianteService.ValidarCamposVacios(estudiante)==1||result.hasErrors())
	     {
			if(estudianteService.ValidarCamposVacios(estudiante)==1)
			model.addAttribute("error", "Debe completar todos los campos");
			
			/*f(estudianteService.ValidarContra(estudiante)==1)
				model.addAttribute("error2", "Contraseña no es válida");*/
			
			if(estudianteService.ValidarEdad(estudiante)==1)
				model.addAttribute("error3", "Edad inválida");
			
			/*if(usuarioService.validar(estudiante.getDni()))
			{
				model.addAttribute("error4", "El usuario ya existe");
			}*/
			
			estudiante = estudianteService.buscarPorId(estudiante.getId());
			model.addAttribute("estudiante", estudiante);
			estudianteSeleccionado=estudiante;
			return "estudiante/editarE";
			
		}
		
		
		else {
	    			estudianteSeleccionado.setId(estudiante.getId());
	    			estudianteSeleccionado.setApellidos(estudiante.getApellidos());
	    			estudianteSeleccionado.setDni(estudiante.getDni());
	    			estudianteSeleccionado.setEdad(estudiante.getEdad());
	    			estudianteSeleccionado.setNombres(estudiante.getNombres());
	    			estudianteSeleccionado.setGrado(estudiante.getGrado());

	    			estudianteService.editarEstudiante(estudianteSeleccionado);
				    model.addAttribute("estudiante",estudianteSeleccionado);
	    			model.addAttribute("mensaje", "El estudiante se modificó correctamente");
	    			estudiante = estudianteService.buscarPorId(estudiante.getId());
	    			model.addAttribute("estudiante", estudiante);
	    			estudianteSeleccionado=estudiante;
	    			
	    			return "estudiante/editarE";

		}
		
	
	}
	
}
