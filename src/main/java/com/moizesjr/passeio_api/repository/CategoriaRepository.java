package com.moizesjr.passeio_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moizesjr.passeio_api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}