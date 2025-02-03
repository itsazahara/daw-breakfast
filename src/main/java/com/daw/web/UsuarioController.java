package com.daw.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.persistence.entities.Usuario;
import com.daw.service.UsuarioService;

import jakarta.persistence.Entity;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios() {
		return ResponseEntity.ok(this.usuarioService.findAllUsuario());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable int idUsuario) {
		
		if(this.usuarioService.existUsuario(idUsuario)) {
			return ResponseEntity.ok(this.usuarioService.findByIdUsuario(idUsuario));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(this.usuarioService.createUsuario(usuario));
		
	}
	
	@PutMapping("/{idUsuario}")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable int idUsuario) {
		
		if(this.usuarioService.existUsuario(idUsuario)) {
			return ResponseEntity.ok(this.usuarioService.updateUsuario(usuario));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable int idUsuario) {
		
		if(this.usuarioService.deleteUsuario(idUsuario)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
}
