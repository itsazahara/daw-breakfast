package com.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Usuario;
import com.daw.persistence.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAllUsuario() {
		return this.usuarioRepository.findAll();
	}

	public boolean existUsuario(int idUsuario) {
		return this.usuarioRepository.existsById(idUsuario);
	}

	public Optional<Usuario> findByIdUsuario(int idUsuario) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);

		return usuario;
	}

	public Usuario updateUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public Usuario createUsuario(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public boolean deleteUsuario(int idUsuario) {
		boolean result = false;

		if (this.usuarioRepository.existsById(idUsuario)) {
			this.usuarioRepository.deleteById(idUsuario);

			result = true;
		}

		return result;
	}
	
	public Usuario updatePassword(int idUsuario, String newPassword) {
		
		Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
		
		usuario.setPassword(newPassword);
		
		return this.usuarioRepository.save(usuario);
		
	}
	
	public Boolean checkPassword(int idUsuario, String password) {
		
		Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
		
		boolean check = usuario.getPassword().equals(password);
		
		return check;
		
	}

}
