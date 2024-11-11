package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.ExpensesShareDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseShareService {

    void save(UserDto user, ExpensesDto expenseDto, Double debt);
    void delete(Long expenseId);
    List<ExpensesShareDto> findByExpenseId(Long expenseId);

}
