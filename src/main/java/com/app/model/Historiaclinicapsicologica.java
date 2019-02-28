package com.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Historiaclinicapsicologica implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idhistoria;
	private Date fechacreacion;
	
	private Infante infante;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdhistoria() {
		return idhistoria;
	}
	public void setIdhistoria(Integer idhistoria) {
		this.idhistoria = idhistoria;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	
	@JsonIgnoreProperties(value={"historiasclinicas","hibernateLazyInitializer","handler"}, allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idinfante")
	public Infante getInfante() {
		return infante;
	}
	public void setInfante(Infante infante) {
		this.infante = infante;
	}
	
	
	
	
}
