package com.daw.web;

import java.util.List;
import java.util.Optional;

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

import com.daw.service.EstablecimientoService;
import com.daw.service.dtos.EstablecimientoDTO;

@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {

	@Autowired
	private EstablecimientoService establecimientoService;
	
	@GetMapping
	public ResponseEntity<List<EstablecimientoDTO>> list(){
		return ResponseEntity.ok(this.establecimientoService.findAll());
	}
	
	@GetMapping("/{idEstablecimiento}")
	public ResponseEntity<EstablecimientoDTO> findById(@PathVariable int idEstablecimiento) {
		Optional<EstablecimientoDTO> establecimiento = this.establecimientoService.findById(idEstablecimiento);
		if(establecimiento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(establecimiento.get());
	}
	
	@PostMapping
	public ResponseEntity<EstablecimientoDTO> create(@RequestBody EstablecimientoDTO establecimientoDTO){
		return new ResponseEntity<>(this.establecimientoService.create(establecimientoDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idEstablecimiento}")
	public ResponseEntity<EstablecimientoDTO> update(@PathVariable int idEstablecimiento, @RequestBody EstablecimientoDTO establecimientoDTO){
		if(idEstablecimiento != establecimientoDTO.getId()) {
			return ResponseEntity.badRequest().build();
		}
		else if(!this.establecimientoService.existsEstablecimiento(idEstablecimiento)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.establecimientoService.save(establecimientoDTO));
	}
	
	@DeleteMapping("/{idEstablecimiento}")
	public ResponseEntity<Void> delete(@PathVariable int idEstablecimiento){
		if(this.establecimientoService.delete(idEstablecimiento)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/ubicacion")
	public ResponseEntity<List<EstablecimientoDTO>> findByUbicacion(@RequestParam String ubicacion){
		return ResponseEntity.ok(this.establecimientoService.getByUbicacion(ubicacion));
	}

	@GetMapping("/ordenados")
	public ResponseEntity<List<EstablecimientoDTO>> getOrderedByPuntuacion(){
		return ResponseEntity.ok(this.establecimientoService.getOrderedByPuntuacion());
	}

}