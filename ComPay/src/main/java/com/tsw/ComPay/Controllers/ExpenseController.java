package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewExpenseDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Services.ExpenseShareService;
import com.tsw.ComPay.Services.ExpensesService;
import com.tsw.ComPay.Services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/group/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final GroupService groupService;

    private final ExpensesService expensesService;

    private final NewExpenseMapper newExpenseMapper;

    private final ExpenseShareService expenseShareService;

    @GetMapping("/{groupId}")
    public String viewGroupDetails(@PathVariable("groupId") Long groupId, Model model) {
        GroupDto group = groupService.findGroupById(groupId);
        List<ExpensesDto> expenses = expensesService.findByGroup(groupId);
        model.addAttribute("group", group);
        model.addAttribute("expenses", expenses);

        return "expenses/expenses";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute("group") NewExpenseDto newExpenseDto, @PathVariable("groupId") Long groupId, Model model) {
        ExpensesDto expense = expensesService.save(newExpenseDto);

        for (UserDto user : newExpenseDto.getDestinationUsers()) {
            expenseShareService.save(user, expense);
        }

        return "redirect:/group/expenses/" + groupId;

    }
}




