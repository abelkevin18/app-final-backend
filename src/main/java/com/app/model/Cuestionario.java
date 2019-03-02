package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
public class Cuestionario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idcuestionario;
	private String nombre;
	private Date fecha;
	
	private String niveleducacion;
	private String grado;
	
	private Infante infante;
	private Profesor profesor;
	
	private Programa programa;
	
	private List<Detallecuestionario> detallecuestionarios;
	
	public Cuestionario() {
		this.detallecuestionarios = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdcuestionario() {
		return idcuestionario;
	}
	
	public void setIdcuestionario(Integer idcuestionario) {
		this.idcuestionario = idcuestionario;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(nullable=false,length = 30)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@NotNull(message ="no puede estar vacio")
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	@NotNull(message = "no puede estar vacío, debe elegir un infante")
	@JsonIgnoreProperties(value={"cuestionarios","hibernateLazyInitializer","handler"}, allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idinfante")
	public Infante getInfante() {
		return infante;
	}

	public void setInfante(Infante infante) {
		this.infante = infante;
	}

	
	@NotNull(message = "no puede estar vacío, debe elegir un profesor")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idprofesor")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	@NotNull(message = "no puede estar vacío, debe elegir un programa")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idprograma")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idcuestionario")
	public List<Detallecuestionario> getDetallecuestionarios() {
		return detallecuestionarios;
	}

	public void setDetallecuestionarios(List<Detallecuestionario> detallecuestionarios) {
		this.detallecuestionarios = detallecuestionarios;
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
	
	
	
	
	
	
	
}
