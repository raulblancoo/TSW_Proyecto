package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseShareService {
    void save(UserDto user, ExpensesDto expenseDto, Double debt);
    List<ExpensesShareDto> findByExpenseId(Long expenseId);
    List<ExpensesShareDto> findByGroupId(Long groupId);
    List<ExpensesShareDto> findByPayerId(Long payerId);
    List<ExpensesShareDto> findByDestinyUserId(Long userAuthId);
    List<DebtDto> findUsersDebtByGroupId(Long groupId);
}
