package com.davr7.backend.services;

import com.davr7.backend.dtos.TransactionInputDto;
import com.davr7.backend.dtos.TransactionOutputDto;
import com.davr7.backend.enums.ContractStatus;
import com.davr7.backend.enums.TransactionType;
import com.davr7.backend.mappers.TransactionMapper;
import com.davr7.backend.models.ClubModel;
import com.davr7.backend.models.ContractModel;
import com.davr7.backend.models.PlayerModel;
import com.davr7.backend.models.TransactionModel;
import com.davr7.backend.repositories.ClubRepository;
import com.davr7.backend.repositories.ContractRepository;
import com.davr7.backend.repositories.PlayerRepository;
import com.davr7.backend.repositories.TransactionRepository;
import com.davr7.backend.services.exceptions.ConflictException;
import com.davr7.backend.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.OffsetDateTime;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final ContractRepository contractRepository;
  private final ClubRepository clubRepository;
  private final PlayerRepository playerRepository;


  public TransactionService(TransactionRepository transactionRepository, ContractRepository contractRepository, ClubRepository clubRepository, PlayerRepository playerRepository) {
    this.transactionRepository = transactionRepository;
    this.contractRepository = contractRepository;
    this.clubRepository = clubRepository;
    this.playerRepository = playerRepository;
  }

  public TransactionOutputDto buy(Integer playerId, TransactionInputDto input) {
    PlayerModel player = playerRepository.findById(playerId).orElseThrow(
        () -> new NotFoundException("Jogador não encontrado."));

    ClubModel club = clubRepository.findById(input.clubId()).orElseThrow(
        () -> new NotFoundException("Clube não encontrado."));

    if (player.getClub() != null) {
      throw new ConflictException("Jogador não está livre.");
    }

    if (playerRepository.countByClubId(club.getId()) >= 12) {
      throw new ConflictException("O clube já tem 12 jogadores.");
    }

    BigDecimal value = player.getMarketValue();
    BigDecimal fee = value.compareTo(new BigDecimal("30.00")) > 0
        ? value.multiply(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP)
        : BigDecimal.ZERO.setScale(2);
    BigDecimal totalCost = value.add(fee);

    if (totalCost.multiply(new BigDecimal("2")).compareTo(club.getCash()) > 0) {
      throw new ConflictException("O custo da compra passa de 50% do caixa.");
    }

    player.setClub(club);
    playerRepository.save(player);

    club.setCash(club.getCash().subtract(totalCost));
    clubRepository.save(club);

    ContractModel contract = new ContractModel();
    contract.setClub(club);
    contract.setPlayer(player);
    contract.setPurchaseValue(value);
    contract.setLoyaltyUntil(input.loyaltyUntil().toInstant());
    contract.setStatus(ContractStatus.ATIVO);
    contract.setStartedAt(Instant.now());
    contractRepository.save(contract);

    TransactionModel transaction = new TransactionModel();
    transaction.setClub(club);
    transaction.setPlayer(player);
    transaction.setType(TransactionType.COMPRA);
    transaction.setValue(value);
    transaction.setFee(fee);
    transaction.setFine(BigDecimal.ZERO);
    transaction.setProfit(null);
    transaction.setCashChange(totalCost.negate());
    transactionRepository.save(transaction);

    return TransactionMapper.toOutputDto(contract, transaction);
  }
}
