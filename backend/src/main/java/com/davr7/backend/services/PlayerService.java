package com.davr7.backend.services;

import com.davr7.backend.dtos.PlayerInputDto;
import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.dtos.UpdatePlayerInputDto;
import com.davr7.backend.mappers.PlayerMapper;
import com.davr7.backend.models.PlayerModel;
import com.davr7.backend.repositories.PlayerRepository;
import com.davr7.backend.services.exceptions.ConflictException;
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

  public PlayerOutputDto create(PlayerInputDto input) {
    if(playerRepository.findByName(input.name()).isPresent()) {
      throw new ConflictException("Jogador já existe");
    }

    PlayerModel player = playerRepository.save(PlayerMapper.toDomain(input));
    return PlayerMapper.toOutputDto(player);
  }

  public PlayerOutputDto update(Integer id, UpdatePlayerInputDto input) {
    PlayerModel player = playerRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Jogador não encontrado."));

    player.setName(input.name());
    player.setAge(input.age());
    player.setAgility(input.agility());
    player.setPosition(input.position());

    playerRepository.save(player);
    return PlayerMapper.toOutputDto(player);
  }
}
