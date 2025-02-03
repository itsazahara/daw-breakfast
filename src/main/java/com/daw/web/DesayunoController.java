package com.daw.web;

import com.daw.persistence.entities.Desayuno;
import com.daw.service.DesayunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<Optional<Desayuno>> findDesayunoById(@PathVariable int idDesayuno){

        if(this.desayunoService.existDesayuno(idDesayuno)){
            return ResponseEntity.ok(this.desayunoService.findById(idDesayuno));
        }
        return ResponseEntity.notFound().build();

    }
}
