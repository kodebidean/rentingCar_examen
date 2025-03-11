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
@Table(name = "animales")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false, name = "license_number")
    private String licenseNumber;
    
    // Campos adicionales con iniciales acmm_*
    @Column(name = "acmm_especie")
    private String acmm_especie;
    
    @Column(name = "acmm_edad")
    private Integer acmm_edad;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Alquiler> alquileres = new ArrayList<>();
}
