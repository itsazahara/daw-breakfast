package com.daw.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Review;
import com.daw.service.DesayunoService;
import com.daw.service.ReviewService;
import com.daw.service.dtos.ReviewDTO;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private DesayunoService desayunoService;

	// Obtener todas las reviews (terminado)
	@GetMapping
	public ResponseEntity<List<Review>> list() {
		return ResponseEntity.ok(this.reviewService.findAll());
	}

	// Obtener una review por su ID (terminado)
	@GetMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> findById(@PathVariable int idReview) {
		if (this.reviewService.existsReview(idReview)) {
			return ResponseEntity.ok(this.reviewService.findById(idReview));
		}

		return ResponseEntity.notFound().build();
	}

	// Crear una review (terminado)
	@PostMapping
	public ResponseEntity<Review> create(@RequestBody Review review) {
		return ResponseEntity.ok(this.reviewService.create(review));
	}

	// Modificar una review (terminado)
	@PutMapping("/{idReview}")
	public ResponseEntity<Review> update(@PathVariable int idReview, @RequestBody Review review) {
		if (idReview != review.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if (!this.reviewService.existsReview(idReview)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(this.reviewService.update(review));
	}

	// Borrar una review (terminado)
	@DeleteMapping("/{idReview}")
	public ResponseEntity<Review> delete(@PathVariable int idReview) {
		if (this.reviewService.delete(idReview)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	// Obtener reviews ordenadas por fecha (más recientes) (terminado)
	@GetMapping("/ordenadas/fecha/recientes")
	public ResponseEntity<List<Review>> getReviewsOrderByFechaRecientes() {
		return ResponseEntity.ok(reviewService.findAllByOrderByFechaDesc());
	}

	// Obtener reviews ordenadas por fecha (más antiguas) (terminado)
	@GetMapping("/ordenadas/fecha/antiguas")
	public ResponseEntity<List<Review>> getReviewsOrderByFechaAntiguas() {
		return ResponseEntity.ok(reviewService.findAllByOrderByFechaAsc());
	}

	// Obtener reviews ordenadas por puntuación (de mayor a menor) (terminado)
	@GetMapping("/ordenadas/puntuacion")
	public ResponseEntity<List<Review>> getReviewsOrderByPuntuacion() {
		return ResponseEntity.ok(reviewService.findAllByOrderByPuntuacionDesc());
	}

	// Obtener todas las reviews de un desayuno (terminado)
	@GetMapping("/desayuno/{idDesayuno}/reviews")
	public ResponseEntity<List<Review>> getReviewsByDesayuno(@PathVariable int idDesayuno) {
		List<Review> reviews = this.reviewService.getReviewsByDesayuno(idDesayuno);
		return ResponseEntity.ok(reviews);
	}

	// Obtener todas las reviews de un usuario (terminado)
	@GetMapping("/usuario/{idUsuario}/reviews")
	public ResponseEntity<List<Review>> getReviewsByUsuario(@PathVariable int idUsuario) {
		List<Review> reviews = this.reviewService.getReviewByUsuario(idUsuario);
		return ResponseEntity.ok(reviews);
	}

	// Obtener reviews recientes de un desayuno específico (terminado)
	@GetMapping("/desayuno/{idDesayuno}/reviews/recientes")
	public ResponseEntity<List<Review>> getRecentReviewsByDesayuno(@PathVariable int idDesayuno) {
		List<Review> reviewsReciente = this.reviewService.getReviewsRecienteByDesayuno(idDesayuno);
		return ResponseEntity.ok(reviewsReciente);
	}

	// Obtener reviews por puntuación de mayor a menor de un determinado desayuno
	// (terminado)
	@GetMapping("/desayuno/{idDesayuno}/reviews/puntuacion")
	public ResponseEntity<List<Review>> getReviewsByPuntuacionDesc(@PathVariable int idDesayuno) {
		List<Review> reviews = this.reviewService.getReviewsByPuntuacionDesc(idDesayuno);
		return ResponseEntity.ok(reviews);
	}

}
