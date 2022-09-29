package com.edudemic.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



@Entity(name="estudiantes")
@PrimaryKeyJoinColumn(referencedColumnName = "usu_codigo")
public class Estudiante extends Usuario{
	@NotEmpty(message = "Ingresar el grado")
	@Column(name = "grado", nullable = false, length = 4)
	String grado;

	public Estudiante(String grado) {
		super();
		this.grado = grado;
	}

	public Estudiante() {
		super();
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}
	
}

