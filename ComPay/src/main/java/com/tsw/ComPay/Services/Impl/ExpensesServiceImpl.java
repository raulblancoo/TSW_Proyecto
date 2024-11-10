package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Mapper.ExpenseMapper;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Models.ExpensesModel;

import com.tsw.ComPay.Repositories.ExpensesRepository;
import com.tsw.ComPay.Services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;

    private final NewExpenseMapper newExpenseMapper;

    private final ExpenseMapper expenseMapper;

    public ExpensesDto save(NewExpenseDto expensesDto) {
        ExpensesModel expense =  expensesRepository.save(newExpenseMapper.toEntity(expensesDto));

        return expenseMapper.toDto(expense);
    }
  
    @Override
    public List<ExpensesDto> findAllExpenses() {
        return expenseMapper.toListDto(expensesRepository.findAll());
    }

    @Override
    public List<ExpensesDto> findByGroup(Long groupId) {
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByGroup_Id(groupId));
    }

    @Override
    public List<ExpensesDto> findExpensesByPayerId(Long userId) {
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByOriginUser_Id(userId));
    }


}
