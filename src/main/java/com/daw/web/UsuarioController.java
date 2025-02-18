package com.daw.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Usuario;
import com.daw.service.UsuarioService;
import com.daw.service.dtos.PasswordDTO;
import com.daw.service.dtos.UsuarioDTO;
import com.daw.service.mappers.UsuarioMapper;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios() {
		return ResponseEntity.ok(this.usuarioService.findAllUsuario());
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable int idUsuario) {

		if (this.usuarioService.existUsuario(idUsuario)) {
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

		if (this.usuarioService.existUsuario(idUsuario)) {
			return ResponseEntity.ok(this.usuarioService.updateUsuario(usuario));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable int idUsuario) {

		if (this.usuarioService.deleteUsuario(idUsuario)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{idUsuario}/password")
	public ResponseEntity<Usuario> updatePasswd(@PathVariable int idUsuario, @RequestBody UsuarioDTO usuarioDTO,
			@RequestParam String newPassword) {
		if (this.usuarioService.existUsuario(idUsuario)) {
			Usuario usuario = usuarioService.findByIdUsuario(idUsuario).get();
			UsuarioMapper.toEntity(usuarioDTO);
			return ResponseEntity.ok(this.usuarioService.updatePassword(idUsuario, newPassword));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/{idUsuario}/checkPassword")
	public ResponseEntity<String> checkPasswd(@PathVariable int idUsuario, @RequestBody UsuarioDTO usuarioDTO,
			@RequestParam String newPassword) {
		if (this.usuarioService.existUsuario(idUsuario)) {
			boolean passwdCheck = this.usuarioService.checkPassword(idUsuario, usuarioDTO.getPassword());

			if (passwdCheck) {
				return ResponseEntity.ok("Contraseña coincide correctamente.");
			} else {
				Usuario usuarioActualizado = this.usuarioService.updatePasswordCheck(idUsuario, newPassword);
				return ResponseEntity.badRequest().body("La Contraseña no coincide. Contraseña actualizada.");
			}
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}/passwordChange")
	public ResponseEntity<UsuarioDTO> actualizarPassword(@PathVariable int id, @RequestBody PasswordDTO passwordDTO) {
		UsuarioDTO usuarioActualizado = usuarioService.updatePasswordIfMatch(id, passwordDTO);
		return ResponseEntity.ok(usuarioActualizado);
	}

}
