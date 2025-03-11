package com.real_ia.backend.service;

import com.real_ia.backend.dto.RentalRequest;
import com.real_ia.backend.model.Alquiler;
import com.real_ia.backend.model.Coche;
import com.real_ia.backend.model.RentalStatus;
import com.real_ia.backend.model.Usuario;
import com.real_ia.backend.repository.AlquilerRepository;
import com.real_ia.backend.repository.CocheRepository;
import com.real_ia.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CocheRepository cocheRepository;

    public List<Alquiler> getAlquileresByUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Animal no encontrado con id: " + usuarioId));
        return alquilerRepository.findByUsuario(usuario);
    }

    @Transactional
    public Alquiler createAlquiler(RentalRequest rentalRequest) {
        Usuario usuario = usuarioRepository.findById(rentalRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Animal no encontrado con id: " + rentalRequest.getUserId()));

        Coche coche = cocheRepository.findById(rentalRequest.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Zapato no encontrado con id: " + rentalRequest.getCarId()));

        if (!coche.getAvailable()) {
            throw new IllegalStateException("El zapato no está disponible para alquilar");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setUsuario(usuario);
        alquiler.setCoche(coche);
        alquiler.setRentalDate(LocalDateTime.now());
        alquiler.setStatus(RentalStatus.ACTIVE);
        alquiler.setAndrey_precioDia(rentalRequest.getAndrey_precioDia());
        alquiler.setAndrey_comentario(rentalRequest.getAndrey_comentario());

        // Actualizar disponibilidad del coche
        coche.setAvailable(false);
        cocheRepository.save(coche);

        return alquilerRepository.save(alquiler);
    }

    @Transactional
    public Alquiler returnAlquiler(Long alquilerId) {
        Alquiler alquiler = alquilerRepository.findById(alquilerId)
                .orElseThrow(() -> new EntityNotFoundException("Alquiler no encontrado con id: " + alquilerId));

        if (alquiler.getStatus() == RentalStatus.COMPLETED) {
            throw new IllegalStateException("El zapato ya ha sido devuelto");
        }

        alquiler.setReturnDate(LocalDateTime.now());
        alquiler.setStatus(RentalStatus.COMPLETED);

        // Actualizar disponibilidad del coche
        Coche coche = alquiler.getCoche();
        coche.setAvailable(true);
        cocheRepository.save(coche);

        return alquilerRepository.save(alquiler);
    }
}
