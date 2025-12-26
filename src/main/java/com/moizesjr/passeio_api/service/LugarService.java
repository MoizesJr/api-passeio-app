package com.moizesjr.passeio_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moizesjr.passeio_api.model.Lugar;
import com.moizesjr.passeio_api.repository.LugarRepository;

@Service
public class LugarService {

  private final LugarRepository repository;

  public LugarService(LugarRepository repository) {
    this.repository = repository;
  }

  public Lugar criar(Lugar lugar) {
    return repository.save(lugar);
  }

  public List<Lugar> listarTodos(String categoria) {
    // Se vier algo escrito na categoria(EXE: /lugares?categoria=praia), usa o
    // filtro novo
    if (categoria != null && !categoria.isEmpty()) {
      return repository.findByCategoriaContainingIgnoreCase(categoria);
    }
    // Se não vier nada, retorna a lista completa
    return repository.findAll();
  }

  public Optional<Lugar> buscarPorId(Long id) {
    return repository.findById(id);
  }

  // verificar antes de salvar
  public Optional<Lugar> atualizar(Long id, Lugar lugarAtualizado) {
    if (!repository.existsById(id)) {
      lugarAtualizado.setId(id);
      return Optional.of(repository.save(lugarAtualizado));
    }
    // Se não existir, retornamos vazio (404)
    return Optional.empty();
  }

  public boolean deletar(Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true; // confirmar que deletou
    }
    return false; // não encontrou para deletar
  }
}
