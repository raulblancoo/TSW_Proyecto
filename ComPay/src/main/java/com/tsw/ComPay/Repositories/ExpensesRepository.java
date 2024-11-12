package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpensesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<ExpensesModel, Long> {
    //ExpensesModel findExpensesModelById(Long id);
    void deleteById(Long id);
    ExpensesModel findExpensesModelById(Long id);
    List<ExpensesModel> findExpensesModelByGroupId(Long id);
    List<ExpensesModel> findExpensesModelByOriginUser_Username(String username);

    @Query("SELECT e FROM ExpensesModel e WHERE e.group.id = :groupId ORDER BY e.expense_date DESC")
    List<ExpensesModel> findExpensesModelByGroup_IdOrderByExpense_dateDesc(Long groupId);

    @Query("SELECT e FROM ExpensesModel e WHERE e.originUser.id = :userId ORDER BY e.expense_date DESC")
    List<ExpensesModel> findExpensesModelByOriginUser_IdOrderByExpense_dateDesc(Long userId);

}
