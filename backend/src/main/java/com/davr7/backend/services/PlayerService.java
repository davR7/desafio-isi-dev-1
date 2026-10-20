package com.davr7.backend.services;

import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.mappers.PlayerMapper;
import com.davr7.backend.models.PlayerModel;
import com.davr7.backend.repositories.PlayerRepository;
import com.davr7.backend.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
  private final PlayerRepository playerRepository;

  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public List<PlayerOutputDto> list() {
    List<PlayerModel> players = playerRepository.findAll();
    return players.stream().map(PlayerMapper::toOutputDto).toList();
  }

  public PlayerOutputDto getById(Integer id) {
    PlayerModel player = playerRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Jogador não encontrado."));

    return PlayerMapper.toOutputDto(player);
  }
}
