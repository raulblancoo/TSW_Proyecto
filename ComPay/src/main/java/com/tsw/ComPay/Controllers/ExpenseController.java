package com.tsw.ComPay.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {
    @GetMapping("/add-payment")
    public String showExpense() {
        return "expenses/expense-layout"; // Retorna la vista principal con los fragmentos
    }
}
