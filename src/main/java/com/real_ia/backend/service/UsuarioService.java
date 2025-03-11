package com.real_ia.backend.service;

import com.real_ia.backend.model.Usuario;
import com.real_ia.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal no encontrado con id: " + id));
    }

    public Usuario createUsuario(Usuario usuario) {
        // Validar que no existan duplicados
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("Ya existe un animal con ese username");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un animal con ese email");
        }
        if (usuarioRepository.existsByLicenseNumber(usuario.getLicenseNumber())) {
            throw new IllegalArgumentException("Ya existe un animal con ese número de licencia");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = getUsuarioById(id);
        
        // Validar que el nuevo username no exista (si se ha cambiado)
        if (!usuario.getUsername().equals(usuarioDetails.getUsername()) && 
                usuarioRepository.existsByUsername(usuarioDetails.getUsername())) {
            throw new IllegalArgumentException("Ya existe un animal con ese username");
        }
        
        // Validar que el nuevo email no exista (si se ha cambiado)
        if (!usuario.getEmail().equals(usuarioDetails.getEmail()) && 
                usuarioRepository.existsByEmail(usuarioDetails.getEmail())) {
            throw new IllegalArgumentException("Ya existe un animal con ese email");
        }
        
        // Validar que el nuevo licenseNumber no exista (si se ha cambiado)
        if (!usuario.getLicenseNumber().equals(usuarioDetails.getLicenseNumber()) && 
                usuarioRepository.existsByLicenseNumber(usuarioDetails.getLicenseNumber())) {
            throw new IllegalArgumentException("Ya existe un animal con ese número de licencia");
        }

        usuario.setUsername(usuarioDetails.getUsername());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setLicenseNumber(usuarioDetails.getLicenseNumber());
        usuario.setAcmm_especie(usuarioDetails.getAcmm_especie());
        usuario.setAcmm_edad(usuarioDetails.getAcmm_edad());

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Animal no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
