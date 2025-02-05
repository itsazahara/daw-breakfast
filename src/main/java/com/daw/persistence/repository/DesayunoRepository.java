package com.daw.persistence.repository;

import com.daw.persistence.entities.Desayuno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesayunoRepository extends JpaRepository<Desayuno,Integer> {

    List<Desayuno> findByDesayunoOrderByPuntuacionAsc();
    List<Desayuno> findByDesayunoOrderByPuntuacionEstablecimientoContaining();

}
