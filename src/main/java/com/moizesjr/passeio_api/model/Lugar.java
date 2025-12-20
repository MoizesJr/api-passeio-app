package com.moizesjr.passeio_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Lugar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String categoria;
  private String localizacao;

  @Column(length = 1000) // Aumenta o tamanho para aceitar URLs grandes
  private String urlFoto;

  private Double avaliacao;
}