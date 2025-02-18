package com.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.repository.EstablecimientoRepository;
import com.daw.service.dtos.EstablecimientoDTO;
import com.daw.service.mappers.EstablecimientoMapper;


@Service
public class EstablecimientoService {
	
	@Autowired
	private EstablecimientoRepository establecimientoRepository;
	
	@Autowired
	private EstablecimientoMapper mapper;
	
	public List<EstablecimientoDTO> findAll(){
		List<Establecimiento> establecimientos = this.establecimientoRepository.findAll();
		return mapper.toDTOList(establecimientos);
	}
	
	public boolean existsEstablecimiento(int idEstablecimiento){
		return this.establecimientoRepository.existsById(idEstablecimiento);
	}
	
	public Optional<EstablecimientoDTO> findById(int idEstablecimiento){
		Optional<Establecimiento> establecimiento = this.establecimientoRepository.findById(idEstablecimiento);
		return establecimiento.map(mapper::toDTO);
	}
	
	public EstablecimientoDTO create(EstablecimientoDTO dto) {
		Establecimiento establecimiento = mapper.toEntity(dto);
		return mapper.toDTO(this.establecimientoRepository.save(establecimiento));
	}
	
	public EstablecimientoDTO save(EstablecimientoDTO dto) {
		Establecimiento establecimiento = mapper.toEntity(dto);
		
		if(dto.getId() == null){
			throw  new IllegalArgumentException("El establecimiento con ID " + dto.getId() + " es nulo");
		}
		
		return mapper.toDTO(this.establecimientoRepository.save(establecimiento));
	}
	
	public boolean delete(int idEstablecimiento) {
		if(this.establecimientoRepository.existsById(idEstablecimiento)) {
			this.establecimientoRepository.deleteById(idEstablecimiento);
			return true;
		}
		return false;
	}
	
	public List<EstablecimientoDTO> getByUbicacion(String ubicacion) {
		return mapper.toDTOList(this.establecimientoRepository.findByUbicacion(ubicacion));
	}

	public List<EstablecimientoDTO> getOrderedByPuntuacion() {
		return mapper.toDTOList(this.establecimientoRepository.findAllByOrderByPuntuacionDesc());
	}
}

