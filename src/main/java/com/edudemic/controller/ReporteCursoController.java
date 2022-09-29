package com.edudemic.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.edudemic.entities.Auxiliar;
import com.edudemic.entities.Mentoria;
import com.edudemic.service.EstudianteService;
import com.edudemic.service.ReporteCursoService;



@Controller
public class ReporteCursoController {
	
	private ReporteCursoService reporteCursoService;
	
	public ReporteCursoController(ReporteCursoService reporteCursoService) 
	{
		
		this.reporteCursoService=reporteCursoService;
	}
	
	@GetMapping("/reporte/curso")
	public String reportCM (Model model)
	{
		Auxiliar auxiliar=new Auxiliar();
		model.addAttribute("reporteCursos", reporteCursoService.reporteC());
		model.addAttribute("auxiliar", auxiliar);
		
		return "/reporte/cursoxmentoria";
	}	
	
	
	@PostMapping("/reporte/lista2")
	public String reporteMentoriaCurso(@Valid Mentoria reporte, BindingResult result, Auxiliar auxiliar, Model model, SessionStatus status) throws Exception
	{
		
		if(auxiliar.getFechaI()=="")
			{
			
			model.addAttribute("error", "Debe completar la Fecha de Inicio");
			model.addAttribute("reporteCursos", reporteCursoService.reporteC());
			model.addAttribute("auxiliar", auxiliar);
			return "/reporte/cursoxmentoria";
			}
		if(auxiliar.getFechaF()=="")
		{
		
		model.addAttribute("error2", "Debe completar la Fecha de Fin");
		model.addAttribute("reporteCursos", reporteCursoService.reporteC());
		model.addAttribute("auxiliar", auxiliar);
	 	return "/reporte/cursoxmentoria";
		}
		
		if(reporteCursoService.ValidarFecha(auxiliar)==1)
		{
		
		model.addAttribute("error3", "La Fecha de Fin debe ser mayor a la Fecha de Inicio");
		model.addAttribute("reporteCursos", reporteCursoService.reporteC());
		model.addAttribute("auxiliar", auxiliar);
	 	return "/reporte/cursoxmentoria";
		}
		
		
		else {
			model.addAttribute("reporteCursos", reporteCursoService.reporteFiltradoCurso(auxiliar));
			model.addAttribute("auxiliar", auxiliar);
			status.setComplete();
		}
		
			
		return "/reporte/cursoxmentoria";
	}
	
	
}
