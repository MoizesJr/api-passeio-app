package com.moizesjr.passeio_api.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  // Construtor auxiliar para facilitar o uso com IDs
  public RecursoNaoEncontradoException(Long id) {
    super("Recurso não encontrado com ID: " + id);
  }
}