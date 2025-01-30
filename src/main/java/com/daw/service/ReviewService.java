package com.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Review;
import com.daw.persistence.entities.Usuario;
import com.daw.persistence.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<ReviewDTO> getReviewPorUsuario(Integer idUsuario) {
        return reviewRepository.findByUsuarioId(idUsuario);
    }
		
	public List<ReviewDTO> getReviewsByBreakfast(Integer idDesayuno) {
        return reviewRepository.findByDesayunoId(idDesayuno);
    }
	
	//NO TERMINADO
	
}
