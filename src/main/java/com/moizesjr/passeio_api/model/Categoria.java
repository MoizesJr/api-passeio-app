package com.moizesjr.passeio_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome não pode estar vazio") // Não aceita null nem ""
  @Size(min = 3, message = "O nome deve ter no mínimo 3 letras")
  private String nome;

  @NotBlank(message = "O descrição não pode estar vazio") // Não aceita null nem ""
  @Column(length = 100)
  private String descricao;
}