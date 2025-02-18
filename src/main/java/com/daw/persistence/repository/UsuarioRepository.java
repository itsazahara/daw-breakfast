package com.daw.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Usuario;

public interface UsuarioRepository extends ListCrudRepository<Usuario, Integer> {
	
	List<Usuario> findByPassword (String password);
	
	

}
