package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.ExpensesShareDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Services.ExpensesService;
import com.tsw.ComPay.Services.ExpenseShareService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final ExpenseShareService expenseShareService;

    @GetMapping("/expenses")
    public String viewExpenses(Model model, HttpServletRequest request){
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpensesDto> expenses = expensesService.findExpensesByPayerId(authenticatedUser.getId());

        model.addAttribute("usuario",authenticatedUser);
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("expenses",expenses);

        return "expenses/allExpenses";
    }

    @GetMapping("/debts")
    public String viewDebts(Model model, HttpServletRequest request) {
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpensesShareDto> debts = expenseShareService.findByDestinyUserId(authenticatedUser.getId());

        // Filtrar deudas donde el originUser es el usuario autenticado
        debts.removeIf(debt -> debt.getExpense().getOriginUser().getId().equals(authenticatedUser.getId()));

        model.addAttribute("usuario", authenticatedUser);
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("debts", debts);

        return "expenses/allDebts";
    }

}
