package com.tsw.ComPay.Controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpensesGroupController {

    @GetMapping("/expensesGroup")
    public String showExpensesGroup(Model model) {
        // Si necesitas pasar datos dinámicos a la vista, añádelos aquí usando el model
        return "index/pruebaGroups"; // Thymeleaf buscará este archivo en templates/index/
    }
}
