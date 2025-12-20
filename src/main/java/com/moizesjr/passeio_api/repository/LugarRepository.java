package com.moizesjr.passeio_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moizesjr.passeio_api.model.Lugar; // <--- Não esqueça de importar o List

public interface LugarRepository extends JpaRepository<Lugar, Long> {

  // O Spring entende: "Busque por Categoria, ignorando maiúsculas/minúsculas e
  // aceitando partes do nome"
  List<Lugar> findByCategoriaContainingIgnoreCase(String categoria);
}