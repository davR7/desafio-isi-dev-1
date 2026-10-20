package com.davr7.backend.repositories;

import com.davr7.backend.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Integer> {
  Optional<PlayerModel> findByName(String name);
}
