package com.daw.web;

import com.daw.persistence.entities.Desayuno;
import com.daw.service.DesayunoService;
import com.daw.service.dtos.DesayunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desayuno")
public class DesayunoController {


    @Autowired
    private DesayunoService desayunoService;

    @GetMapping
    public ResponseEntity<List<DesayunoDTO>> findAll(){
        return ResponseEntity.ok((List<DesayunoDTO>) this.desayunoService.listAll());
    }

    @GetMapping("/{idDesayuno}")
    public ResponseEntity<Optional<DesayunoDTO>> findDesayunoById(@PathVariable int idDesayuno){

        if(this.desayunoService.existDesayuno(idDesayuno)){
            return ResponseEntity.ok(this.desayunoService.findById(idDesayuno));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Desayuno> createDesayuno(@RequestBody Desayuno desayuno){
        return ResponseEntity.ok(this.desayunoService.create(desayuno));
    }

    @PutMapping("/{idDesayuno}")
    public ResponseEntity<Desayuno> updateDesayuno(@RequestBody Desayuno desayuno, @PathVariable int idDesayuno){
        if(this.desayunoService.existDesayuno(idDesayuno)){
            return ResponseEntity.ok(this.desayunoService.update(desayuno));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idDesayuno}")
    public ResponseEntity<Desayuno> deleteDesayuno(@PathVariable int idDesayuno){
        if(this.desayunoService.delete(idDesayuno)){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/puntos")
    public ResponseEntity<List<Desayuno>> getDesayunosOrderByPuntuacion(){
        return ResponseEntity.ok(this.desayunoService.findByDesayunoPuntuacion());
    }

    @GetMapping("/puntos/{idEstablecimiento}")
    public ResponseEntity<List<Desayuno>> getDesayunosOrderByPuntuacionEstablecimiento(@PathVariable int idEstablecimiento){
        return ResponseEntity.ok(this.desayunoService.findByPuntuacionDesayunoEstablecimiento(idEstablecimiento));
    }

    @GetMapping("/precios/establecimientos/{idEstablecimiento}")
    public ResponseEntity<List<Desayuno>> getDesayunosOrderByPrecioEstablecimiento(@PathVariable int idEstablecimiento){
        return ResponseEntity.ok(this.desayunoService.findByDesayunoOrderByPrecio(idEstablecimiento));
    }

    @GetMapping("/establecimiento/{idEstablecimiento}")
    public ResponseEntity<List<Desayuno>> getDesayunoByEstablecimiento(@PathVariable int idEstablecimiento){
        return ResponseEntity.ok(this.desayunoService.findByDesayunosEstablecimiento(idEstablecimiento));
    }
    
    @PutMapping("/{idDesayuno}/imagen")
    public ResponseEntity<Desayuno> updateImagen(@RequestBody Desayuno desayuno, @PathVariable int idDesayuno, @RequestParam String imagen){
    	if(this.desayunoService.existDesayuno(idDesayuno)) {
    		return ResponseEntity.ok(this.desayunoService.updateDesayuno(idDesayuno, desayuno, imagen));
    	}
    	
    	return ResponseEntity.notFound().build();
    }
}
