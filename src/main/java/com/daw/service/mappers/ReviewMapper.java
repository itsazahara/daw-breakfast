package com.daw.service.mappers;

import com.daw.persistence.entities.Review;
import com.daw.service.dtos.ReviewDTO;

public class ReviewMapper {

	public static ReviewDTO toDto(Review review) {
		ReviewDTO dto = new ReviewDTO();
		
		dto.setId(review.getId());

	}
}
