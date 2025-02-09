package com.daw.service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.entities.Review;
import com.daw.persistence.repository.DesayunoRepository;
import com.daw.persistence.repository.EstablecimientoRepository;
import com.daw.persistence.repository.ReviewRepository;
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

    @Autowired
    private ReviewRepository reviewRepository;


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
        
        Desayuno nuevoDesayuno = this.desayunoRepository.save(desayuno);
        recalcularPuntuacionEstablecimiento(desayuno.getEstablecimiento().getId());
        recalcularPuntuacionDesayuno(desayuno.getId());
        return nuevoDesayuno;
    }

    public Desayuno update(Desayuno desayuno){
        if(desayuno.getId() == null){
            throw new IllegalArgumentException("El desayuno con ID " + desayuno.getId() + " no existe");
        }
        
        Desayuno desayunoActualizado = this.desayunoRepository.save(desayuno);
        recalcularPuntuacionEstablecimiento(desayuno.getEstablecimiento().getId());
        recalcularPuntuacionDesayuno(desayuno.getId());
        return desayunoActualizado;
    }

    public boolean delete(int idDesayuno) {
        Optional<Desayuno> optionalDesayuno = this.desayunoRepository.findById(idDesayuno);
        if (optionalDesayuno.isEmpty()) {
            return false;
        }

        Desayuno desayuno = optionalDesayuno.get();
        int idEstablecimiento = desayuno.getEstablecimiento().getId();
        this.desayunoRepository.deleteById(idDesayuno);
        recalcularPuntuacionDesayuno(idDesayuno);
        recalcularPuntuacionEstablecimiento(idEstablecimiento);
        return true;
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
   
   public void recalcularPuntuacionEstablecimiento(int idEstablecimiento) {
       Optional<Establecimiento> optionalEstablecimiento = establecimientoRepository.findById(idEstablecimiento);
       if (optionalEstablecimiento.isPresent()) {
           Establecimiento establecimiento = optionalEstablecimiento.get();
           List<Desayuno> desayunos = establecimiento.getDesayunos();

           double nuevaPuntuacion = desayunos.stream().mapToDouble(Desayuno::getPuntuacion).average()
                   .orElse(0.0);

           establecimiento.setPuntuacion(nuevaPuntuacion);
           establecimientoRepository.save(establecimiento);
       }
   }


    public void recalcularPuntuacionDesayuno(int idDesayuno) {
        Desayuno desayuno = this.desayunoRepository.findById(idDesayuno).get();

        List<Review> reviewDesayuno = this.reviewRepository.findByDesayunoId(idDesayuno);
        double promedioDesayuno = reviewDesayuno.stream().mapToDouble(Review::getPuntuacion).average().orElse(0.0);
        desayuno.setPuntuacion(promedioDesayuno);
        this.desayunoRepository.save(desayuno);
    }

}

