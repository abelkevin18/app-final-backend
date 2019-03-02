package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Programa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idprograma;
	private String descripcion;
	private String observaciones;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdprograma() {
		return idprograma;
	}
	public void setIdprograma(Integer idprograma) {
		this.idprograma = idprograma;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 300)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 300)
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	

}
