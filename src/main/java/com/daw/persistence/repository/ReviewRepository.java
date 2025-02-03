package com.daw.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Review;

public interface ReviewRepository extends ListCrudRepository<Review, Integer>{
	
	List<Review> findAllByOrderByFechaCreacionDesc();
	List<Review> findAllByOrderByFechaCreacionAsc();
	List<Review> findAllByOrderByPuntuacionDesc();
	List<Review> findByDesayunoOrderByFechaCreacionDesc(int idDesayuno);
	List<Review> findByDesayunoOrderByPuntuacionDesc(int idDesayuno);

}
