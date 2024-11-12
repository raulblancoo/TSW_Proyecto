package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    void delete(Long id);
    ExpensesDto update(NewExpenseDto expenseDto, Long id);
    List<ExpensesDto> findAllExpenses();
    ExpensesDto save(NewExpenseDto expenseDto);
    List<ExpensesDto> findByGroup(Long groupId);
    List<ExpensesDto> findExpensesByPayerId(Long userId);
    ExpensesDto findById(Long id);
    double calculateTotalExpenseByGroupId(Long groupId);
}
