package com.daw.persistence.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "establecimiento")
@Getter
@Setter
@NoArgsConstructor
public class Establecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 30, nullable = false, unique = true)
	private String nombre;
	
	@Column(length = 150)
	private String descripcion;
	
	@Column(length = 255, nullable = false)
	private String ubicacion;
	
	@Column(columnDefinition = "DECIMAL(3,2)", nullable = false)
	private Double puntuacion = 0.0;
	
	@OneToMany(mappedBy = "establecimiento", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Desayuno> desayunos;

}
