package com.daw.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DesayunoDTO {

    private Integer id;
    private String nombre;
    private Double precio;
    private String imagen;
    private Double puntuacion;
    private String establecimiento;
    private List<ReviewDTO> reviews;
}
