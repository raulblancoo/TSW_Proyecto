package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.ExpensesDto;
import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GroupMembersController {

    private final GroupService groupService;
    private final GroupMembersService groupMembersService;


    // TODO: hacer introducir miembro en el grupo
    @PostMapping("/groups/expenses/{groupId}/addMember")
    public String createNewGroupMember(Model model,@PathVariable("groupId") Long groupId, @ModelAttribute("user") UserDto userDto){
        GroupDto grupo = groupService.findGroupById(groupId);
        groupMembersService.saveGroupMember(grupo, userDto);


        return "redirect:/group/expenses/" + groupId;
    }
}
