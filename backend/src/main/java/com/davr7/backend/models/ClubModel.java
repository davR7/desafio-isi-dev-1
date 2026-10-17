package com.davr7.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubs")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, nullable = false, unique = true)
  private String name;

  @Column(columnDefinition = "CHAR(3)", nullable = false, unique = true)
  private String acronym;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal cash;

  @Column(length = 5)
  private String formation;

  public BigDecimal getSquadValue() {
    return  new BigDecimal("0.00");
  }

  public BigDecimal getNetWorth() {
    return  new BigDecimal("300.00");
  }

  @OneToMany(mappedBy = "club")
  private List<PlayerModel> players =  new ArrayList<>();

  @JsonProperty("created_at")
  @Column(nullable = false, name = "created_at")
  public Instant createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = Instant.now();
  }
}
