package com.edudemic.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

public class Auxiliar {

	@NotEmpty(message = "Selecciona una fecha de inicio")
	String fechaI;
	public String getFechaI() {
		return fechaI;
	}

	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
	}

	public String getFechaF() {
		return fechaF;
	}

	public void setFechaF(String fechaF) {
		this.fechaF = fechaF;
	}

	String fechaF;
	
	public Auxiliar()
	{
		
	}
	
	
	
	
	
	
	
	
}
