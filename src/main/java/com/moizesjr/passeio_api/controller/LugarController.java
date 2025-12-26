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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moizesjr.passeio_api.model.Lugar;
import com.moizesjr.passeio_api.service.LugarService;

@RestController
@RequestMapping("/lugares")
@CrossOrigin(origins = "*") // <--- LIBERA O ACESSO PARA O FRONT
public class LugarController {

  private final LugarService service;

  public LugarController(LugarService service) {
    this.service = service;
  }

  // 1º CRIAR LUGAR
  @PostMapping
  public ResponseEntity<Lugar> criar(@RequestBody Lugar lugar) {
    Lugar lugarSalvo = service.criar(lugar);
    return ResponseEntity.status(201).body(lugarSalvo); // 201 Created
  }

  // 2º LISTAR LUGARES
  @GetMapping
  public ResponseEntity<List<Lugar>> listar(@RequestParam(required = false) String categoria) {
    return ResponseEntity.ok(service.listarTodos(categoria)); // 200 OK
  }

  // 3º BUSCAR LUGAR POR ID
  @GetMapping("/{id}")
  public ResponseEntity<Lugar> buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id)
        .map(lugar -> ResponseEntity.ok(lugar)) // Se tiver lugar -> 200 OK
        .orElse(ResponseEntity.notFound().build()); // Se estiver vazio -> 404 Not Found;
  }

  // 4º ATUALIZAR LUGAR
  @PutMapping("/{id}")
  public ResponseEntity<Lugar> atualizar(@PathVariable Long id, @RequestBody Lugar lugar) {
    return service.atualizar(id, lugar)
        // .map: Se a caixa tiver algo (o lugar atualizado): 200 OK
        .map(lugarAtualizado -> ResponseEntity.ok(lugarAtualizado))
        // .orElse: Se a caixa estiver vazia (não achou o ID): 404 Not Found
        .orElse(ResponseEntity.notFound().build());
  }

  // 5º DELETAR LUGAR
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