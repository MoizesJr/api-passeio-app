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

import com.moizesjr.passeio_api.model.Usuario;
import com.moizesjr.passeio_api.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UsuarioController {

  private final UsuarioService service;

  public UsuarioController(UsuarioService service) {
    this.service = service;
  }

  // LOGIN VIA GOOGLE
  @PostMapping("/login")
  public ResponseEntity<Usuario> login(@RequestBody Usuario usuarioGoogle) {
    return ResponseEntity.ok(service.login(usuarioGoogle));
  }

  // LISTAR TODOS
  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> listar() {
    return ResponseEntity.ok(service.listarTodos());
  }

  // BUSCAR POR ID
  @GetMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscarPorId(id));
  }

  // CRIAR (Manual, se necessário)
  @PostMapping("/usuarios")
  public ResponseEntity<Usuario> criar(@RequestBody @Valid Usuario usuario) {
    return ResponseEntity.status(201).body(service.criar(usuario));
  }

  // ATUALIZAR (Útil para mudar perfil de USER para ADMIN via sistema)
  @PutMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
    return ResponseEntity.ok(service.atualizar(id, usuario));
  }

  // DELETAR
  @DeleteMapping("/usuarios/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
  }
}