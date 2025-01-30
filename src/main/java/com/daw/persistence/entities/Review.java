package com.daw.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "id_desayuno")
	private Integer idDesayuno;
	
	@Column(columnDefinition = "DATETIME")
	private LocalDateTime fecha;
	
	@Column(columnDefinition = "DECIMAL(5,2)")
	private Double precio;
	
	@Column(length = 255, nullable = false, unique = true)
	private String imagen;
	
	@Column(nullable = false, unique = true)
	private Integer puntuacion;
	
	@Column(columnDefinition = "TEXT(200)")
	private String comentarios;
	
	//NO TERMINADO

}
