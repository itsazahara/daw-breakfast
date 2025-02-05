package com.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.repository.EstablecimientoRepository;

@Service
public class EstablecimientoService {
	
	@Autowired
	private EstablecimientoRepository establecimientoRepository;
	
	// CRUDs
	public List<Establecimiento> findAll(){
		return this.establecimientoRepository.findAll();
	}
	
	public boolean existsEstablecimiento(int idEstablecimiento){
		return this.establecimientoRepository.existsById(idEstablecimiento);
	}
	
	public Optional<Establecimiento> findById(int idEstablecimiento){
		return this.establecimientoRepository.findById(idEstablecimiento);
	}
	
	public Establecimiento create(Establecimiento establecimiento) {
		return this.establecimientoRepository.save(establecimiento);
	}
	
	public Establecimiento save(Establecimiento establecimiento) {
		return this.establecimientoRepository.save(establecimiento);
	}
	
	public boolean delete(int idEstablecimiento) {
		boolean result = false;
		
		if(this.establecimientoRepository.existsById(idEstablecimiento)) {
			this.establecimientoRepository.deleteById(idEstablecimiento);
			result = true;
		}
		
		return result;
	}
	
	public List<Establecimiento> getByUbicacion(String ubicacion) {
		return this.establecimientoRepository.findByUbicacion(ubicacion);
	}
}


