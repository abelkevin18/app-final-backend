package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.app.model.Usuario;
import com.app.service.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	@Autowired
	private IUsuarioService us;
	
	@GetMapping("/usuarios")
	public List<Usuario> index() {
		return us.findAll();
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(
			@Valid @RequestBody 
			Usuario usuario,
			BindingResult result) {
		
		
		Usuario newUsuario = null;
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
			newUsuario = us.create(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro en la bd");
			response.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensaje", "El objeto ha sido registrado con éxito");
		response.put("usuario", newUsuario);
		
		return new ResponseEntity<Map<String, Object>> (response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(
			@Valid @RequestBody Usuario usuario, 
			BindingResult result,
			@PathVariable int id) {
		
		Usuario currentUsuario = this.us.findById(id);
		Map<String, Object> r = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());

			r.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.BAD_REQUEST);
		}
		
		if(currentUsuario == null) {
			r.put("mensaje", "No se puede editar porque El usuario con ID: "+ id +" no existe en la BD!");
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.NOT_FOUND);
		}
		
		Usuario usuarioActualizado = null;
		
		try {
			currentUsuario = this.us.findById(id);
			currentUsuario.setNombreusuario(usuario.getNombreusuario());
			
			usuarioActualizado = us.update(currentUsuario);
			
			
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al realizar la modificación en la bd");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		r.put("mensaje", "El usuario ha sido actualizado con éxito");
		r.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Map<String, Object> r = new HashMap<>();
		try {
			us.delete(id);
		} catch (DataAccessException e) {
			r.put("mensaje", "Error al realizar la eliminación en la bd");
			r.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		r.put("mensaje", "El usuario ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>> (r,HttpStatus.OK);
		
	}
	

}
