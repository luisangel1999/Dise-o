package com.edudemic.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reporteMentoriaxCurso")
public class ReporteCurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int cantidad;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	@OneToOne
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;
	
	public ReporteCurso() {
	}
	public ReporteCurso(Long id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}

	
	
}
