package com.daw.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstablecimientoDTO {
	private int id;
    private String nombre;
    private String ubicacion;
    private double puntuacion;
}
