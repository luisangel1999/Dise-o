package com.edudemic.entities;

import javax.validation.constraints.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Entity
@Table(name="cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[a-zA-Z\\s]{1,25}", message = "El nombre del curso es invalido")
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@Size(max = 500, message = "Limite de 500 caracteres permitidos")
	@Column(name = "descripcion", nullable = true, length = 500)
	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;

	}
}