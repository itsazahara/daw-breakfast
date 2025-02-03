package com.daw.persistence.repository;

import com.daw.persistence.entities.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Integer> {
    List<Establecimiento> findByUbicacion(String ubicacion);
    List<Establecimiento> findAllByOrderByPuntuacionDesc();
}

