package com.davr7.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

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
  private Integer id;

  @Column(length = 60, nullable = false, unique = true)
  private String name;

  @JdbcType(CharJdbcType.class)
  @Column(nullable = false, unique = true)
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

  @OneToMany(mappedBy = "club")
  private List<ContractModel> contracts = new ArrayList<>();

  @OneToMany(mappedBy = "club")
  private List<TransactionModel> transactions = new ArrayList<>();

  @JsonProperty("created_at")
  @Column(nullable = false, name = "created_at")
  public Instant createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = Instant.now();
  }
}
