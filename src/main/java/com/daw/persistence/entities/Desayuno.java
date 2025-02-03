package com.daw.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desayuno")
@Getter
@Setter
@NoArgsConstructor
public class Desayuno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double precio;

    @Column(columnDefinition = "VARCHAR(255)")
    private String imagen;

    @Column(columnDefinition = "DECIMAL(3,2)")
    private Double puntuacion;

}
