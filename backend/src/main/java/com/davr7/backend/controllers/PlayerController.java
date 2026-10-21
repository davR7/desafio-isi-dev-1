package com.davr7.backend.controllers;

import com.davr7.backend.dtos.PlayerInputDto;
import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.dtos.UpdatePlayerInputDto;
import com.davr7.backend.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {
  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping
  public ResponseEntity<List<PlayerOutputDto>> listHandle() {
    List<PlayerOutputDto> players = playerService.list();
    return  ResponseEntity.ok().body(players);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<PlayerOutputDto> getByIdHandle(@PathVariable Integer id) {
    PlayerOutputDto player = playerService.getById(id);
    return  ResponseEntity.ok().body(player);
  }

  @PostMapping
  public ResponseEntity<PlayerOutputDto> createHandle(@Validated @RequestBody PlayerInputDto input) {
    PlayerOutputDto player = playerService.create(input);
    return  ResponseEntity.status(HttpStatus.CREATED).body(player);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PlayerOutputDto> updateHandle(@PathVariable Integer id, @Validated @RequestBody UpdatePlayerInputDto input) {
    PlayerOutputDto player = playerService.update(id, input);
    return  ResponseEntity.ok().body(player);
  }
}
