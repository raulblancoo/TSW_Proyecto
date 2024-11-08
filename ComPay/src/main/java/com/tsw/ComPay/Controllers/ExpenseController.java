package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Enums.ExpenseMethodEnum;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
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
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ExpenseMethodEnum> expenseMethods = Arrays.asList(ExpenseMethodEnum.values());
        model.addAttribute("expenseMethods", expenseMethods);

        model.addAttribute("usuario", authenticatedUser);
        model.addAttribute("group", group);
        model.addAttribute("expenses", expenses);
        model.addAttribute("users", users);
        model.addAttribute("expense", new NewExpenseDto());

        expenses.isEmpty();

        return "expenses/expenses";
    }

    @PostMapping("/create/{groupId}")
    public String createGroup(@ModelAttribute("expense") NewExpenseDto newExpenseDto, @PathVariable("groupId") Long groupId, Model model) {
        newExpenseDto.setGroup(groupService.findGroupById(groupId));
        newExpenseDto.setOriginUser(userService.findByUserId(newExpenseDto.getOriginUserId()));

        // Crear la fecha de hoy y convertirla a Date en formato sin hora
        Date todayDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        newExpenseDto.setExpense_date(todayDate);

        ExpensesDto expense = expensesService.save(newExpenseDto);

        for (Long userId : newExpenseDto.getDestinationUsers()) {
            expenseShareService.save(userService.findByUserId(userId), expense);
        }

        return "redirect:/group/expenses/" + groupId;
    }
}




