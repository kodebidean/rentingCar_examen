package com.real_ia.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "zapatos")
public class Coche {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, name = "plate_number")
    private String plateNumber;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private Boolean available = true;
    
    // Campo adicional con apellido_*
    @Column(name = "mugueta_talla")
    private Integer mugueta_talla;
    
    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Alquiler> alquileres = new ArrayList<>();
}
