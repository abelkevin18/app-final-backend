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

import com.app.model.Profesor;
import com.app.service.IProfesorService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProfesorRestController {
	
	@Autowired
	IProfesorService profesorService;
	private final Logger log = LoggerFactory.getLogger(ProfesorRestController.class);
	
	@GetMapping("/profesores")
	public List<Profesor> index() {
		return profesorService.findAll();
	}
	
	@GetMapping("/profesores/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		Profesor profesor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			profesor = profesorService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}

		if (profesor == null) {
			response.put("mensaje", "El registro no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
		}

	}
	
	@PostMapping("/profesores")
	public ResponseEntity<?> create(
			@Valid @RequestBody 
			Profesor profesor,
			BindingResult result) {
		
		Profesor newProfesor = null;
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
			newProfesor = profesorService.create(profesor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el almacenamiento del registro en la base de datos");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El registro ha sido almacenado con éxito");
		response.put("profesor", newProfesor);
		return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
	}
	
	@PutMapping("/profesores/{id}")
	public ResponseEntity<?> update(
			@Valid @RequestBody Profesor profesor, 
			BindingResult result,
			@PathVariable int id) {
		
		Profesor currentProfesor = this.profesorService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if(currentProfesor == null) {
			response.put("mensaje", "El registro que pretende editar, no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		Profesor profesorActualizado = null;
		
		try {
			profesor.setIdpersona(currentProfesor.getIdpersona());
			profesorActualizado = profesorService.update(profesor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la actualización en la base de datos.");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		response.put("mensaje", "El registro ha sido actualizado con éxito.");
		response.put("profesor", profesorActualizado);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/profesores/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> r = new HashMap<>();
		try {
			profesorService.delete(id);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al eliminar el registro de la base de datos.");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		r.put("mensaje", "El registro ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.OK);
		
	}
	

}