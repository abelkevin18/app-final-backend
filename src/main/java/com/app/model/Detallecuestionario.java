package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Detallecuestionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer iddetallecuestionario;
	private Integer numeroitem;
	private String descripcionitem;
	private String respuestaitem;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIddetallecuestionario() {
		return iddetallecuestionario;
	}
	public void setIddetallecuestionario(Integer iddetallecuestionario) {
		this.iddetallecuestionario = iddetallecuestionario;
	}
	
	public Integer getNumeroitem() {
		return numeroitem;
	}
	public void setNumeroitem(Integer numeroitem) {
		this.numeroitem = numeroitem;
	}
	
	@Column(length=300)
	public String getDescripcionitem() {
		return descripcionitem;
	}
	public void setDescripcionitem(String descripcionitem) {
		this.descripcionitem = descripcionitem;
	}
	
	@Column(length=1)
	public String getRespuestaitem() {
		return respuestaitem;
	}
	public void setRespuestaitem(String respuestaitem) {
		this.respuestaitem = respuestaitem;
	}
	
	
}
