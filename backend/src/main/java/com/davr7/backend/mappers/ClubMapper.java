package com.davr7.backend.mappers;

import com.davr7.backend.dtos.ClubOutputDto;
import com.davr7.backend.dtos.PlayerSummaryDto;
import com.davr7.backend.models.ClubModel;

import java.util.List;

public class ClubMapper {
  public static ClubOutputDto toOutputDto(ClubModel club) {
    List<PlayerSummaryDto> players = club.getPlayers()
        .stream().map(PlayerMapper::toSummaryDto).toList();

    return new ClubOutputDto(
        club.getId(),
        club.getName(),
        club.getAcronym(),
        club.getCash(),
        club.getSquadValue(),
        club.getNetWorth(),
        players
    );
  }
}
