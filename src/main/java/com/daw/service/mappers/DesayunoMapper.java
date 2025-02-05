package com.daw.service.mappers;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Review;
import com.daw.service.dtos.DesayunoDTO;
import com.daw.service.dtos.ReviewDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DesayunoMapper {

    public DesayunoDTO toDTO(Desayuno desayuno){
        DesayunoDTO dto = new DesayunoDTO();

        dto.setId(desayuno.getId());
        dto.setNombre(desayuno.getNombre());
        dto.setPrecio(desayuno.getPrecio());
        dto.setImagen(desayuno.getImagen());
        dto.setPuntuacion(desayuno.getPuntuacion());
        dto.setEstablecimiento(desayuno.getEstablecimiento().getNombre());

        List<ReviewDTO>reviews = new ArrayList<ReviewDTO>();

        for (Review review : desayuno.getReviews()) {
            ReviewDTO reviewDTO = new ReviewDTO();
            // Asumiendo que tienes un método toDTO en Review o configuras ReviewDTO manualmente
            reviewDTO.setId(review.getId());
            reviewDTO.setFecha(review.getFecha());
            reviewDTO.setPuntuacion(review.getPuntuacion());
            // Agrega más campos si es necesario
            reviews.add(reviewDTO);
        }

        dto.setReviews(reviews);

        return dto;
    }
}
