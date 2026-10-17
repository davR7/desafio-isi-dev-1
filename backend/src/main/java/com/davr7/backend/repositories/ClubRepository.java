package com.davr7.backend.repositories;

import com.davr7.backend.models.ClubModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubModel, Long> {
  Optional<ClubModel> findByName(String name);
}
