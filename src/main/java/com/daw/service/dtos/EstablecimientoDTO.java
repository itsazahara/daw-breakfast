package com.daw.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstablecimientoDTO {
	private Integer  id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private double puntuacion;
}
