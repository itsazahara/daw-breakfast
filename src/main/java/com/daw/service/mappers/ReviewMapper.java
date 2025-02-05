package com.daw.service.mappers;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Review;
import com.daw.service.dtos.ReviewDTO;

@Component
public class ReviewMapper {

	public static ReviewDTO toDto(Review review) {
		ReviewDTO dto = new ReviewDTO();

		dto.setId(review.getId());
		dto.setFecha(review.getFecha());
		dto.setPrecio(review.getPrecio());
		dto.setImagen(review.getImagen());
		dto.setPuntuacion(review.getPuntuacion());
		dto.setComentarios(review.getComentarios());

		dto.setUsername(review.getUsuario().getUsername());
		dto.setEmail(review.getUsuario().getEmail());
		dto.setNombreDesayuno(review.getDesayuno().getNombre());
		dto.setNombreEstablecimiento(review.getDesayuno().getEstablecimiento().getNombre());

		return dto;

		// NO TERMINADO

	}
}
