package com.real_ia.backend.repository;

import com.real_ia.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByLicenseNumber(String licenseNumber);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByLicenseNumber(String licenseNumber);
}
