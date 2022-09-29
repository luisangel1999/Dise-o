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
import com.edudemic.service.ReporteGradoService;
import com.edudemic.service.ReporteMentoriaService;

@Controller
public class ReporteGradoController {
	
	private ReporteGradoService reporteGradoService;
	
	
	public ReporteGradoController(ReporteGradoService reporteGradoService) 
	{
		
		this.reporteGradoService=reporteGradoService;
	}
	
	@GetMapping("/reporte/grado")
	public String reportG (Model model)
	{
		Auxiliar auxiliar=new Auxiliar();
		model.addAttribute("reporteGrados", reporteGradoService.reporteG());
		model.addAttribute("auxiliar", auxiliar);
		
		return "/reporte/gradoxmentoria";
	}	
	
	
	@PostMapping("/reporte/lista3")
	public String reporteMentoria(@Valid Mentoria reporte, BindingResult result, Auxiliar auxiliar, Model model, SessionStatus status) throws Exception
	{
		
		if(auxiliar.getFechaI()=="")
			{
			
			model.addAttribute("error", "Debe completar la Fecha de Inicio");
			model.addAttribute("reporteGrados", reporteGradoService.reporteG());
			model.addAttribute("auxiliar", auxiliar);
			return "reporte/gradoxmentoria";
			}
		if(auxiliar.getFechaF()=="")
		{
		
		model.addAttribute("error2", "Debe completar la Fecha de Fin");
		model.addAttribute("reporteGrados", reporteGradoService.reporteG());
		model.addAttribute("auxiliar", auxiliar);
	 	return "reporte/gradoxmentoria";
		}
		
		if(reporteGradoService.ValidarFecha(auxiliar)==1)
		{
		
		model.addAttribute("error3", "La Fecha de Fin debe ser mayor a la Fecha de Inicio");
		model.addAttribute("reporteGrados", reporteGradoService.reporteG());
		model.addAttribute("auxiliar", auxiliar);
	 	return "reporte/gradoxmentoria";
		}
		
		
		else {
			model.addAttribute("reporteGrados", reporteGradoService.reporteFiltradoGrado(auxiliar));
			model.addAttribute("auxiliar", auxiliar);
			status.setComplete();
		}
		
			
		return "reporte/gradoxmentoria";
	}
	

}
