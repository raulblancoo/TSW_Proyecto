package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Mapper.ExpenseShareMapper;
import com.tsw.ComPay.Models.ExpenseShareModel;
import com.tsw.ComPay.Models.ExpensesModel;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.ExpenseShareRepository;
import com.tsw.ComPay.Services.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delete(Long id) {
        expenseShareRepository.deleteByExpenseId(id);
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
        Map<UserModel, Double> loanMap = calculateLoans(groupId);
        Map<UserModel, Double> debtMap = calculateDebts(groupId);
        List<UserModel> uniqueUsers = findUniqueUsers(groupId);
        return createDebtDtos(uniqueUsers, loanMap, debtMap);
    }

    @Override
    public List<BizumsDto> findUsersBizumsByGroupId(Long groupId) {
        Map<UserModel, Double> balanceMap = calculateBalances(groupId);
        PriorityQueue<Map.Entry<UserModel, Double>> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        PriorityQueue<Map.Entry<UserModel, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        // Poblar los heaps con los balances positivos y negativos
        for (Map.Entry<UserModel, Double> entry : balanceMap.entrySet()) {
            if (entry.getValue() < 0) {
                minHeap.add(entry);
            } else if (entry.getValue() > 0) {
                maxHeap.add(entry);
            }
        }

        List<BizumsDto> bizumsTransactions = new ArrayList<>();

        while (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            Map.Entry<UserModel, Double> debtor = minHeap.poll();
            Map.Entry<UserModel, Double> creditor = maxHeap.poll();

            double amount = Math.min(-debtor.getValue(), creditor.getValue());
            BizumsDto bizum = new BizumsDto();
            bizum.setDebtUser(createUserDto(debtor.getKey()));
            bizum.setLoanUser(createUserDto(creditor.getKey()));
            bizum.setAmount(amount);

            bizumsTransactions.add(bizum);

            debtor.setValue(debtor.getValue() + amount);
            creditor.setValue(creditor.getValue() - amount);

            // Reinsertar en los heaps si quedan saldos pendientes
            if (debtor.getValue() < 0) {
                minHeap.add(debtor);
            }
            if (creditor.getValue() > 0) {
                maxHeap.add(creditor);
            }
        }

        return bizumsTransactions;
    }

    private Map<UserModel, Double> calculateBalances(Long groupId) {
        Map<UserModel, Double> balances = new HashMap<>();
        List<ExpenseShareModel> listExpenses = expenseShareRepository.findExpensesByGroup(groupId);

        for (ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser(); // El usuario que paga (acreedor)
            UserModel payer = expense.getDestinyUser(); // El usuario que debe (deudor)
            double debtAmount = expense.getDebt();

            // Ignorar los casos en los que loaner y payer sean la misma persona
            if (!loaner.equals(payer)) {
                // Actualizar saldo del acreedor (suma el monto adeudado)
                balances.put(loaner, balances.getOrDefault(loaner, 0.0) + debtAmount);

                // Actualizar saldo del deudor (resta el monto adeudado)
                balances.put(payer, balances.getOrDefault(payer, 0.0) - debtAmount);
            }
        }

        return balances;
    }

    private Map<UserModel, Double> calculateLoans(Long groupId) {
        Map<UserModel, Double> loanMap = new HashMap<>();
        List<ExpenseShareModel> listExpenses = expenseShareRepository.findExpensesByGroup(groupId);

        for (ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser();
            if (!expense.getDestinyUser().equals(loaner)) {
                loanMap.put(loaner, loanMap.getOrDefault(loaner, 0.0) + expense.getDebt());
            }
        }

        return loanMap;
    }

    private Map<UserModel, Double> calculateDebts(Long groupId) {
        Map<UserModel, Double> debtMap = new HashMap<>();
        List<ExpenseShareModel> listExpenses = expenseShareRepository.findExpensesByGroup(groupId);

        for (ExpenseShareModel expense : listExpenses) {
            UserModel payer = expense.getDestinyUser();
            UserModel loaner = expense.getExpense().getOriginUser();
            if (!loaner.equals(payer)) {
                debtMap.put(payer, debtMap.getOrDefault(payer, 0.0) + expense.getDebt());
            }
        }

        return debtMap;
    }

    private List<UserModel> findUniqueUsers(Long groupId) {
        List<UserModel> uniqueUsers = new ArrayList<>();
        List<ExpenseShareModel> listExpenses = expenseShareRepository.findExpensesByGroup(groupId);

        for (ExpenseShareModel expense : listExpenses) {
            UserModel loaner = expense.getExpense().getOriginUser();
            UserModel payer = expense.getDestinyUser();
            if (!loaner.equals(payer)) {
                if (!uniqueUsers.contains(loaner)) {
                    uniqueUsers.add(loaner);
                }
                if (!uniqueUsers.contains(payer)) {
                    uniqueUsers.add(payer);
                }
            }
        }

        return uniqueUsers;
    }

    private List<DebtDto> createDebtDtos(List<UserModel> uniqueUsers, Map<UserModel, Double> loanMap, Map<UserModel, Double> debtMap) {
        List<DebtDto> listDebDto = new ArrayList<>();

        for (UserModel user : uniqueUsers) {
            DebtDto debt = new DebtDto();
            debt.setUser(createUserDto(user));
            debt.setLoanAmount(loanMap.getOrDefault(user, 0.0));
            debt.setDebtAmount(debtMap.getOrDefault(user, 0.0));

            listDebDto.add(debt);
        }

        return listDebDto;
    }

    private UserDto createUserDto(UserModel user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAvatarURL(user.getAvatarURL());
        return userDto;
    }




}

