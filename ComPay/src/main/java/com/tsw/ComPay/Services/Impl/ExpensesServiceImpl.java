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
import com.tsw.ComPay.Services.UserService;
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
    private final UserService userService;

    public ExpensesDto save(NewExpenseDto expensesDto) {
        ExpensesModel expense =  expensesRepository.save(newExpenseMapper.toEntity(expensesDto));

        return expenseMapper.toDto(expense);
    }

    public ExpensesDto update(NewExpenseDto newExpenseDto, Long id) {

        ExpensesDto expense = expenseMapper.toDto(expensesRepository.findExpensesModelById(id));

        expense.setExpense_name(newExpenseDto.getName());
        expense.setAmount(newExpenseDto.getAmount());
        expense.setOriginUser(userService.findByUserId(newExpenseDto.getOriginUserId()));
        expense.setShare_method(newExpenseDto.getShare_method());

       ExpensesModel expenseModel = expensesRepository.save(expenseMapper.toEntity(expense));

        return expenseMapper.toDto(expenseModel);
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
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByGroup_IdOrderByExpense_dateDesc(groupId));
    }

    @Override
    public List<ExpensesDto> findExpensesByPayerId(Long userId) {
        return expenseMapper.toListDto(expensesRepository.findExpensesModelByOriginUser_IdOrderByExpense_dateDesc(userId));
    }

    public ExpensesDto findById(Long id) {
        return expenseMapper.toDto(expensesRepository.findExpensesModelById(id));
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
