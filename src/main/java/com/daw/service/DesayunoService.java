package com.daw.service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.repository.DesayunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesayunoService {

    @Autowired
    private DesayunoRepository desayunoRepository;


    public List<Desayuno> listAll(){
        return this.desayunoRepository.findAll();
    }

    public Optional<Desayuno> findById(int idDesayuno){
        return this.desayunoRepository.findById(idDesayuno);
    }

    public boolean existDesayuno(int idDesayuno){
        return this.desayunoRepository.existsById(idDesayuno);
    }

    public Desayuno create(Desayuno desayuno){
        desayuno.setPuntuacion(0.0);
        desayuno.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuGbZQa0K6RcMzEEnIcozJKd6Cu8wGBk8ThA&s");
        return this.desayunoRepository.save(desayuno);
    }

    public Desayuno update(Desayuno desayuno){
        return this.desayunoRepository.save(desayuno);
    }

    public boolean delete(int idDesayuno){
        boolean result = false;

        if(this.desayunoRepository.existsById(idDesayuno)){
            this.desayunoRepository.deleteById(idDesayuno);
            result = true;
        }

        return result;
    }
}
