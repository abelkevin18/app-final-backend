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

import com.app.model.Infante;
import com.app.service.IInfanteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class InfanteRestController {

	@Autowired
	IInfanteService is;

	private final Logger log = LoggerFactory.getLogger(InfanteRestController.class);

	@GetMapping("/infantes")
	public List<Infante> index() {
		return is.findAll();
	}

	@GetMapping("/infantes/{id}")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		Infante infante = null;
		Map<String, Object> r = new HashMap<>();
		
		try {
			infante = is.findById(id);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al realizar la consulta en la base de datos");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		

		if (infante == null) {
			r.put("mensaje", "El infante no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Infante>(infante, HttpStatus.OK);
		}

	}
	
	@PostMapping("/infantes")
	public ResponseEntity<?> create(
			@Valid @RequestBody 
			Infante infante,
			BindingResult result) {
		
		Infante newInfante = null;
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
			newInfante = is.create(infante);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El infante ha sido registrado con éxito");
		response.put("infante", newInfante);
		return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/infantes/{id}")
	public ResponseEntity<?> update(
			@Valid @RequestBody Infante infante, 
			BindingResult result,
			@PathVariable int id) {
		
		Map<String, Object> r = new HashMap<>();
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			r.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.BAD_REQUEST);
		}
		
		Infante currentInfante = this.is.findById(id);
		if(currentInfante == null) {
			r.put("mensaje", "No es posible editar, debido a que el infante no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.NOT_FOUND);
		}
		
		Infante infanteActualizado = null;
		
		try {
			//currentInfante = this.is.findById(id);
			
			/*currentInfante.setNombre(infante.getNombre());
			currentInfante.setApellido(infante.getApellido());
			currentInfante.setDireccion(infante.getDireccion());
			currentInfante.setSexo(infante.getSexo());
			currentInfante.setTelefono(infante.getTelefono());
			currentInfante.setFechanacimiento(infante.getFechanacimiento());
			
			currentInfante.setNiveleducacion(infante.getNiveleducacion());
			currentInfante.setNivelsocioeconomico(infante.getNivelsocioeconomico());
			currentInfante.setGrado(infante.getGrado());*/
			infante.setIdpersona(currentInfante.getIdpersona());
			infanteActualizado = is.update(infante);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al realizar la modificación en la base de datos");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		
		r.put("mensaje", "El infante ha sido actualizado con éxito");
		r.put("infante", infanteActualizado);
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/infantes/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> r = new HashMap<>();
		try {
			is.delete(id);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al realizar la eliminación en la base de datos");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		r.put("mensaje", "El infante ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.OK);
		
	}
	

}
