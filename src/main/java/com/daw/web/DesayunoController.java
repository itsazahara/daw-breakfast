package com.daw.web;

import com.daw.persistence.entities.Desayuno;
import com.daw.service.DesayunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desayuno")
public class DesayunoController {


    @Autowired
    private DesayunoService desayunoService;

    @GetMapping
    public ResponseEntity<List<Desayuno>> findAll(){
        return ResponseEntity.ok((List<Desayuno>) this.desayunoService.listAll());
    }

    @GetMapping("/{idDesayuno}")
    public ResponseEntity<Optional<Desayuno>> findDesayunoById(@PathVariable int idDesayuno){

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
}
