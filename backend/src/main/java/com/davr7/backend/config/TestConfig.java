package com.davr7.backend.config;

import com.davr7.backend.enums.PlayerCondition;
import com.davr7.backend.enums.PlayerPosition;
import com.davr7.backend.models.ClubModel;
import com.davr7.backend.models.PlayerModel;
import com.davr7.backend.repositories.ClubRepository;
import com.davr7.backend.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {
  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private ClubRepository clubRepository;

  @Override
  public void run(String... args) throws Exception {
    ClubModel c1 = new ClubModel();
    c1.setName("Águias FC");
    c1.setAcronym("AGU");
    c1.setCash(new BigDecimal("300.00"));

    PlayerModel p1 = new PlayerModel();
    p1.setName("Meia Lua");
    p1.setPosition(PlayerPosition.MEI);
    p1.setAgility(85);
    p1.setAge(25);
    p1.setCondition(PlayerCondition.APTO);
    p1.setPace(70);
    p1.setShooting(68);
    p1.setPassing(82);
    p1.setDribbling(79);
    p1.setDefending(55);
    p1.setPhysical(64);
    p1.setPotential(86);
    p1.setClub(c1);

    PlayerModel p2 = new PlayerModel();
    p2.setName("Trovão");
    p2.setPosition(PlayerPosition.ATA);
    p2.setAgility(80);
    p2.setAge(23);
    p2.setCondition(PlayerCondition.APTO);
    p2.setPace(90);
    p2.setShooting(84);
    p2.setPassing(65);
    p2.setDribbling(78);
    p2.setDefending(35);
    p2.setPhysical(76);
    p2.setPotential(88);
    p2.setClub(c1);

    clubRepository.save(c1);
    playerRepository.saveAll(Arrays.asList(p1, p2));
  }
}
