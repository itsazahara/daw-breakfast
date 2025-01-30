package com.daw.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Usuario;

public interface UsuarioRepository extends ListCrudRepository<Usuario, Integer> {
	
	

}
