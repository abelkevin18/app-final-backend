package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Infante extends Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nivelsocioeconomico;
	
	private Institucioneducativa institucioneducativa;
	
	//private List<Historiaclinicapsicologica> historiasclinicas;
	private List<Cuestionario> cuestionarios;
	
	public Infante() {
		this.cuestionarios = new ArrayList<>();
	}
	
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 2)
	public String getNivelsocioeconomico() {
		return nivelsocioeconomico;
	}
	public void setNivelsocioeconomico(String nivelsocioeconomico) {
		this.nivelsocioeconomico = nivelsocioeconomico;
	}
	

	@JsonIgnoreProperties(value={"infante","hibernateLazyInitializer","handler"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="infante", cascade = CascadeType.ALL)
	public List<Cuestionario> getCuestionarios() {
		return cuestionarios;
	}

	public void setCuestionarios(List<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}


	@NotNull(message = "no puede estar vacío, debe elegir una institucion educativa")
	@JsonIgnoreProperties(value={"infantes","hibernateLazyInitializer","handler"}, allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idinstitucioneducativa")
	public Institucioneducativa getInstitucioneducativa() {
		return institucioneducativa;
	}


	public void setInstitucioneducativa(Institucioneducativa institucioneducativa) {
		this.institucioneducativa = institucioneducativa;
	}
	
	
	
	
	

}
