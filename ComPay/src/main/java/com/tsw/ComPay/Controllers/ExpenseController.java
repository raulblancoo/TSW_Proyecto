package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Enums.ExpenseMethodEnum;
import com.tsw.ComPay.Mapper.NewExpenseMapper;
import com.tsw.ComPay.Repositories.ExpenseShareRepository;
import com.tsw.ComPay.Repositories.ExpensesRepository;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final ExpensesRepository expensesRepository;
    private final ExpenseShareRepository expenseShareRepository;

    @GetMapping("/{groupId}")
    public String viewGroupDetails(@ModelAttribute("expense") NewExpenseDto newExpenseDto,
                                   @ModelAttribute("members") NewGroupMemberDto newGroupMemberDto,
                                   @PathVariable("groupId") Long groupId, HttpServletRequest request, Model model) {
        GroupDto group = groupService.findGroupById(groupId);
        group.setAmount(expensesService.calculateTotalExpenseByGroupId(groupId));
        List<ExpensesDto> expenses = expensesService.findByGroup(groupId);
        List<UserDto> users = groupMembersService.getAllFromGroup(groupId);
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        List<DebtDto> debts = expenseShareService.findUsersDebtByGroupId(groupId);
        List<BizumsDto> bizums  = expenseShareService.findUsersBizumsByGroupId(groupId);

        List<ExpenseMethodEnum> expenseMethods = Arrays.asList(ExpenseMethodEnum.values());
        model.addAttribute("expenseMethods", expenseMethods);

        model.addAttribute("usuario", authenticatedUser);
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("group", group);
        model.addAttribute("expenses", expenses);
        model.addAttribute("debts", debts);
        model.addAttribute("bizums", bizums);
        model.addAttribute("users", users);

        return "expenses/expenses";
    }

    @PostMapping("/create/{groupId}")
    public String createExpense(@ModelAttribute("expense") NewExpenseDto newExpenseDto, @PathVariable("groupId") Long groupId, Model model) {
        newExpenseDto.setGroup(groupService.findGroupById(groupId));
        newExpenseDto.setOriginUser(userService.findByUserId(newExpenseDto.getOriginUserId()));


        Date todayDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        newExpenseDto.setExpense_date(todayDate);

        ExpensesDto expense = expensesService.save(newExpenseDto);

        for (Long userId : newExpenseDto.getDestinationUsers()) {
            expenseShareService.save(userService.findByUserId(userId), expense,
                            newExpenseDto.getShare_method().equals(ExpenseMethodEnum.PORCENTAJES)
                            ? newExpenseDto.getDebts()[userId.intValue()] * expense.getAmount() / 100
                            : newExpenseDto.getDebts()[userId.intValue()]);
        }

        return "redirect:/group/expenses/" + groupId;
    }

    @GetMapping("edit/{groupId}/{expenseId}")
    public ResponseEntity<Map<String, Object>> updateExpense(@PathVariable Long groupId, @PathVariable Long expenseId) {

        ExpensesDto expense  = expensesService.findById(expenseId);
        GroupDto group = groupService.findGroupById(groupId);
        List<Long> users = userService.getUserByExpenseId(expenseId).stream().map(UserDto::getId).toList();
        List<Double> debts = expenseShareService.findByExpenseId(expenseId).stream().map(ExpensesShareDto::getDebt).toList();

        Map<String, Object> response = new HashMap<>();

        response.put("users", users);
        response.put("expense", expense);
        response.put("group", group);
        response.put("debts", debts);

        return ResponseEntity.ok(response);

    }

    @PostMapping("edit/{groupId}/{expenseId}")
    public String updateExpense(@PathVariable Long groupId, @PathVariable Long expenseId, @ModelAttribute("expense") NewExpenseDto newExpenseDto) {

        ExpensesDto expense = expensesService.update(newExpenseDto, expenseId);
        expenseShareService.delete(expenseId);
        for (Long userId : newExpenseDto.getDestinationUsers()) {
                expenseShareService.save(userService.findByUserId(userId), expense,
                        newExpenseDto.getShare_method().equals(ExpenseMethodEnum.PORCENTAJES)
                                ? newExpenseDto.getDebts()[userId.intValue()] * expense.getAmount() / 100
                                : newExpenseDto.getDebts()[userId.intValue()]);

        }

        return "redirect:/group/expenses/" + groupId;
    }

    @GetMapping("delete/{groupId}/{expenseId}")
    public String deleteExpense(@PathVariable Long groupId, @PathVariable Long expenseId) {
        expenseShareService.delete(expenseId);
        expensesService.delete(expenseId);

        return "redirect:/group/expenses/" + groupId;
    }

}




