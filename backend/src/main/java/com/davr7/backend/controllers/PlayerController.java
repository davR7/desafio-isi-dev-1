package com.davr7.backend.controllers;

import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
