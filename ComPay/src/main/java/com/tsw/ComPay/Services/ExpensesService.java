package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Repositories.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    void delete(Long id);
    List<ExpensesDto> findAllExpenses();
    ExpensesDto save(NewExpenseDto expenseDto);
    List<ExpensesDto> findByGroup(Long groupId);
    List<ExpensesDto> findExpensesByPayerId_Username(String username);
}
