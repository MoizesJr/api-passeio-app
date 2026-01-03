package com.moizesjr.passeio_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moizesjr.passeio_api.exception.RecursoNaoEncontradoException;
import com.moizesjr.passeio_api.model.Usuario;
import com.moizesjr.passeio_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

  private final UsuarioRepository repository;

  public UsuarioService(UsuarioRepository repository) {
    this.repository = repository;
  }

  // Lógica de Login (Mantida conforme sua necessidade inicial)
  public Usuario login(Usuario usuarioGoogle) {
    return repository.findByEmail(usuarioGoogle.getEmail())
        .orElseGet(() -> {
          usuarioGoogle.setPerfil("USER"); // Padrão para novos
          return repository.save(usuarioGoogle);
        });
  }

  public List<Usuario> listarTodos() {
    return repository.findAll();
  }

  public Usuario buscarPorId(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));
  }

  public Usuario criar(Usuario usuario) {
    return repository.save(usuario);
  }

  public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
    Usuario existente = buscarPorId(id);
    usuarioAtualizado.setId(existente.getId());
    return repository.save(usuarioAtualizado);
  }

  public void deletar(Long id) {
    buscarPorId(id); // Garante que existe antes de deletar
    repository.deleteById(id);
  }
}