package com.davr7.backend.controllers;

import com.davr7.backend.dtos.*;
import com.davr7.backend.services.PlayerService;
import com.davr7.backend.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {
  private final PlayerService playerService;
  private final TransactionService transactionService;


  public PlayerController(PlayerService playerService, TransactionService transactionService) {
    this.playerService = playerService;
    this.transactionService = transactionService;
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

  @PatchMapping(value = "/{id}/condition")
  public ResponseEntity<PlayerOutputDto> updateConditionHandle(@PathVariable Integer id, @Validated @RequestBody UpdateConditionPlayerInputDto input) {
    PlayerOutputDto player = playerService.updateCondition(id, input);
    return  ResponseEntity.ok().body(player);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<PlayerOutputDto> removeHandle(@PathVariable Integer id) {
    playerService.remove(id);
    return  ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/{id}/buy")
  public ResponseEntity<TransactionOutputDto> buyHandle(@PathVariable Integer id, @Validated @RequestBody TransactionInputDto input) {
    TransactionOutputDto transaction = transactionService.buy(id, input);
    return  ResponseEntity.ok().body(transaction);
  }
}
