package com.real_ia.backend.controller;

import com.real_ia.backend.model.Coche;
import com.real_ia.backend.service.CocheService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zapatos")
public class CocheController {

    @Autowired
    private CocheService cocheService;

    @GetMapping
    public ResponseEntity<List<Coche>> searchCoches(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model) {
        List<Coche> coches = cocheService.searchCoches(brand, model);
        return new ResponseEntity<>(coches, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Coche>> getAvailableCoches() {
        List<Coche> coches = cocheService.getAvailableCoches();
        return new ResponseEntity<>(coches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable Long id) {
        try {
            Coche coche = cocheService.getCocheById(id);
            return new ResponseEntity<>(coche, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCoche(@RequestBody Coche coche) {
        try {
            Coche newCoche = cocheService.createCoche(coche);
            return new ResponseEntity<>(newCoche, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoche(@PathVariable Long id, @RequestBody Coche coche) {
        try {
            Coche updatedCoche = cocheService.updateCoche(id, coche);
            return new ResponseEntity<>(updatedCoche, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable Long id) {
        try {
            cocheService.deleteCoche(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
