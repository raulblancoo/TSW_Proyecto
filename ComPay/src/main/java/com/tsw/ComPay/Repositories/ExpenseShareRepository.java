package com.tsw.ComPay.Repositories;

import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShareModel, Long> {
    List<ExpenseShareModel> findByExpense_Id(Long id);
    List<ExpenseShareModel> findByExpense_GroupId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByExpenseOriginUserId(Long id);
    List<ExpenseShareModel> findExpenseShareModelsByDestinyUserId(Long userAuthId);
}
