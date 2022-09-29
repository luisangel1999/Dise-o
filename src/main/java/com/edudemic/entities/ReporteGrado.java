package com.edudemic.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="reporteMentoriaxGrado")
public class ReporteGrado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int cantidad;
	private int grado;
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
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public ReporteGrado() {
	}
	public ReporteGrado(Long id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}
	

}
