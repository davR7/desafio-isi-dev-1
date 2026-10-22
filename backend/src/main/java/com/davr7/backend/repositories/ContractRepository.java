package com.davr7.backend.repositories;

import com.davr7.backend.models.ContractModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractModel, Integer> {
}
