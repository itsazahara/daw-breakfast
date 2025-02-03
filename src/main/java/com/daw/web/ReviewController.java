package com.daw.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Review;
import com.daw.persistence.repository.DesayunoRepository;
import com.daw.persistence.repository.ReviewRepository;
import com.daw.service.DesayunoService;
import com.daw.service.ReviewService;
import com.daw.service.UsuarioService;
import com.daw.service.dtos.ReviewDTO;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DesayunoService desayunoService;

	// Obtener una review por su ID (terminado)
	@GetMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> findById(@PathVariable int idReview){
		if (this.reviewService.existsReview(idReview)) {
			return ResponseEntity.ok(this.reviewService.findById(idReview));
		}

		return ResponseEntity.notFound().build();
	}

	/*// Obtener todas las reviews de un usuario
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Review>> getReviewsByUsuario(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		return usuario.map(u -> ResponseEntity.ok(reviewRepository.findByUsuario(u)))
				.orElse(ResponseEntity.notFound().build());
	}

	// ✅ Obtener todas las reviews de un desayuno
	@GetMapping("/desayuno/{desayunoId}")
	public ResponseEntity<List<Review>> getReviewsByDesayuno(@PathVariable Long desayunoId) {
		Optional<Desayuno> desayuno = desayunoRepository.findById(desayunoId);
		return desayuno.map(d -> ResponseEntity.ok(reviewRepository.findByDesayuno(d)))
				.orElse(ResponseEntity.notFound().build());
	}

	// ✅ Crear una review
	@PostMapping
	public ResponseEntity<Review> createReview(@RequestBody Review review) {
		if (review.getPuntuacion() < 0 || review.getPuntuacion() > 5) {
			return ResponseEntity.badRequest().body(null);
		}
		Review nuevaReview = reviewRepository.save(review);
		return ResponseEntity.ok(nuevaReview);
	}

	// ✅ Modificar una review
	@PutMapping("/{id}")
	public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
		Optional<Review> reviewOptional = reviewRepository.findById(id);
		if (reviewOptional.isPresent()) {
			Review review = reviewOptional.get();
			review.setPuntuacion(reviewDetails.getPuntuacion());
			review.setComentario(reviewDetails.getComentario());
			Review updatedReview = reviewRepository.save(review);
			return ResponseEntity.ok(updatedReview);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// ✅ Borrar una review
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable int id) {
		if (ReviewRepository.existsById(id)) {
			ReviewRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Obtener reviews ordenadas por fecha (más recientes)
	@GetMapping("/ordenadas/fecha/recientes")
	public List<Review> getReviewsOrderByFechaRecientes() {
		return ReviewRepository.findAllByOrderByFechaCreacionDesc();
	}

	// ✅ Obtener reviews ordenadas por fecha (más antiguas)
	@GetMapping("/ordenadas/fecha/antiguas")
	public List<Review> getReviewsOrderByFechaAntiguas() {
		return reviewRepository.findAllByOrderByFechaCreacionAsc();
	}

	// ✅ Obtener reviews ordenadas por puntuación (de mayor a menor)
	@GetMapping("/ordenadas/puntuacion")
	public List<Review> getReviewsOrderByPuntuacion() {
		return reviewRepository.findAllByOrderByPuntuacionDesc();
	}

	// Obtener reviews recientes de un desayuno específico
	@GetMapping("/desayuno/{desayunoId}/recientes")
	public ResponseEntity<List<Review>> getReviewsRecientesByDesayuno(@PathVariable Long desayunoId) {
		Optional<Desayuno> desayuno = desayunoRepository.findById(desayunoId);
		return desayuno.map(d -> ResponseEntity.ok(reviewRepository.findByDesayunoOrderByFechaCreacionDesc(d)))
				.orElse(ResponseEntity.notFound().build());
	}

	 Obtener reviews por puntuación de mayor a menor de un determinado desayuno
	@GetMapping("/desayuno/{idDesayuno}/puntuacion")
	public ResponseEntity<List<Review>> getReviewsByPuntuacionByDesayuno(@PathVariable int idDesayuno) {
		Optional<Desayuno> desayuno = DesayunoRepository.findById(idDesayuno);
		return desayuno.map(d -> ResponseEntity.ok(reviewRepository.findByDesayunoOrderByPuntuacionDesc(d)))
				.orElse(ResponseEntity.notFound().build());
	}*/

}
