package com.daw.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 30, nullable = false)
	private String username;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 50, nullable = false, unique = true)
	private String password;
	
	
}
