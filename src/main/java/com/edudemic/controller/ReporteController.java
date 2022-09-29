package com.edudemic.controller;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.edudemic.service.EstudianteService;
import com.edudemic.service.ReporteMentoriaService;
import com.edudemic.entities.Auxiliar;
import com.edudemic.entities.Mentoria;

@Controller
public class ReporteController {
	private EstudianteService estudianteService;
	private ReporteMentoriaService reporteMentoriaService;
	
	public ReporteController(EstudianteService estudianteService,ReporteMentoriaService reporteMentoriaService) 
	{
		this.estudianteService = estudianteService;
		this.reporteMentoriaService=reporteMentoriaService;
		
	}
	@GetMapping("/reporte/estudiante/nota")
	public String reporteE(Model model){
		
		model.addAttribute("estudiantes",estudianteService.listaEstudiantes());

		return "reporte/estudianteR";
	}
	@GetMapping("/reporte/mentoria")
	public String reportPM (Model model)
	{
		Auxiliar auxiliar=new Auxiliar();
		model.addAttribute("reporteMentorias", reporteMentoriaService.reporteM());
		model.addAttribute("auxiliar", auxiliar);
		
		
		return "reporte/mentoriaR";
	}	
	
	
	@PostMapping("/reporte/lista")
	public String reporteMentoria(@Valid Mentoria reporte, BindingResult result, Auxiliar auxiliar, Model model, SessionStatus status) throws Exception
	{
		
		if(auxiliar.getFechaI()=="")
			{
			
			model.addAttribute("error", "Debe completar la Fecha de Inicio");
			model.addAttribute("reporteMentorias", reporteMentoriaService.reporteM());
			model.addAttribute("auxiliar", auxiliar);
			return "reporte/mentoriaR";
			}
		if(auxiliar.getFechaF()=="")
		{
		
		model.addAttribute("error2", "Debe completar la Fecha de Fin");
		model.addAttribute("reporteMentorias", reporteMentoriaService.reporteM());
		model.addAttribute("auxiliar", auxiliar);
	 	return "reporte/mentoriaR";
		}
		
		if(reporteMentoriaService.ValidarFecha(auxiliar)==1)
		{
		
		model.addAttribute("error3", "La Fecha de Fin debe ser mayor a la Fecha de Inicio");
		model.addAttribute("reporteMentorias", reporteMentoriaService.reporteM());
		model.addAttribute("auxiliar", auxiliar);
	 	return "reporte/mentoriaR";
		}
		
		
		else {
			model.addAttribute("reporteMentorias", reporteMentoriaService.reporteFiltrado(auxiliar));
			model.addAttribute("auxiliar", auxiliar);
			status.setComplete();
		}
		
			
		return "reporte/mentoriaR";
	}
	
	
	
	
/*	@GetMapping("/reporte/curso")
	public String reportCM (Model model)
	{
		model.addAttribute("reporteMentorias", reporteMentoriaService.reporteM());
		
		return "reporte/mentoriaR";
	}	*/
}
