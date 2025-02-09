package com.daw.service.mappers;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Usuario;
import com.daw.service.dtos.UsuarioDTO;

@Component
public class UsuarioMapper {
	
	public static UsuarioDTO toDto(Usuario usuario) {
		
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setPassword(usuario.getPassword());
		dto.setNewPassword("");
		
		return dto;
		
	}
	
	public static Usuario toEntity(UsuarioDTO dto, Usuario usuario) {
		
        if (dto.getPassword() != null) {
            usuario.setPassword(dto.getNewPassword());
        }
        
        return usuario;
    }

}
