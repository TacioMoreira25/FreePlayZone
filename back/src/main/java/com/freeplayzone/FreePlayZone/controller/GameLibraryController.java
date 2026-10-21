package com.freeplayzone.FreePlayZone.controller;

import com.freeplayzone.FreePlayZone.domain.gameLibrary.GameLibraryService;
import com.freeplayzone.FreePlayZone.domain.user.User;
import com.freeplayzone.FreePlayZone.domain.user.UserService;
import com.freeplayzone.FreePlayZone.dto.GamesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Library")
public class GameLibraryController {

    @Autowired
    private GameLibraryService bibliotecaService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody GamesResponseDTO jogo) {
        User user = userService.getUserLogado();
        bibliotecaService.addJogo(user, jogo);
        return ResponseEntity.ok("Jogo salvo na biblioteca.");
    }

    @GetMapping
    public ResponseEntity<List<GamesResponseDTO>> listar() {
        User user = userService.getUserLogado();
        return ResponseEntity.ok(bibliotecaService.listarBiblioteca(user));
    }

    @DeleteMapping("/remover/{nome}")
    public ResponseEntity<?> remover(@PathVariable String nome) {
        User user = userService.getUserLogado();
        bibliotecaService.removerJogo(user, nome);
        return ResponseEntity.ok("Jogo removido da biblioteca.");
    }
}
