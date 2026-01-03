package com.moizesjr.passeio_api.exception;

import java.util.ArrayList;
import java.util.List;

// Herda tudo o que o StandardError já tem (timestamp, status, error...)
public class ValidationError extends StandardError {

  private List<FieldMessage> errors = new ArrayList<>();

  // Método para adicionar erros na lista
  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }
}
