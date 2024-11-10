package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Mapper.ExpenseShareMapper;
import com.tsw.ComPay.Repositories.ExpenseShareRepository;
import com.tsw.ComPay.Services.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseShareServiceImpl implements ExpenseShareService {

    private final ExpenseShareRepository expenseShareRepository;

    private final ExpenseShareMapper expenseShareMapper;

    public void save(UserDto user, ExpensesDto expenseDto, Double debt) {

        ExpensesShareDto expenseShareDto = new ExpensesShareDto();


        expenseShareDto.setDestinyUser(user);

        expenseShareDto.setExpense(expenseDto);

        expenseShareDto.setDebt(debt);

        expenseShareRepository.save(expenseShareMapper.toEntity(expenseShareDto));
    }

    @Override
    public List<ExpensesShareDto> findByExpenseId(Long expenseId) {
        return expenseShareMapper.toListDto(expenseShareRepository.findByExpense_Id(expenseId));
    }

    @Override
    public List<ExpensesShareDto> findByGroupId(Long groupId) {
        return expenseShareMapper.toListDto(expenseShareRepository.findByExpense_GroupId(groupId));
    }

    @Override
    public List<ExpensesShareDto> findByPayerId(Long payerId) {
        return expenseShareMapper.toListDto(expenseShareRepository.findExpenseShareModelsByExpenseOriginUserId(payerId));
    }

    @Override
    public List<ExpensesShareDto> findByDestinyUserId(Long userAuthId) {
        return expenseShareMapper.toListDto(expenseShareRepository.findExpenseShareModelsByDestinyUserId(userAuthId));
    }


}

