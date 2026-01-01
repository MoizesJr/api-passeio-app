package com.moizesjr.passeio_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moizesjr.passeio_api.exception.RecursoNaoEncontradoException;
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

  public Categoria buscarPorId(Long id) {
    return repository.findById(id)
        // Se não achar, LANÇA O ERRO. O Handler montar o JSON.
        .orElseThrow(() -> new RecursoNaoEncontradoException("Lugar não existente com ID: " + id));
  }

  public Categoria atualizar(Long id, Categoria categoriaAtualizado) {
    // Verifica o método buscarPorId que já lança erro se falhar
    Categoria existente = buscarPorId(id);

    categoriaAtualizado.setId(existente.getId());
    return repository.save(categoriaAtualizado);
  }

  public void deletar(Long id) {
    // Se não existir, o buscarPorId já estoura o erro 404
    buscarPorId(id);
    repository.deleteById(id);
  }

}
