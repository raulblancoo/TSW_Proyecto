package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    ExpensesService expensesService;

    @GetMapping("/expenses")
    public String showExpenses(Model model){
        List<ExpensesDto> expenses = expensesService.findAllExpenses();
        model.addAttribute("expenses", expenses);


        return "expenses/allExpenses";
    }



//@GetMapping("")
//    public String showGroups(Model model) {
//        List<GroupDto> groups = groupService.findAllGroups();
//        model.addAttribute("groups", groups);
//
//        List<CurrencyEnum> currencies = Arrays.asList(CurrencyEnum.values());
//        model.addAttribute("currencies", currencies);
//
//        return "groups/groups";
//    }

}
