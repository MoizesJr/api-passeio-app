package com.moizesjr.passeio_api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FieldMessage {
  private String fieldName; // Ex: "nome"
  private String message; // Ex: "O nome é obrigatório"
}
