package com.daw.service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.repository.DesayunoRepository;
import com.daw.service.dtos.DesayunoDTO;
import com.daw.service.mappers.DesayunoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesayunoService {

    @Autowired
    private DesayunoRepository desayunoRepository;

    @Autowired
    private DesayunoMapper desayunoMapper;


    public List<DesayunoDTO> listAll(){
        return this.desayunoRepository.findAll().stream().map(desayunoMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<DesayunoDTO> findById(int idDesayuno){
        return this.desayunoRepository.findById(idDesayuno).map(desayunoMapper::toDTO);
    }

    public boolean existDesayuno(int idDesayuno){
        return this.desayunoRepository.existsById(idDesayuno);
    }

    public Desayuno create(Desayuno desayuno){
        desayuno.setPuntuacion(0.0);
        if(desayuno.getImagen() == null){
            desayuno.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuGbZQa0K6RcMzEEnIcozJKd6Cu8wGBk8ThA&s");
        }

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



    public List<Desayuno> findByDesayunoPuntuacion(){
        return this.desayunoRepository.findByDesayunoOrderByPuntuacionAsc();
    }
}
