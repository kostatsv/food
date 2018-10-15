package com.example.food.backend.repositories;

import com.example.food.backend.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findAllByReceiptDateBetween(LocalDate startDate, LocalDate endDate);

}
