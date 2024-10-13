package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void saveGroup(GroupDto groupDTO) {
        GroupModel group = new GroupModel();

        group.setGroupname(groupDTO.getGroupname());
        group.setImageURL(groupDTO.getImageURL());
        group.setAmount(groupDTO.getAmount());

        groupRepository.save(group);
    }
}
