package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Mapper.ExpenseShareMapper;
import com.tsw.ComPay.Repositories.ExpenseShareRepository;
import com.tsw.ComPay.Services.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseShareServiceImpl implements ExpenseShareService {

    private final ExpenseShareRepository expenseShareRepository;

    private final ExpenseShareMapper expenseShareMapper;

    public void save(UserDto user, ExpensesDto expenseDto) {

        ExpensesShareDto expenseShareDto = new ExpensesShareDto();


        expenseShareDto.setDestinyUser(user);

        expenseShareDto.setExpense(expenseDto);


        expenseShareRepository.save(expenseShareMapper.toEntity(expenseShareDto));
    }
}

