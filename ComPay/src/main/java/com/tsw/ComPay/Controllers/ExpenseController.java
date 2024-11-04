package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Services.ExpensesService;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ExpenseController {

    @Autowired
    ExpensesService expensesService;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupMembersService groupMembersService;

    /* TODO: para que pueda mostrar los pagos ordenados se debe enviar a un mapa que se organice las fechas
        y tenga la lista de pagos asociada a cada fecha. Despues en el modelo se deben de recorrer todas las
        fechas e ir listando los pagos
    */
    @GetMapping("groups/{groupId}/expenses")
    public String showExpenses(@PathVariable("groupId") Long groupId, Model model){
        GroupDto group = groupService.findGroupById(groupId);
        List<ExpensesDto> expenses = expensesService.findAllExpensesByGroupId(groupId);

        model.addAttribute("group",group);
        model.addAttribute("expenses", expenses);

        return "expenses/expenses";
    }

    @GetMapping("/expenses")
    public String showMyExpenses(Model model){
        return "expenses/allExpenses";
    }


    // TODO: para que ponga la fecha por encima
//    @GetMapping("/expenses")
//    public String getExpenses(@PathVariable("groupId") Long groupId, Model model) {
//        // Suponiendo que tienes un servicio que te devuelve todos los gastos
//        List<ExpensesDto> expenses = expensesService.findAllExpensesByGroupId(groupId);
//
//        // Agrupar los gastos por fecha
//        Map<Date, List<ExpensesDto>> groupedExpenses = expenses.stream()
//                .collect(Collectors.groupingBy(ExpensesDto::getExpense_date));
//
//        // Pasar el mapa de gastos agrupados al modelo
//        model.addAttribute("groupedExpenses", groupedExpenses);
//
//        // Retornar la vista que mostrará la lista de gastos
//        return "expenses/expenses";
//    }


}
