package com.moizesjr.passeio_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moizesjr.passeio_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  // Busca para achar pelo email
  Optional<Usuario> findByEmail(String email);
}