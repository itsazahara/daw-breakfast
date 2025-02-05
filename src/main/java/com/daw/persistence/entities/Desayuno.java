package com.daw.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "desayuno")
@Getter
@Setter
@NoArgsConstructor
public class Desayuno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double precio;

    @Column(length = 255)
    private String imagen;

    @Column(columnDefinition = "DECIMAL(3,2)")
    private Double puntuacion;


    @ManyToOne
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id", insertable = false, updatable = false)
    private Establecimiento establecimiento;

    @OneToMany(mappedBy = "desayuno", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;
}
