package com.davr7.backend.models;

import com.davr7.backend.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "club_id", nullable = false)
  private ClubModel club;

  @ManyToOne
  @JoinColumn(name = "player_id", nullable = false)
  private PlayerModel player;

  @Column(name = "purchase_value", precision = 6, scale = 2)
  private BigDecimal purchaseValue;

  @Column(name = "loyalty_until", nullable = false)
  private Instant loyaltyUntil;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 12)
  private ContractStatus status;

  @Column(name = "started_at", nullable = false)
  private Instant startedAt;

  @Column(name = "ended_at")
  private Instant endedAt;
}
