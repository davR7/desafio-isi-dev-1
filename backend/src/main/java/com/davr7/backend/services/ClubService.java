package com.davr7.backend.services;

import com.davr7.backend.dtos.ClubOutputDto;
import com.davr7.backend.mappers.ClubMapper;
import com.davr7.backend.models.ClubModel;
import com.davr7.backend.repositories.ClubRepository;
import com.davr7.backend.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
  private final ClubRepository clubRepository;

  public ClubService(ClubRepository clubRepository) {
    this.clubRepository = clubRepository;
  }

  public List<ClubOutputDto> list() {
    List<ClubModel> clubs = clubRepository.findAll();
    return clubs.stream().map(ClubMapper::toOutputDto).toList();
  }

  public ClubOutputDto getById(Integer id) {
    ClubModel club = clubRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Clube não encontrado."));

    return ClubMapper.toOutputDto(club);
  }
}
