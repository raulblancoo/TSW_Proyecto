package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Mapper.ExpenseMapper;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.ExpensesModel;

import com.tsw.ComPay.Repositories.ExpensesRepository;
import com.tsw.ComPay.Services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delete(Long id) {
        expensesRepository.deleteById(id);
    }
  
    @Override
    public List<ExpensesDto> findAllExpenses() {
        return expenseMapper.toListDto(expensesRepository.findAll());
    }

    @Override
    public List<ExpensesDto> findByGroup(Long groupId) {
        // TODO
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByGroup_IdOrderByExpense_dateDesc(groupId));
    }

    @Override
    public List<ExpensesDto> findExpensesByPayerId(Long userId) {
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByOriginUser_IdOrderByExpense_dateDesc(userId));
    }

    @Override
    public double calculateTotalExpenseByGroupId(Long groupId) {
        List<ExpensesDto> expenses = findByGroup(groupId);
        double total = 0;

        for(ExpensesDto expense : expenses){
            total += expense.getAmount();
        }

        return total;
    }
}
