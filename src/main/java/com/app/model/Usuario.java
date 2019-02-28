package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Usuario extends Persona implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String nombreusuario;
	private String clave;
	
	
	@NotEmpty(message ="No puede estar vacio")
	@Size(min=4, max=20, message="El tamaño tiene que estar entre 4 y 20")
	@Column(nullable=false)
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	
	@NotEmpty(message ="No puede estar vacio")
	@Size(min=8, max=30, message="El tamaño tiene que estar entre 8 y 30")
	@Column(nullable=false)
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	
}
