package com.daw.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Review;
import com.daw.service.dtos.ReviewDTO;

public interface ReviewRepository extends ListCrudRepository<Review, Integer>{
	
	List<Review> findByUsuarioId(int idUsuario);
	List<Review> findByDesayunoId(int idDesayuno);
	List<Review> findAllByOrderByFechaDesc();
	List<Review> findAllByOrderByFechaAsc();
	List<Review> findAllByOrderByPuntuacionDesc();
	List<Review> findByDesayunoOrderByFechaDesc(int idDesayuno);
	List<Review> findByDesayunoOrderByPuntuacionDesc(int idDesayuno);

}
