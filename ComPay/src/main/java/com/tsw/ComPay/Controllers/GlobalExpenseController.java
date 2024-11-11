package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GlobalExpenseController {
    private final ExpensesService expensesService;

    @GetMapping("/expenses")
    public String viewExpenses(Model model){
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpensesDto> expenses = expensesService.findExpensesByPayerId_Username(authenticatedUser.getUsername());

        model.addAttribute("usuario",authenticatedUser);
        model.addAttribute("expenses", expenses);

        return "expenses/allExpenses";
    }

    @GetMapping("/debts")
    public String viewDebts(Model model){
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpensesDto> debts = expensesService.findExpensesByPayerId_Username(authenticatedUser.getUsername());

        model.addAttribute("usuario",authenticatedUser);
        model.addAttribute("debts", debts);

        return "expenses/allDebts";
    }
}
