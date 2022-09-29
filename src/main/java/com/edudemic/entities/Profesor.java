package com.edudemic.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="profesores")
@PrimaryKeyJoinColumn(referencedColumnName = "usu_codigo")
public class Profesor extends Usuario{

	@ManyToOne
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;
	public Profesor() {
		super();
		
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
}

