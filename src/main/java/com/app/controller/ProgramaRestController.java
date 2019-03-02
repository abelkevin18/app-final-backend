package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Programa;
import com.app.service.IProgramaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProgramaRestController {
	
	@Autowired
	IProgramaService programaService;
	private final Logger log = LoggerFactory.getLogger(ProgramaRestController.class);
	
	@GetMapping("/programas")
	public List<Programa> index() {
		return programaService.findAll();
	}
	
	@GetMapping("/programas/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		Programa programa = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			programa = programaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}

		if (programa == null) {
			response.put("mensaje", "El registro no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Programa>(programa, HttpStatus.OK);
		}

	}
	
	@PostMapping("/programas")
	public ResponseEntity<?> create(
			@Valid @RequestBody 
			Programa programa,
			BindingResult result) {
		
		Programa newPrograma = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newPrograma = programaService.create(programa);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el almacenamiento del registro en la base de datos");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El registro ha sido almacenado con éxito");
		response.put("programa", newPrograma);
		return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
	}
	
	@PutMapping("/programas/{id}")
	public ResponseEntity<?> update(
			@Valid @RequestBody Programa programa, 
			BindingResult result,
			@PathVariable int id) {
		
		Programa currentPrograma = this.programaService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if(currentPrograma == null) {
			response.put("mensaje", "El registro que pretende editar, no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		Programa programaActualizado = null;
		
		try {
			programa.setIdprograma (currentPrograma.getIdprograma());
			programaActualizado = programaService.update(programa);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la actualización en la base de datos.");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		response.put("mensaje", "El registro ha sido actualizado con éxito.");
		response.put("programa", programaActualizado);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/programas/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> r = new HashMap<>();
		try {
			programaService.delete(id);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al eliminar el registro de la base de datos.");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		r.put("mensaje", "El registro ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.OK);
		
	}
	

}