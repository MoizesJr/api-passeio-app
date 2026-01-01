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

import jakarta.validation.Valid;

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
  public ResponseEntity<Lugar> criar(@RequestBody @Valid Lugar lugar) {
    Lugar lugarSalvo = service.criar(lugar);
    return ResponseEntity.ok(lugarSalvo); // 201 Created
  }

  // 2º LISTAR LUGARES
  @GetMapping
  public ResponseEntity<List<Lugar>> listar(
      @RequestParam(required = false, defaultValue = "") String nome,
      @RequestParam(required = false, defaultValue = "-1") String categoria) {

    return ResponseEntity.ok(service.listarTodos(nome, categoria));
  }

  // 3º BUSCAR LUGAR POR ID
  @GetMapping("/{id}")
  public ResponseEntity<Lugar> buscarPorId(@PathVariable Long id) {
    // Se der erro, o Service lança exceção e o código nem passa daqui
    Lugar lugar = service.buscarPorId(id);
    return ResponseEntity.ok(lugar);
  }

  // 4º ATUALIZAR LUGAR
  @PutMapping("/{id}")
  public ResponseEntity<Lugar> atualizar(@PathVariable Long id, @RequestBody @Valid Lugar lugar) {
    Lugar atualizado = service.atualizar(id, lugar);
    return ResponseEntity.ok(atualizado);
  }

  // 5º DELETAR LUGAR
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
  }
}