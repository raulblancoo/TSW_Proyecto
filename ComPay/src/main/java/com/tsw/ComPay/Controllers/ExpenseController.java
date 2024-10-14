package com.tsw.ComPay.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("expense")
public class ExpenseController {
    @GetMapping("/new")
    public String showExpense() {
        return "expenses/expense-layout"; // Retorna la vista principal con los fragmentos
    }
}
