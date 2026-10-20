package com.davr7.backend.mappers;

import com.davr7.backend.dtos.PlayerInputDto;
import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.dtos.PlayerSummaryDto;
import com.davr7.backend.enums.PlayerCondition;
import com.davr7.backend.models.PlayerModel;

public class PlayerMapper {
  public static PlayerModel toDomain(PlayerInputDto input) {
    PlayerModel player = new PlayerModel();
    player.setName(input.name());
    player.setAge(input.age());
    player.setPosition(input.position());
    player.setCondition(PlayerCondition.APTO);
    player.setAgility(input.agility());
    player.setPace(input.pace());
    player.setShooting(input.shooting());
    player.setPassing(input.passing());
    player.setDribbling(input.dribbling());
    player.setDefending(input.defending());
    player.setPhysical(input.physical());
    player.setPotential(input.potential());
    return player;
  }

  public static PlayerOutputDto toOutputDto(PlayerModel player) {
    Integer clubId = player.getClub() != null ? player.getClub().getId() : null;

    return new PlayerOutputDto(
        player.getId(),
        player.getName(),
        player.getAge(),
        player.getPosition(),
        player.getCondition(),
        player.getAgility(),
        player.getPace(),
        player.getShooting(),
        player.getPassing(),
        player.getDribbling(),
        player.getDefending(),
        player.getPhysical(),
        player.getPotential(),
        player.getOverall(),
        player.getBaseValue(),
        player.getMarketValue(),
        clubId
    );
  }

  public static PlayerSummaryDto toSummaryDto(PlayerModel player) {
    return new PlayerSummaryDto(
        player.getId(),
        player.getName(),
        player.getAge(),
        player.getPosition(),
        player.getCondition(),
        player.getAgility(),
        player.getPace(),
        player.getShooting(),
        player.getPassing(),
        player.getDribbling(),
        player.getDefending(),
        player.getPhysical(),
        player.getPotential(),
        player.getOverall(),
        player.getBaseValue(),
        player.getMarketValue()
    );
  }
}
