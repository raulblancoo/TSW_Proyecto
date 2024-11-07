package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Models.GroupMembersModel;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.*;
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
    private final GroupMembersRepository groupMembersRepository;
    private final UserService userService;
    private final GroupMembersService groupMembersService;

    @GetMapping("/{groupId}")
    public String viewGroupDetails(@PathVariable("groupId") Long groupId, Model model) {
        GroupDto group = groupService.findGroupById(groupId);
        List<ExpensesDto> expenses = expensesService.findByGroup(groupId);
        List<UserDto> users = groupMembersService.getAllFromGroup(groupId);

        model.addAttribute("group", group);
        model.addAttribute("expenses", expenses);
        model.addAttribute("users", users);
        model.addAttribute("expense", new NewExpenseDto());

        return "expenses/expenses";
    }

    @PostMapping("/create/{groupId}")
    public String createGroup(@ModelAttribute("expense") NewExpenseDto newExpenseDto, @PathVariable("groupId") Long groupId, Model model) {
        newExpenseDto.setGroup(groupService.findGroupById(groupId));
        ExpensesDto expense = expensesService.save(newExpenseDto);

        for (Long userId : newExpenseDto.getDestinationUsers()) {
            expenseShareService.save(userService.findByUserId(userId), expense);
        }

        return "redirect:/group/expenses/" + groupId;

    }
}




