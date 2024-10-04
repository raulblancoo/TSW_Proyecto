package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {
}
