package com.daw.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daw.persistence.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByUsuarioId(int idUsuario);
	List<Review> findByDesayunoId(int idDesayuno);
	List<Review> findAllByOrderByFechaDesc();
	List<Review> findAllByOrderByFechaAsc();
	List<Review> findAllByOrderByPuntuacionDesc();
	List<Review> findByDesayunoIdOrderByFechaDesc(int idDesayuno);
	List<Review> findByDesayunoIdOrderByPuntuacionDesc(int idDesayuno);

}
