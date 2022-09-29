package com.edudemic.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="inscripciones")
public class Inscripcion 
{
	//editado x joao
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "mentoria_id", nullable = false)
	private Mentoria mentoria;
	
	@ManyToOne
	@JoinColumn(name = "estudiante_id", nullable = false)
	private Estudiante estudiante;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public Mentoria getMentoria(){ return mentoria; }
	public void setMentoria(Mentoria mentoria) { this.mentoria = mentoria; }
	
	public Estudiante getEstudiante() {	return estudiante; }
	public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante;}

	public Inscripcion() {	}
	public Inscripcion(Long id) 
	{
		super();
		this.id = id;
	}
}
