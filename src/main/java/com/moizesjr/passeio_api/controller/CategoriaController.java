package com.moizesjr.passeio_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moizesjr.passeio_api.model.Categoria;
import com.moizesjr.passeio_api.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*") // <--- LIBERA O ACESSO
public class CategoriaController {

  private final CategoriaService service;

  public CategoriaController(CategoriaService service) {
    this.service = service;
  }

  // 1º CRIAR CATEGORIA
  @PostMapping
  public ResponseEntity<Categoria> criar(@RequestBody @Valid Categoria categoria) {
    Categoria categoriaCriada = service.criar(categoria);
    return ResponseEntity.status(201).body(categoriaCriada); // 201 Created
  }

  // 2º LISTAR CATEGORIAS
  @GetMapping
  public ResponseEntity<List<Categoria>> listar() {
    return ResponseEntity.ok(service.listarTodos());
  }

  // 3º BUSCAR CATEGORIA POR ID
  @GetMapping("/{id}")
  public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
    // Se der erro, o Service lança exceção e o código nem passa daqui
    Categoria categoria = service.buscarPorId(id);
    return ResponseEntity.ok(categoria);
  }

  // 4º ATUALIZAR CATEGORIA
  @PutMapping("/{id}")
  public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody @Valid Categoria categoria) {
    Categoria atualizado = service.atualizar(id, categoria);
    return ResponseEntity.ok(atualizado);
  }

  // 5º DELETAR CATEGORIA
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
  }
}