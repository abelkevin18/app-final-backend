package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Persona {
	
	private Integer idpersona;
	private String nombre;
	private String apellido;
	private String direccion;
	private String sexo;
	private String telefono;
	private Date fechanacimiento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 20, nullable = false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 30)
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Column(length = 100)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@NotEmpty(message ="no puede estar vacio")
	@Column(length = 1)
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	@Column(length = 9)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@NotNull(message ="no puede estar vacio")
	@Temporal(TemporalType.DATE)
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

}
