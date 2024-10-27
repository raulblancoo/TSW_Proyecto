package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Mapper.ExpenseMapper;
import com.tsw.ComPay.Repositories.ExpensesRepository;
import com.tsw.ComPay.Services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpenseMapper expenseMapper;
    private final ExpensesRepository expensesRepository;

    @Override
    public void saveExpense(ExpensesDto expenseDto) {
        expensesRepository.save(expenseMapper.toEntity(expenseDto));
    }

    @Override
    public List<ExpensesDto> findAllExpenses() {
        return expenseMapper.toListDto(expensesRepository.findAll());
    }

    @Override
    public List<ExpensesDto> findExpensesByGroupId(Long groupId) {
        return expenseMapper.toListDto(expensesRepository.findExpensesModelById(groupId));
    }
}
