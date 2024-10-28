
package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Enums.CurrencyEnum;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMembersService groupMembersService;

    @Autowired
    private UserService userService;


    @GetMapping("")
    public String showGroups(Model model) {
        List<GroupDto> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);

        List<CurrencyEnum> currencies = Arrays.asList(CurrencyEnum.values());
        model.addAttribute("currencies", currencies);

        return "groups/groups"; 
    }

    //TODO: Revisar si el atributo model hace falta aquí
    @PostMapping("/create")
    public String createGroup(Model model, @ModelAttribute("group") NewGroupDto newGroupDto) {
        groupService.saveGroup(newGroupDto);

        for(String email : newGroupDto.getEmails()) {
            groupMembersService.saveGroupMember(groupService.findGroupByName(newGroupDto.getGroupName()), userService.findByEmail(email));
        }

        return "redirect:/groups";
    }

    // TODO: función edit
//    @GetMapping("/{groupId}/edit")
//    public String editGroup(@PathVariable("groupId") Long groupId, Model model) {
//        GroupDto group = groupService.findGroupById(groupId);
//        model.addAttribute("group", group);
//
//        return "";
//    }

    // TODO: comprobar endpoint para luego hacer expenses
    @GetMapping("/{groupId}")
    public String viewGroupDetails(@PathVariable("groupId") Long groupId, Model model) {
        GroupDto group = groupService.findGroupById(groupId);
        model.addAttribute("group", group);

        return "redirect:/groups/{groupId}/expenses";
    }
}
