package com.davr7.backend.repositories;

import com.davr7.backend.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
}
