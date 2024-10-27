package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.ExpensesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    void saveExpense(ExpensesDto expenseDto0);

    List<ExpensesDto> findAllExpenses();
    List<ExpensesDto> findExpensesByGroupId(Long groupId);

}
