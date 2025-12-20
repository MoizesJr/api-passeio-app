package com.moizesjr.passeio_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.moizesjr.passeio_api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*") // <--- LIBERA O ACESSO
public class CategoriaController {

  @Autowired
  private CategoriaRepository repository;

  @GetMapping
  public List<Categoria> listar() {
    return repository.findAll();
  }

  // MÉTODO NOVO: Busca apenas 1 lugar pelo ID
  @GetMapping("/{id}")
  public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
    return repository.findById(id)
        .map(categoria -> ResponseEntity.ok(categoria)) // Se achar, devolve o lugar (Status 200)
        .orElse(ResponseEntity.notFound().build()); // Se não achar, devolve erro 404
  }

  @PostMapping
  public Categoria criar(@RequestBody Categoria categoria) {
    return repository.save(categoria);
  }

  @PutMapping("/{id}")
  public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
    // Garante que estamos atualizando o ID que veio na URL
    categoria.setId(id);
    return repository.save(categoria);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    repository.deleteById(id);
  }
}