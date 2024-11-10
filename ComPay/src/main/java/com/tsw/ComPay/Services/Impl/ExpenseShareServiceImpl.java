package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Mapper.ExpenseShareMapper;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.ExpenseShareRepository;
import com.tsw.ComPay.Services.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<DebtDto> findUsersDebtByGroupId(Long groupId) {
        Map<UserModel, Double> loanMap = new HashMap<UserModel, Double>();
        Map<UserModel, Double> debtMap = new HashMap<UserModel, Double>();
        List<ExpenseShareModel> listExpenses = expenseShareRepository.findExpensesByGroup(groupId);
        List<UserModel> uniqueUsers = new ArrayList<>();
        List<DebtDto> listDebDto = new ArrayList<>();

        for (ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser();
            if (!expense.getDestinyUser().equals(loaner)) {
                if (loanMap.containsKey(loaner)) {
                    loanMap.put(loaner, expense.getDebt() + loanMap.get(loaner));

                } else {
                    loanMap.put(loaner, expense.getDebt());
                }
            }
        }

        for(ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser();
            UserModel payer = expense.getDestinyUser();
            if (!loaner.equals(payer)) {
                if(debtMap.containsKey(payer)){
                    debtMap.put(payer, expense.getDebt() + debtMap.get(payer));
                }else{
                    debtMap.put(payer, expense.getDebt());
                }
            }
        }

        for(ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser();
            UserModel payer = expense.getDestinyUser();
            if (!loaner.equals(payer)) {
                if(!uniqueUsers.contains(loaner)){
                    uniqueUsers.add(loaner);
                }
                if(!uniqueUsers.contains(payer)){
                    uniqueUsers.add(payer);
                }
            }
        }

        for(UserModel user : uniqueUsers) {
            DebtDto debt = new DebtDto();
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setAvatarURL(user.getAvatarURL());

            debt.setUser(userDto);
            if(loanMap.containsKey(user)) {
                debt.setLoanAmount(loanMap.get(user));
            } else{
                debt.setDebtAmount(0);
            }

            if(debtMap.containsKey(user)) {
                debt.setDebtAmount(debtMap.get(user));
            } else{
                debt.setDebtAmount(0);
            }

            listDebDto.add(debt);
        }

        return listDebDto;
    }


}

