package com.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Usuario;
import com.daw.persistence.repository.UsuarioRepository;
import com.daw.service.mappers.UsuarioMapper;

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

		if(usuario.getId() == null){
			throw  new IllegalArgumentException("El usuario con ID " + usuario.getId() + " es nulo");
		}
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
		UsuarioMapper.toDto(usuario);
	    return this.usuarioRepository.save(usuario);
	}
	
	public Usuario updatePasswordCheck(int idUsuario, String newPassword) {
	    Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(idUsuario);
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        usuario.setPassword(newPassword);
	        return this.usuarioRepository.save(usuario);
	    } else {
	        return null;
	    }
	}

	
	public boolean checkPassword(int idUsuario, String password) {
	    Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(idUsuario);
	    if (optionalUsuario.isPresent()) {
	        Usuario usuario = optionalUsuario.get();
	        return usuario.getPassword().equals(password);
	    } else {
	        return false; // Devuelve false si no se encuentra el usuario
	    }
	}



}
