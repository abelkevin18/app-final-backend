package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Detalleresultado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer iddetalleresultado;
	private String criterio;
	private Integer puntuacion;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIddetalleresultado() {
		return iddetalleresultado;
	}
	public void setIddetalleresultado(Integer iddetalleresultado) {
		this.iddetalleresultado = iddetalleresultado;
	}
	
	@Column(nullable=false,length=20)
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
}
