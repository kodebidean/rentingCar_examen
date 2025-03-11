package com.real_ia.backend.repository;

import com.real_ia.backend.model.Alquiler;
import com.real_ia.backend.model.RentalStatus;
import com.real_ia.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByUsuario(Usuario usuario);
    List<Alquiler> findByUsuarioAndStatus(Usuario usuario, RentalStatus status);
}
