package com.davr7.backend.models;

import com.davr7.backend.enums.PlayerCondition;
import com.davr7.backend.enums.PlayerPosition;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private Integer age;

  @Enumerated(EnumType.STRING)
  @Column(length = 3, nullable = false)
  private PlayerPosition position;

  @Enumerated(EnumType.STRING)
  @Column(length = 20, nullable = false)
  private PlayerCondition condition;

  @Column(nullable = false)
  private Integer agility;

  @Column(nullable = false)
  private Integer pace;

  @Column(nullable = false)
  private Integer shooting;

  @Column(nullable = false)
  private Integer passing;

  @Column(nullable = false)
  private Integer dribbling;

  @Column(nullable = false)
  private Integer defending;

  @Column(nullable = false)
  private Integer physical;

  @Column(nullable = false)
  private Integer potential;

  @Column(name = "base_value", nullable = false, precision = 6, scale = 2, updatable = false)
  private BigDecimal baseValue;

  @ManyToOne
  @JoinColumn(name = "club_id")
  private ClubModel club;

  @OneToMany(mappedBy = "player")
  private List<ContractModel> contracts = new ArrayList<>();

  @OneToMany(mappedBy = "player")
  private List<TransactionModel> transactions = new ArrayList<>();

  public int getOverall() {
    double result = (pace * position.getPace()
        + shooting * position.getShooting()
        + passing * position.getPassing()
        + dribbling * position.getDribbling()
        + defending * position.getDefending()
        + physical * position.getPhysical()) / 100.0;

    return (int) Math.round(result);
  }

  public BigDecimal getMarketValue() {
    BigDecimal totPercentage = calculateAgeDiscount()
        .subtract(calculateConditionDiscount())
        .max(BigDecimal.valueOf(-40));

    return baseValue
        .multiply(BigDecimal.valueOf(100).add(totPercentage))
        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
        .max(BigDecimal.ONE.setScale(2));
  }

  private BigDecimal calculateBaseValue() {
    int overall = getOverall();

    double totPoints = calculatePoints(overall, 40, 60) * 0.50
        + calculatePoints(overall, 60, 75) * 2.0
        + calculatePoints(overall, 75, 99) * 6.0;

    if (age <= 21) {
      totPoints *= (100 + potential - overall) / 100.00;
    }

    return BigDecimal.valueOf(totPoints)
        .setScale(2, RoundingMode.HALF_UP)
        .max(BigDecimal.ONE);
  }

  private BigDecimal calculateConditionDiscount() {
    BigDecimal discount = switch (condition) {
      case MAL_CONDICIONADO -> BigDecimal.valueOf(15);
      case DOENTE -> BigDecimal.valueOf(30);
      default -> BigDecimal.ZERO;
    };

    if (agility >= 31 && agility <= 79) {
      return discount.add(BigDecimal.TEN);
    }

    if (agility >= 80 && agility <= 100) {
      return discount.divide(BigDecimal.valueOf(2));
    }

    return discount;
  }

  private BigDecimal calculateAgeDiscount() {
    if (age <= 21) return BigDecimal.TEN;
    if (age >= 32) return BigDecimal.TEN.negate();
    return BigDecimal.ZERO;
  }

  private int calculatePoints(int overall, int from, int to) {
    return Math.max(0, Math.min(overall, to) - from);
  }

  @JsonProperty("created_at")
  @Column(nullable = false, name = "created_at", updatable = false)
  public Instant createdAt;

  @JsonProperty("updated_at")
  @Column(nullable = false, name = "updated_at")
  public Instant updatedAt;

  @PrePersist
  public void prePersist() {
    this.baseValue = calculateBaseValue();

    Instant now = Instant.now();
    createdAt = now;
    updatedAt = now;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = Instant.now();
  }
}
