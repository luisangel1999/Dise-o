package com.edudemic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edudemic.entities.Inscripcion;
import com.edudemic.entities.Profesor;
import com.edudemic.entities.Curso;
import com.edudemic.entities.Auxiliar;
import com.edudemic.entities.ReporteCurso;
import com.edudemic.entities.ReporteMentoria;
import com.edudemic.repository.InscripcionRepository;
import com.edudemic.repository.ProfesorRepository;
import com.edudemic.repository.CursoRepository;

@Service
public class ReporteCursoService {

	
	private CursoRepository cursoRepository;
	private InscripcionRepository inscripcionRepository;
	public ReporteCursoService(CursoRepository cursoRepository, InscripcionRepository inscripcionRepository) 
	{
		this.cursoRepository=cursoRepository;
		this.inscripcionRepository=inscripcionRepository;
	}
	
	public List<ReporteCurso> reporteC()
	{
		List<Curso> listP =  cursoRepository.findAll();
		List<Inscripcion> listM =  inscripcionRepository.findAll();
		List<ReporteCurso> listPM = new ArrayList<>();
		for(int i=0; i <listP.size(); i++)
		{
			ReporteCurso reporteMentoria = new ReporteCurso();
			reporteMentoria.setCurso(listP.get(i));	
			for(int j=0; j<listM.size(); j++)
			{
				if(listM.get(j).getMentoria().getProfesor().getCurso().getId()==listP.get(i).getId())
				{
					reporteMentoria.setCantidad(reporteMentoria.getCantidad()+1);
				}
			}
			listPM.add(reporteMentoria);
		}
		return listPM;
	}
	
	public List<ReporteCurso> reporteFiltradoCurso(Auxiliar auxiliar)
	{
		
		String[] parts=auxiliar.getFechaI().split("-");
		String Ipart1 = parts[0];
		int añoI = Integer.parseInt(Ipart1);
		String Ipart2 = parts[1];
		int mesI = Integer.parseInt(Ipart2);
		String Ipart3 = parts[2];
		int diaI = Integer.parseInt(Ipart3);
		
		
		String[] parts2=auxiliar.getFechaF().split("-");
		String Fpart1 = parts2[0];
		int añoF = Integer.parseInt(Fpart1);
		String Fpart2 = parts2[1];
		int mesF = Integer.parseInt(Fpart2);
		String Fpart3 = parts2[2];
		int diaF = Integer.parseInt(Fpart3);
		
		
		List<Curso> listP =  cursoRepository.findAll();
		List<Inscripcion> listM =  inscripcionRepository.findAll();
		List<ReporteCurso> listPM = new ArrayList<>();
		for(int i=0; i <listP.size(); i++)
		{
			ReporteCurso reporteMentoria = new ReporteCurso();
			reporteMentoria.setCurso(listP.get(i));	
			for(int j=0; j<listM.size(); j++)
			{
				String[] partsInscripcion=listM.get(j).getMentoria().getFecha().split("-");
				String Mpart1 = partsInscripcion[0];
				int añoM = Integer.parseInt(Mpart1);
				String Mpart2 = partsInscripcion[1];
				int mesM = Integer.parseInt(Mpart2);
				String Mpart3 = partsInscripcion[2];
				int diaM = Integer.parseInt(Mpart3);
				
				
				if(añoM>=añoI && añoM<=añoF)
				{
					if(mesM>=mesI && mesM<=mesF)
					{  
					  	if(diaM>=diaI && diaM<=diaF)
					  	{
				
				if(listM.get(j).getMentoria().getProfesor().getCurso().getId()==listP.get(i).getId())
				{
					reporteMentoria.setCantidad(reporteMentoria.getCantidad()+1);
				}
					  	}
					}
				}
				
			}
			listPM.add(reporteMentoria);
		}
		return listPM;
	
	
	}
	
	
public int ValidarFecha(Auxiliar auxiliar) {
		
		int validar=0;
		String[] parts=auxiliar.getFechaI().split("-");
		String Ipart1 = parts[0];
		int añoI = Integer.parseInt(Ipart1);
		String Ipart2 = parts[1];
		int mesI = Integer.parseInt(Ipart2);
		String Ipart3 = parts[2];
		int diaI = Integer.parseInt(Ipart3);
		
		
		String[] parts2=auxiliar.getFechaF().split("-");
		String Fpart1 = parts2[0];
		int añoF = Integer.parseInt(Fpart1);
		String Fpart2 = parts2[1];
		int mesF = Integer.parseInt(Fpart2);
		String Fpart3 = parts2[2];
		int diaF = Integer.parseInt(Fpart3);
		
		
		if(añoF<añoI)
		{
			validar=1;
			
		}
		else {
			
			if(mesF<mesI)
			{
				validar=1;
			}
			else {
				
				if(diaF<diaI)
					validar=1;
				
			}
			
		}
		
		
		return validar;
	}
	
}
