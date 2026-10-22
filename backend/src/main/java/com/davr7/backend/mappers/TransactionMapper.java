package com.davr7.backend.mappers;

import com.davr7.backend.dtos.ContractOutputDto;
import com.davr7.backend.dtos.TransactionOutputDto;
import com.davr7.backend.models.ContractModel;
import com.davr7.backend.models.TransactionModel;

import java.time.ZoneOffset;

public class TransactionMapper {
  public static TransactionOutputDto toOutputDto(ContractModel contract, TransactionModel transaction) {
    ContractOutputDto contractOutput = new ContractOutputDto(
        contract.getPurchaseValue(),
        contract.getLoyaltyUntil().atOffset(ZoneOffset.of("-03:00")),
        contract.getStatus()
    );

    return  new TransactionOutputDto(
        transaction.getId(),
        transaction.getPlayer().getId(),
        transaction.getPlayer().getName(),
        transaction.getType(),
        transaction.getValue(),
        transaction.getFee(),
        transaction.getProfit(),
        transaction.getCashChange(),
        transaction.getCreatedAt().atOffset(ZoneOffset.of("-03:00")),
        contractOutput,
        transaction.getClub().getId(),
        transaction.getClub().getCash());
  }
}
