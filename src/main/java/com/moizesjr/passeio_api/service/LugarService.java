package com.moizesjr.passeio_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moizesjr.passeio_api.exception.RecursoNaoEncontradoException;
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

  public List<Lugar> listarTodos(String nome, String categoriaNome) {
    if (categoriaNome != null && !categoriaNome.equals("-1")) {
      return repository.findByNomeContainingIgnoreCaseAndCategoriaNomeContainingIgnoreCase(nome, categoriaNome);
    }
    // Se não vier nada, retorna a lista completa
    return repository.findByNomeContainingIgnoreCase(nome);
  }

  public Lugar buscarPorId(Long id) {
    return repository.findById(id)
        // Se não achar, LANÇA O ERRO. O Handler montar o JSON.
        .orElseThrow(() -> new RecursoNaoEncontradoException("Lugar não existente com ID: " + id));
  }

  // verificar antes de salvar
  public Lugar atualizar(Long id, Lugar lugarAtualizado) {
    // Verifica o método buscarPorId que já lança erro se falhar
    Lugar existente = buscarPorId(id);

    lugarAtualizado.setId(existente.getId());
    return repository.save(lugarAtualizado);
  }

  public void deletar(Long id) {
    // Se não existir, o buscarPorId já estoura o erro 404
    buscarPorId(id);
    repository.deleteById(id);
  }
}
