package com.davr7.backend.mappers;

import com.davr7.backend.dtos.PlayerOutputDto;
import com.davr7.backend.dtos.PlayerSummaryDto;
import com.davr7.backend.models.PlayerModel;

public class PlayerMapper {
  public static PlayerOutputDto toOutputDto(PlayerModel player) {
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
        player.getClub().getId()
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
