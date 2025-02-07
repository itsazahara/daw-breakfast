package com.daw.service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.repository.DesayunoRepository;
import com.daw.persistence.repository.EstablecimientoRepository;
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

    @Autowired
    private EstablecimientoRepository establecimientoRepository;


    public List<DesayunoDTO> listAll(){
        return this.desayunoRepository.findAll().stream().map(desayunoMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<DesayunoDTO> findById(int idDesayuno){
        return this.desayunoRepository.findById(idDesayuno).map(desayunoMapper::toDTO);
    }

    public boolean existDesayuno(int idDesayuno){
        return this.desayunoRepository.existsById(idDesayuno);
    }

    public Desayuno create(Desayuno desayuno) {

        if (desayuno.getImagen() == null || desayuno.getPuntuacion() == null) {
            desayuno.setPuntuacion(0.0);
            desayuno.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuGbZQa0K6RcMzEEnIcozJKd6Cu8wGBk8ThA&s");
        }

        if (desayuno.getEstablecimiento() != null && desayuno.getEstablecimiento().getId() != null) {
            Establecimiento establecimiento = establecimientoRepository
                    .findById(desayuno.getEstablecimiento().getId())
                    .orElseThrow(() -> new IllegalArgumentException("El establecimiento con ID " + desayuno.getEstablecimiento().getId() + " no existe."));

            desayuno.setEstablecimiento(establecimiento);


        }
        return this.desayunoRepository.save(desayuno);
    }

    public Desayuno update(Desayuno desayuno){
        if(desayuno.getId() == null){
            throw new IllegalArgumentException("El desayuno con ID " + desayuno.getId() + " no existe");
        }
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
        return this.desayunoRepository.findAllByOrderByPuntuacionDesc();
    }

    public List<Desayuno> findByPuntuacionDesayunoEstablecimiento(int idEstablecimiento){
        return this.desayunoRepository.findAllByEstablecimientoIdOrderByPuntuacionDesc(idEstablecimiento);
    }

    public List<Desayuno> findByDesayunoOrderByPrecio(int idEstablecimiento){
        return this.desayunoRepository.findAllByEstablecimientoIdOrderByPrecioAsc(idEstablecimiento);
    }

   public List<Desayuno> findByDesayunosEstablecimiento(int idEstablecimiento){
        return this.desayunoRepository.findAllByEstablecimientoId(idEstablecimiento);
    }
}
