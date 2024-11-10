package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpenseShareModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShareModel, Long> {
    List<ExpenseShareModel> findByExpense_Id(Long id);
}
