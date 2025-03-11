package com.real_ia.backend.controller;

import com.real_ia.backend.dto.RentalRequest;
import com.real_ia.backend.model.Alquiler;
import com.real_ia.backend.service.AlquilerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @PostMapping
    public ResponseEntity<?> createAlquiler(@RequestBody RentalRequest rentalRequest) {
        try {
            Alquiler alquiler = alquilerService.createAlquiler(rentalRequest);
            return new ResponseEntity<>(alquiler, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnAlquiler(@PathVariable Long id) {
        try {
            Alquiler alquiler = alquilerService.returnAlquiler(id);
            return new ResponseEntity<>(alquiler, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}/rentals")
    public ResponseEntity<?> getAlquileresByUsuarioId(@PathVariable Long userId) {
        try {
            List<Alquiler> alquileres = alquilerService.getAlquileresByUsuarioId(userId);
            return new ResponseEntity<>(alquileres, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
