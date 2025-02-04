package com.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Review;
import com.daw.persistence.repository.ReviewRepository;
import com.daw.service.dtos.ReviewDTO;
import com.daw.service.mappers.ReviewMapper;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	/*public List<ReviewDTO> getReviewPorUsuario(Integer idUsuario) {
        return reviewRepository.findByUsuarioId(idUsuario);
    }*/
		
	public List<ReviewDTO> getReviewsByDesayuno(int idDesayuno) {
        return reviewRepository.findByDesayunoId(idDesayuno);
    }
	
	public ReviewDTO findById(int idReview) {
		return ReviewMapper.toDto(this.reviewRepository.findById(idReview).get());
	}
	
	public Optional<Review> findByIdEntity(int idReview) {
		return this.reviewRepository.findById(idReview);
	}
	
	public boolean existsReview(int idReview) {
		return this.reviewRepository.existsById(idReview);
	}
	
	public Review create(Review review) {
		return this.reviewRepository.save(review);
	}
	
	public Review update(Review review) {
		return this.reviewRepository.save(review);
	}
	
	public boolean delete(int idReview) {
		boolean result = false;

		if (this.reviewRepository.existsById(idReview)) {
			this.reviewRepository.deleteById(idReview);
			result = true;
		}

		return result;
	}
	
	public List<Review> findAllByOrderByFechaCreacionDesc() {
		return this.reviewRepository.findAllByOrderByFechaCreacionDesc();
	}
	
	public List<Review> findAllByOrderByFechaCreacionAsc() {
		return this.reviewRepository.findAllByOrderByFechaCreacionAsc();
	}
	
	public List<Review> findAllByOrderByPuntuacionDesc() {
		return this.reviewRepository.findAllByOrderByPuntuacionDesc();
	}
	
	public List<Review> findByDesayunoOrderByFechaCreacionDesc(int idDesayuno) {
		return this.reviewRepository.findByDesayunoOrderByFechaCreacionDesc(idDesayuno);
	}
	
	public List<Review> findByDesayunoOrderByPuntuacionDesc(int idDesayuno) {
		return this.reviewRepository.findByDesayunoOrderByPuntuacionDesc(idDesayuno);
	}
	
	//NO TERMINADO
	
}
