package com.davr7.backend.models;

import com.davr7.backend.enums.PlayerCondition;
import com.davr7.backend.enums.PlayerPosition;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  @JsonProperty("club_id")
  @Column(name = "club_id")
  private Long clubId;

  public Integer getOverall() {
    return 75;
  }

  public BigDecimal getBaseValue() {
    return new BigDecimal("40.00");
  }

  public BigDecimal getMarketValue() {
    return new BigDecimal("37.00");
  }

  @JsonProperty("created_at")
  @Column(nullable = false, name = "created_at")
  public Instant createdAt;

  @JsonProperty("updated_at")
  @Column(nullable = false, name = "updated_at")
  public Instant updatedAt;

  @PrePersist
  public void prePersist() {
    Instant now = Instant.now();
    createdAt = now;
    updatedAt = now;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = Instant.now();
  }
}
