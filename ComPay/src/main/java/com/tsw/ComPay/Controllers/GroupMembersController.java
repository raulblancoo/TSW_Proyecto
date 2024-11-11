package com.tsw.ComPay.Controllers;

import com.tsw.ComPay.Dto.*;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GroupMembersController {

    private final GroupService groupService;
    private final UserService userService;
    private final GroupMembersService groupMembersService;


//    @GetMapping("/group/expenses/{groupId}/addMember")
//    public String view(Model model, @PathVariable("groupId") Long groupId, @ModelAttribute("members") NewGroupMemberDto newGroupMemberDto) {
//        GroupDto grupo = groupService.findGroupById(groupId);
//
//        for (String email : newGroupMemberDto.getEmails()) {
//            // TODO LOGICA PARA SACAR LOS USERDTO A PARTIR DE EMAILS
//            UserDto user = userService.findByEmail(email);
//        }
//
//        return "redirect:/group/expenses/" + groupId;
//    }


    @PostMapping("/group/expenses/{groupId}/addMember")
    public String createNewGroupMember(Model model, @PathVariable("groupId") Long groupId, @ModelAttribute("members") NewGroupMemberDto newGroupMemberDto) {
        GroupDto grupo = groupService.findGroupById(groupId);

        for (String email : newGroupMemberDto.getEmails()) {
            UserDto user = userService.findByEmail(email);
            groupMembersService.saveGroupMember(grupo, user);
        }

        return "redirect:/group/expenses/" + groupId;
    }
}