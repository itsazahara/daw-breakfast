package com.daw.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.entities.Review;
import com.daw.persistence.entities.Usuario;
import com.daw.persistence.repository.DesayunoRepository;
import com.daw.persistence.repository.EstablecimientoRepository;
import com.daw.persistence.repository.ReviewRepository;
import com.daw.service.dtos.DesayunoDTO;
import com.daw.service.dtos.ReviewDTO;
import com.daw.service.mappers.ReviewMapper;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private DesayunoRepository desayunoRepository;

	@Autowired
	private EstablecimientoRepository establecimientoRepository;
	
	@Autowired
	private DesayunoService desayunoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	

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
		// Validación de la puntuación
		if (review.getPuntuacion() < 0 || review.getPuntuacion() > 5) {
			throw new IllegalArgumentException("La puntuación debe estar entre 0 y 5.");
		}

		review.setFecha(LocalDateTime.now());
		
		Review savedReview = this.reviewRepository.save(review);
		recalcularPuntuacion(savedReview.getIdDesayuno());
		
		Optional<Desayuno> d = this.desayunoService.findEntityById(review.getIdDesayuno());
		Usuario u = this.usuarioService.findByIdUsuario(review.getIdUsuario()).get();
		
		savedReview.setDesayuno(d.get());
		savedReview.setUsuario(u);

		return savedReview;
	}

	public Review update(Review review) {
		if (review.getPuntuacion() < 0 || review.getPuntuacion() > 5) {
			throw new IllegalArgumentException("La puntuación debe estar entre 0 y 5.");
		}

		Review updatedReview = this.reviewRepository.save(review);
		recalcularPuntuacion(updatedReview.getIdDesayuno());

		return updatedReview;
	}

	public boolean delete(int idReview) {
		boolean result = false;

		Optional<Review> review = this.reviewRepository.findById(idReview);
		if (review.isPresent()) {
			Review reviewToDelete = review.get();

			this.reviewRepository.deleteById(idReview);
			recalcularPuntuacion(reviewToDelete.getIdDesayuno());

			result = true;
		}

		return result;
	}

	public List<Review> findAllByOrderByFechaDesc() {
		return this.reviewRepository.findAllByOrderByFechaDesc();
	}

	public List<Review> findAllByOrderByFechaAsc() {
		return this.reviewRepository.findAllByOrderByFechaAsc();
	}

	public List<Review> findAllByOrderByPuntuacionDesc() {
		return this.reviewRepository.findAllByOrderByPuntuacionDesc();
	}

	public List<Review> getReviewsRecienteByDesayuno(int idDesayuno) {
		return reviewRepository.findByDesayunoIdOrderByFechaDesc(idDesayuno);
	}

	public List<Review> getReviewsByPuntuacionDesc(int idDesayuno) {
		return reviewRepository.findByDesayunoIdOrderByPuntuacionDesc(idDesayuno);
	}

	private void recalcularPuntuacion(int idDesayuno) {
		List<Review> reviews = this.reviewRepository.findByDesayunoId(idDesayuno);

		double promedioDesayuno = reviews.stream().mapToInt(Review::getPuntuacion).average().orElse(0.0);

		Desayuno desayuno = desayunoRepository.findById(idDesayuno).orElseThrow();
		desayuno.setPuntuacion(promedioDesayuno);
		desayunoRepository.save(desayuno);

		Establecimiento establecimiento = desayuno.getEstablecimiento();

		List<Review> reviewsEstablecimiento = this.reviewRepository.findByDesayunoId(desayuno.getId());

		double promedioEstablecimiento = reviewsEstablecimiento.stream().mapToInt(Review::getPuntuacion).average()
				.orElse(0.0);

		establecimiento.setPuntuacion(promedioEstablecimiento);
		establecimientoRepository.save(establecimiento);
	}

}
