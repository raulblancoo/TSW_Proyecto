package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Mapper.GroupMembersMapper;
import com.tsw.ComPay.Models.GroupMembersModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMembersServiceImpl implements GroupMembersService {

    @Autowired
    private GroupMembersRepository groupMembersRepository ;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;


    private GroupMembersMapper groupMembersMapper;

    @Override
    public void saveGroupMember(String groupName, String email) {
        GroupMembersDto groupMembersDto = null;


        groupMembersDto.setUser(userService.findByEmail(email));
        groupMembersDto.setGroup(groupService.findGroupByName(groupName));

        groupMembersRepository.save(groupMembersMapper.toEntity(groupMembersDto));

        // TODO: logica para que inserte en cada tupla un email
        //String[] emails = group.getEmails();
        /*for(int i = 0; i < emails.length;i++){
            System.out.println(emails[i]);
        }*/

        // hacer validacion de nombre de grupo distinto
       // groupMember.setGroup(groupService.findGroupByName(group.getGroupName()));


    }
}
