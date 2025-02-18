package com.daw.service.mappers;

import com.daw.persistence.entities.Establecimiento;
import com.daw.service.dtos.EstablecimientoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstablecimientoMapper {

    public EstablecimientoDTO toDTO(Establecimiento establecimiento) {
        EstablecimientoDTO dto = new EstablecimientoDTO();

        dto.setId(establecimiento.getId());
        dto.setNombre(establecimiento.getNombre());
        dto.setDescripcion(establecimiento.getDescripcion());
        dto.setUbicacion(establecimiento.getUbicacion());
        dto.setPuntuacion(establecimiento.getPuntuacion());

        return dto;
    }

    public Establecimiento toEntity(EstablecimientoDTO dto) {
        Establecimiento establecimiento = new Establecimiento();
        
        if (dto.getId() != null) {
            establecimiento.setId(dto.getId());
        }
        
        establecimiento.setNombre(dto.getNombre());
        establecimiento.setDescripcion(dto.getDescripcion());
        establecimiento.setUbicacion(dto.getUbicacion());

        return establecimiento;
    }

    public List<EstablecimientoDTO> toDTOList(List<Establecimiento> establecimientos) {
        List<EstablecimientoDTO> dtos = new ArrayList<>();
        for (Establecimiento establecimiento : establecimientos) {
            dtos.add(toDTO(establecimiento));
        }
        return dtos;
    }

    public List<Establecimiento> toEntityList(List<EstablecimientoDTO> dtos) {
        List<Establecimiento> establecimientos = new ArrayList<>();
        for (EstablecimientoDTO dto : dtos) {
            establecimientos.add(toEntity(dto));
        }
        return establecimientos;
    }
}
