package com.real_ia.backend.repository;

import com.real_ia.backend.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long> {
    Optional<Coche> findByPlateNumber(String plateNumber);
    List<Coche> findByBrandContainingIgnoreCase(String brand);
    List<Coche> findByModelContainingIgnoreCase(String model);
    List<Coche> findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(String brand, String model);
    List<Coche> findByAvailable(Boolean available);
    boolean existsByPlateNumber(String plateNumber);
}
