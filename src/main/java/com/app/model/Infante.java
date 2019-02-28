package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Infante extends Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nivelsocioeconomico;
	private String niveleducacion;
	private String grado;
	
	private List<Historiaclinicapsicologica> historiasclinicas;
	private List<Cuestionario> cuestionarios;
	
	public Infante() {
		this.historiasclinicas = new ArrayList<>();
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 2)
	public String getNivelsocioeconomico() {
		return nivelsocioeconomico;
	}
	public void setNivelsocioeconomico(String nivelsocioeconomico) {
		this.nivelsocioeconomico = nivelsocioeconomico;
	}
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 15)
	public String getNiveleducacion() {
		return niveleducacion;
	}
	public void setNiveleducacion(String niveleducacion) {
		this.niveleducacion = niveleducacion;
	}
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 15, nullable = false)
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	

	@JsonIgnoreProperties(value={"infante","hibernateLazyInitializer","handler"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="infante", cascade = CascadeType.ALL)
	public List<Historiaclinicapsicologica> getHistoriasclinicas() {
		return historiasclinicas;
	}
	public void setHistoriasclinicas(List<Historiaclinicapsicologica> historiasclinicas) {
		this.historiasclinicas = historiasclinicas;
	}


	@JsonIgnoreProperties(value={"infante","hibernateLazyInitializer","handler"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="infante", cascade = CascadeType.ALL)
	public List<Cuestionario> getCuestionarios() {
		return cuestionarios;
	}

	public void setCuestionarios(List<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}
	
	
	
	
	

}
