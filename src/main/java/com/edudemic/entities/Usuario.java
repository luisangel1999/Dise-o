package com.edudemic.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_codigo")
	private Long id;
	
	
	//@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Pattern(regexp = "[a-zA-Z\\s]{1,40}", message = "Nombre no válido")
	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;
	
	@Pattern(regexp = "[a-zA-Z\\s]{1,40}", message = "Apellidos no válido")
	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;
	
	//@Max(22)
	//@Min(11)
	private int edad;
	
	//@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]{8}", message = "DNI no es válido")
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;
	
	//@NotEmpty(message = "Ingresar Password")
	
	//@Pattern(regexp = "[a-zA-Z0-9]", message = "Contraseña no válida")
	private String password;
	
	@ManyToOne//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rol_id")
	private Rol rol;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Usuario() {
		super();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Usuario(Long id, String nombres, String apellidos, String dni, String password,int edad) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.password = password;
		this.edad=edad;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}



	
}