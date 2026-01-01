package com.moizesjr.passeio_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lugar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome não pode estar vazio") // Não aceita null nem vazio
  @Size(min = 3, message = "O nome deve ter no mínimo 3 letras")
  private String nome;

  @ManyToOne // Muitos Lugares pertencem a Uma Categoria
  @JoinColumn(name = "categoria_id") // Cria uma chave estrangeira no banco
  private Categoria categoria;

  private String localizacao;

  @Column(length = 1000) // Aumenta o tamanho para aceitar URLs grandes
  private String urlFoto;

  private Double avaliacao;
}