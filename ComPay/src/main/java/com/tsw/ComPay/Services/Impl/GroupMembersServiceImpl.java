package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.GroupMembersModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMembersServiceImpl implements GroupMembersService {

    @Autowired
    private GroupMembersRepository groupMembersRepository ;

    @Autowired
    private GroupService groupService;

    @Override
    public void saveGroupMember(NewGroupDto group) {
        GroupMembersModel groupMember = new GroupMembersModel();

        String[] emails = group.getEmails();
        for(int i = 0; i < emails.length;i++){
            System.out.println(emails[i]);
        }

        // hacer validacion de nombre de grupo distinto
        groupMember.setGroup(groupService.findGroupByName(group.getGroupName()));

        groupMembersRepository.save(groupMember);
    }
}
