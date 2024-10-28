
package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Enums.CurrencyEnum;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {


    private final GroupService groupService;


    private final GroupMembersService groupMembersService;


    private final  UserService userService;

    @GetMapping("/show")
    public String showGroups(Model model) {
        //TODO: Mapper e implementar funciones para listar grupo
        List<GroupDto> groups = groupService.findAllGroups(); // Assuming you have a method to retrieve the groups
        model.addAttribute("groups", groups);
        model.addAttribute("usuario", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "groups/groups"; // Retornamos la vista principal
    }

    @GetMapping("/create")
    public String createGroup(Model model) {
        model.addAttribute("currency", new NewGroupDto());
        List<CurrencyEnum> currencies = Arrays.asList(CurrencyEnum.values());
        model.addAttribute("currencies", currencies);

        return "groups/create-group"; // Retorna la vista principal con los fragmentos
    }

    @PostMapping("/create")
    public String createGroupPost(Model model, @ModelAttribute("group") NewGroupDto newGroupDto) {
        groupService.saveGroup(newGroupDto);

        for(String email : newGroupDto.getEmails()) {
            groupMembersService.saveGroupMember(groupService.findGroupByName(newGroupDto.getGroupName()), userService.findByEmail(email));
        }

        return "redirect:/groups/show";
    }

    // pasarle el nombre del grupo directamente
    @GetMapping("/show/payments")
    public String viewGroup(Model model) {
        return "prueba/pruebapagos"; // Retorna la vista principal con los fragmentos
    }

}
