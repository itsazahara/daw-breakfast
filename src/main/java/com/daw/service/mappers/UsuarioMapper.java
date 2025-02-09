package com.daw.service.mappers;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Usuario;
import com.daw.service.dtos.UsuarioDTO;

@Component
public class UsuarioMapper {
	
	public static UsuarioDTO toDto(Usuario usuario) {
		
		UsuarioDTO dto = new UsuarioDTO();

		dto.setId(usuario.getId());
		dto.setPassword(usuario.getPassword());

		
		return dto;
		
	}
	
	public static Usuario toEntity(UsuarioDTO dto) {

		Usuario usuario = new Usuario();

		usuario.setId(dto.getId());
        usuario.setPassword(dto.getPassword());
        
        return usuario;
    }

}
