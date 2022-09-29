package com.edudemic.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reporteMentoria")
public class ReporteMentoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int cantidad;
	@OneToOne
	@JoinColumn(name = "profesor_id", nullable = false)
	private Profesor profesor;
	public ReporteMentoria() {
	}
	public ReporteMentoria(Long id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}
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
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
}
