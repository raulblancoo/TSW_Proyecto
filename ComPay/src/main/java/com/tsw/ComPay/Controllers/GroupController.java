
package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Enums.CurrencyEnum;
import com.tsw.ComPay.Services.GroupMembersService;
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

    @Autowired
    private GroupMembersService groupMembersService;

    @GetMapping("/show")
    public String showGroups(Model model) {
        //TODO: Mapper e implementar funciones para listar grupo
        List<GroupDto> groups = groupService.findAllGroups(); // Assuming you have a method to retrieve the groups
        model.addAttribute("groups", groups);
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
            groupMembersService.saveGroupMember(newGroupDto.getGroupName(), email);
        }

        return "redirect:/groups/show";
    }

    // pasarle el nombre del grupo directamente
    @GetMapping("/view-group")
    public String viewGroup(Model model) {
        return "groups/group-expense-layout"; // Retorna la vista principal con los fragmentos
    }

}
