package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {
    List<ExpensesModel> findExpensesModelByGroup_Id(Long id);
}
