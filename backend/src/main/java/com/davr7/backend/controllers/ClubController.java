package com.davr7.backend.controllers;

import com.davr7.backend.dtos.ClubInputDto;
import com.davr7.backend.dtos.ClubOutputDto;
import com.davr7.backend.dtos.ClubSummaryDto;
import com.davr7.backend.services.ClubService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clubs")
public class ClubController {
  private final ClubService clubService;

  public ClubController(ClubService clubService) {
    this.clubService = clubService;
  }

  @GetMapping
  public ResponseEntity<List<ClubSummaryDto>> listHandle() {
    List<ClubSummaryDto> clubs = clubService.list();
    return  ResponseEntity.ok().body(clubs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClubOutputDto> getByIdHandle(@PathVariable Integer id) {
    ClubOutputDto club = clubService.getById(id);
    return  ResponseEntity.ok().body(club);
  }

  @PostMapping
  public ResponseEntity<ClubOutputDto> createHandle(@Valid @RequestBody ClubInputDto input) {
    ClubOutputDto club = clubService.create(input);
    return  ResponseEntity.status(HttpStatus.CREATED).body(club);
  }
}
