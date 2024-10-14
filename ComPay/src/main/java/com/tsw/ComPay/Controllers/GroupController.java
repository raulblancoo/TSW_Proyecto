
package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Enums.CurrencyEnum;
import com.tsw.ComPay.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/show")
    public String showGroups(Model model) {
        return "groups/groups"; // Retornamos la vista principal
    }

    @GetMapping("/create")
    public String createGroup(Model model) {
        //List<String> currency = Arrays.asList(CurrencyEnum.values().toString());
        model.addAttribute("currency", new NewGroupDto());
        return "groups/create-group"; // Retorna la vista principal con los fragmentos
    }

    @PostMapping("/create")
    public String createGroupPost(Model model, @ModelAttribute("group") NewGroupDto newGroupDto) {
        model.addAttribute("group", newGroupDto);
        return "";
    }

    @GetMapping("/view-group")
    public String viewGroup(Model model) {
        return "index/expensesGroup"; // Retorna la vista principal con los fragmentos
    }
}
