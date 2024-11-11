
package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Enums.CurrencyEnum;
import com.tsw.ComPay.Services.ExpensesService;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final GroupMembersService groupMembersService;
    private final UserService userService;
    private final ExpensesService expensesService;

    @GetMapping("")
    public String showGroups(Model model, @ModelAttribute("group") NewGroupDto newGroupDto) {
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for(GroupDto group : authenticatedUser.getGroup()){
            group.setAmount(expensesService.calculateTotalExpenseByGroupId(group.getId()));
        }

        model.addAttribute("groups", authenticatedUser.getGroup());
        model.addAttribute("usuario", authenticatedUser);

        List<CurrencyEnum> currencies = Arrays.asList(CurrencyEnum.values());
        model.addAttribute("currencies", currencies);

        return "groups/groups";
    }

    @PostMapping("/create")
    public String createGroup(Model model, @ModelAttribute("group") NewGroupDto newGroupDto) {
        UserAuthDto authenticatedUser = (UserAuthDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        groupService.saveGroup(newGroupDto);
        List<String> emails = new ArrayList<>(List.of(newGroupDto.getEmails()));
        emails.add(authenticatedUser.getEmail());

        // TODO: TIENE QUE SER FINDGROUPBYID (PARA QUE DOS USUARIOS DIFERENES PUEDAN CREAR GRUPOS CON EL MISMO NOMBRE AL QUE YA ESTÁN)
        for (String email : emails) {
            groupMembersService.saveGroupMember(groupService.findGroupByName(newGroupDto.getGroupName()), userService.findByEmail(email));
        }

        authenticatedUser.setGroup(groupService.actualizarGrupos(authenticatedUser.getEmail())); // Actualiza la sesión con los grupos nuevos

        return "redirect:/groups";
    }
}
