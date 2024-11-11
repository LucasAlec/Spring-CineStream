// FilmeController.java
package com.tech.ada.spring_cinestream.controller;

import com.tech.ada.spring_cinestream.model.Filme;
import com.tech.ada.spring_cinestream.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class UserController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        Optional<Filme> filme = filmeService.buscarPorId(id);
        return filme.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Filme salvar(@RequestBody Filme filme) {
        return filmeService.salvar(filme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (filmeService.buscarPorId(id).isPresent()) {
            filmeService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
