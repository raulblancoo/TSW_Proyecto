package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseShareService {

    void save(UserDto user, ExpensesDto expenseDto);
}
