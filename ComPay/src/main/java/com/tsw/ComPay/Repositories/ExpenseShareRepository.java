package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Models.ExpenseShareModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShareModel, Long> {
    List<ExpenseShareModel> findByExpense_Id(Long id);
    List<ExpenseShareModel> findByExpense_GroupId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByExpenseOriginUserId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByDestinyUserId(Long userAuthId);
    @Query(
            value = " SELECT s FROM ExpenseShareModel s WHERE s.expense.group.id = :groupId"
    )
    List<ExpenseShareModel> findExpensesByGroup(Long groupId);
}
