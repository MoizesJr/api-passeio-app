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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moizesjr.passeio_api.model.Lugar;
import com.moizesjr.passeio_api.repository.LugarRepository;

@RestController
@RequestMapping("/lugares")
@CrossOrigin(origins = "*") // <--- LIBERA O ACESSO PARA O FRONT
public class LugarController {

  @Autowired
  private LugarRepository repository;

  @GetMapping
  public List<Lugar> listar(@RequestParam(required = false) String categoria) {
    // Se vier algo escrito na categoria, usa o filtro novo
    if (categoria != null && !categoria.isEmpty()) {
      return repository.findByCategoriaContainingIgnoreCase(categoria);
    }
    // Se não vier nada, retorna a lista completa
    return repository.findAll();
  }

  // MÉTODO NOVO: Busca apenas 1 lugar pelo ID
  @GetMapping("/{id}")
  public ResponseEntity<Lugar> buscarPorId(@PathVariable Long id) {
    return repository.findById(id)
        .map(lugar -> ResponseEntity.ok(lugar)) // Se achar, devolve o lugar (Status 200)
        .orElse(ResponseEntity.notFound().build()); // Se não achar, devolve erro 404
  }

  @PostMapping
  public Lugar criar(@RequestBody Lugar lugar) {
    return repository.save(lugar);
  }

  @PutMapping("/{id}")
  public Lugar atualizar(@PathVariable Long id, @RequestBody Lugar lugar) {
    // Garante que estamos atualizando o ID que veio na URL
    lugar.setId(id);
    return repository.save(lugar);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    repository.deleteById(id);
  }
}