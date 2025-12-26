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
  public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
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
    return service.buscarPorId(id)
        .map(categoria -> ResponseEntity.ok(categoria)) // Se achar, devolve o lugar (Status 200)
        .orElse(ResponseEntity.notFound().build()); // Se não achar, devolve erro 404
  }

  // 4º ATUALIZAR CATEGORIA
  @PutMapping("/{id}")
  public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
    return service.atualizar(id, categoria)
        .map(categoriaAtualizada -> ResponseEntity.ok(categoriaAtualizada)) // Se achar, devolve o lugar (Status 200)
        .orElse(ResponseEntity.notFound().build()); // Se não achar, devolve erro 404
  }

  // 5º DELETAR CATEGORIA
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (service.deletar(id)) {
      // Se deletou com sucesso: retorna 204 No Content (padrão para delete)
      return ResponseEntity.noContent().build();
    }
    // Se retornou false (não achou o id): 404
    return ResponseEntity.notFound().build();
  }
}