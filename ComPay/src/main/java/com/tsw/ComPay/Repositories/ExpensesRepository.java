package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {
    //ExpensesModel findExpensesModelById(Long id);
    List<ExpensesModel> findExpensesModelById(Long id);
    List<ExpensesModel> findExpensesModelByGroupId(Long id);
}
