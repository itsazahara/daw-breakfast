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

	public List<Review> findAll() {
		return this.reviewRepository.findAll();
	}

	public List<Review> getReviewByUsuario(int idUsuario) {
		return reviewRepository.findByUsuarioId(idUsuario);
	}

	public List<Review> getReviewsByDesayuno(int idDesayuno) {
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
		return this.reviewRepository.findAllByOrderByFechaDesc();
	}

	public List<Review> findAllByOrderByFechaCreacionAsc() {
		return this.reviewRepository.findAllByOrderByFechaAsc();
	}

	public List<Review> findAllByOrderByPuntuacionDesc() {
		return this.reviewRepository.findAllByOrderByPuntuacionDesc();
	}

	public List<Review> findByDesayunoIdOrderByFechaDesc(int idDesayuno) {
		return this.reviewRepository.findByDesayunoIdOrderByFechaDesc(idDesayuno);
	}

	public List<Review> findByDesayunoIdOrderByPuntuacionDesc(int idDesayuno) {
		return this.reviewRepository.findByDesayunoIdOrderByPuntuacionDesc(idDesayuno);
	}

}
