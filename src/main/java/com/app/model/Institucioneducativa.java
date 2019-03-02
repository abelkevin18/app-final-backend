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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Institucioneducativa implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	
	private List<Infante> infantes;
	
	public Institucioneducativa() {
		this.infantes = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 50)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 120)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 12)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@JsonIgnoreProperties(value={"institucioneducativa","hibernateLazyInitializer","handler"}, allowSetters=true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="institucioneducativa", cascade = CascadeType.ALL)
	@JsonIgnore
	public List<Infante> getInfantes() {
		return infantes;
	}

	public void setInfantes(List<Infante> infantes) {
		this.infantes = infantes;
	}
	
	
	
	
	
}
