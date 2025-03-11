package com.real_ia.backend.service;

import com.real_ia.backend.model.Coche;
import com.real_ia.backend.repository.CocheRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {

    @Autowired
    private CocheRepository cocheRepository;

    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    public Coche getCocheById(Long id) {
        return cocheRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zapato no encontrado con id: " + id));
    }

    public Coche createCoche(Coche coche) {
        // Validar que no existan duplicados
        if (cocheRepository.existsByPlateNumber(coche.getPlateNumber())) {
            throw new IllegalArgumentException("Ya existe un zapato con ese número de placa");
        }

        return cocheRepository.save(coche);
    }

    public Coche updateCoche(Long id, Coche cocheDetails) {
        Coche coche = getCocheById(id);
        
        // Validar que el nuevo plateNumber no exista (si se ha cambiado)
        if (!coche.getPlateNumber().equals(cocheDetails.getPlateNumber()) && 
                cocheRepository.existsByPlateNumber(cocheDetails.getPlateNumber())) {
            throw new IllegalArgumentException("Ya existe un zapato con ese número de placa");
        }

        coche.setPlateNumber(cocheDetails.getPlateNumber());
        coche.setBrand(cocheDetails.getBrand());
        coche.setModel(cocheDetails.getModel());
        coche.setYear(cocheDetails.getYear());
        coche.setAvailable(cocheDetails.getAvailable());
        coche.setMugueta_talla(cocheDetails.getMugueta_talla());

        return cocheRepository.save(coche);
    }

    public void deleteCoche(Long id) {
        if (!cocheRepository.existsById(id)) {
            throw new EntityNotFoundException("Zapato no encontrado con id: " + id);
        }
        cocheRepository.deleteById(id);
    }

    public List<Coche> searchCoches(String brand, String model) {
        if (brand != null && model != null) {
            return cocheRepository.findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(brand, model);
        } else if (brand != null) {
            return cocheRepository.findByBrandContainingIgnoreCase(brand);
        } else if (model != null) {
            return cocheRepository.findByModelContainingIgnoreCase(model);
        } else {
            return cocheRepository.findAll();
        }
    }

    public List<Coche> getAvailableCoches() {
        return cocheRepository.findByAvailable(true);
    }
}
