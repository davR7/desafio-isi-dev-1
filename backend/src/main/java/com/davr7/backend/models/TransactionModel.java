package com.davr7.backend.models;

import com.davr7.backend.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "club_id", nullable = false)
  private ClubModel club;

  @ManyToOne
  @JoinColumn(name = "player_id", nullable = false)
  private PlayerModel player;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", length = 6)
  private TransactionType type;

  @Column(name = "value", precision = 6, scale = 2)
  private BigDecimal value;

  @Column(name = "fee", precision = 6, scale = 2)
  private BigDecimal fee;

  @Column(name = "fine", precision = 6, scale = 2)
  private BigDecimal fine;

  @Column(name = "profit", precision = 7, scale = 2)
  private BigDecimal profit;

  @Column(name = "cash_change", precision = 8, scale = 2)
  private BigDecimal cashChange;

  @Column(name = "created_at", nullable = false, updatable = false)
  public Instant createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = Instant.now();
  }
}
