package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Mapper.GroupMapper;
import com.tsw.ComPay.Mapper.NewGroupMapper;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Services.GroupService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private final NewGroupMapper newGroupMapper;

    private final GroupMapper groupMapper;

    public void saveGroup(NewGroupDto groupDto) {
        groupRepository.save(newGroupMapper.toEntity(groupDto));
    }

    public GroupDto findGroupByName(String groupName){
        return groupMapper.toDto(groupRepository.findByGroupName(groupName));
    }

    public List<GroupDto> findAllGroups() {
        return groupMapper.toListDto(groupRepository.findAll());
    }

    public GroupDto existingGroup(GroupDto groupDto) {
        return groupMapper.toDto(existingGroupModel(groupDto));
    }

    public GroupModel existingGroupModel(GroupDto groupDto) {
        return groupRepository.findGroupModelById(groupDto.getId());
    }

}
