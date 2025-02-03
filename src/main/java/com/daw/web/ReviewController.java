package com.daw.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Review;
import com.daw.service.ReviewService;
import com.daw.service.dtos.ReviewDTO;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	// Endpoint para obtener las reviews de un usuario

	// Endpoint para obtener todas las reviews de un desayuno

	// Endpoint para obtener una review mediante su ID

	@GetMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> findById(@PathVariable int idReview) {
		if (this.reviewService.existsReview(idReview)) {
			return ResponseEntity.ok(this.reviewService.findById(idReview));
		}

		return ResponseEntity.notFound().build();
	}

	// Endpoint para crear una review
	
	@PostMapping
	public ResponseEntity<ReviewDTO> create(@RequestBody Review review) {
		if (!this.usuarioService.existUsuario(review.getIdUsuario())) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<ReviewDTO>(this.reviewService.create(review), HttpStatusCode.CREATED);
	}

	// Endpoint para modificar una review

	// Endpoint para borrar una review

}
