package com.moizesjr.passeio_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moizesjr.passeio_api.model.Lugar;

public interface LugarRepository extends JpaRepository<Lugar, Long> {

  // Este método busca Lugares que contenham o nome informado
  // E que pertençam a uma Categoria com o nome informado.
  List<Lugar> findByNomeContainingIgnoreCaseAndCategoriaNomeContainingIgnoreCase(String nome, String categoriaNome);

  // Caso queira filtrar só por nome quando a categoria for "Todas"
  List<Lugar> findByNomeContainingIgnoreCase(String nome);
}