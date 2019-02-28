package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Resultadocuestionario implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idresultado;
	private String apreciacion;
	
	private Cuestionario cuestionario;
	
	private List<Detalleresultado> detalleresultados;
	
	public Resultadocuestionario() {
		this.detalleresultados = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdresultado() {
		return idresultado;
	}
	public void setIdresultado(Integer idresultado) {
		this.idresultado = idresultado;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length=300)
	public String getApreciacion() {
		return apreciacion;
	}
	public void setApreciacion(String apreciacion) {
		this.apreciacion = apreciacion;
	}
	
	@NotNull(message = "no puede estar vacío")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idcuestionario")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	//@JsonIgnore
	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idresultado")
	public List<Detalleresultado> getDetalleresultados() {
		return detalleresultados;
	}

	public void setDetalleresultados(List<Detalleresultado> detalleresultados) {
		this.detalleresultados = detalleresultados;
	}
	
	
	
	
	
	
	
}
