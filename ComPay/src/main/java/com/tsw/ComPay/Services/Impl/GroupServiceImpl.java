package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Services.GroupService;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void saveGroup(NewGroupDto groupDto) {
        GroupModel group = new GroupModel();

        group.setGroupName(groupDto.getGroupName());
        group.setCurrency(groupDto.getCurrency());

        groupRepository.save(group);
    }

    public GroupModel findGroupByName(String groupName){
        return groupRepository.findByGroupName(groupName);
    }

}
