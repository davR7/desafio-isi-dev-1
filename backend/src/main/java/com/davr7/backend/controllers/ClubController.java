package com.davr7.backend.controllers;

import com.davr7.backend.dtos.ClubOutputDto;
import com.davr7.backend.services.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clubs")
public class ClubController {
  private final ClubService clubService;

  public ClubController(ClubService clubService) {
    this.clubService = clubService;
  }

  @GetMapping
  public ResponseEntity<List<ClubOutputDto>> listHandle() {
    List<ClubOutputDto> clubs = clubService.list();
    return  ResponseEntity.ok().body(clubs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClubOutputDto> getByIdHandle(@PathVariable Long id) {
    ClubOutputDto club = clubService.getById(id);
    return  ResponseEntity.ok().body(club);
  }
}
