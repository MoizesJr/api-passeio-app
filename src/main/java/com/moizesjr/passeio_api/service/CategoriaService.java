package com.moizesjr.passeio_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moizesjr.passeio_api.model.Categoria;
import com.moizesjr.passeio_api.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private final CategoriaRepository repository;

  public CategoriaService(CategoriaRepository repository) {
    this.repository = repository;
  }

  public Categoria criar(Categoria categoria) {
    return repository.save(categoria);
  }

  public List<Categoria> listarTodos() {
    return repository.findAll();
  }

  public Optional<Categoria> buscarPorId(Long id) {
    return repository.findById(id);
  }

  public Optional<Categoria> atualizar(Long id, Categoria categoria) {
    if (repository.existsById(id)) {
      categoria.setId(id);
      return Optional.of(repository.save(categoria));
    }
    return Optional.empty();
  }

  public boolean deletar(Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

}
