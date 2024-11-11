package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Mapper.GroupMapper;
import com.tsw.ComPay.Mapper.GroupMembersMapper;
import com.tsw.ComPay.Mapper.NewGroupMapper;
import com.tsw.ComPay.Mapper.UserMapper;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Repositories.UserRepository;
import com.tsw.ComPay.Services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final NewGroupMapper newGroupMapper;

    private final GroupMapper groupMapper;

    private final GroupMembersMapper groupMembersMapper;

    private final GroupMembersRepository groupMembersRepository;

    private final UserMapper userMapper;

    private final UserRepository userRepository;
    // Lista estática de URLs de imágenes
    private static final List<String> IMG_URLS = Arrays.asList(
            "/images/bg1.jpg", "/images/bg2.jpg", "/images/bg3.jpg", "/images/bg4.jpg",
            "/images/bg5.jpg", "/images/bg6.jpg", "/images/bg7.jpg", "/images/bg8.jpg",
            "/images/bg9.jpg", "/images/bg10.jpg"
    );

    @Override
    public Long saveGroup(NewGroupDto groupDto) {
        groupDto.setImgURL(getImageUrl());
        GroupModel group = groupRepository.save(newGroupMapper.toEntity(groupDto));
        return group.getId();
    }

    @Override
    public GroupDto findGroupByName(String groupName) {
        return groupMapper.toDto(groupRepository.findByGroupName(groupName));
    }

    @Override
    public List<GroupDto> findAllGroups() {
        return groupMapper.toListDto(groupRepository.findAll());
    }

    @Override
    public GroupDto findGroupById(Long groupId) {
        return groupMapper.toDto(groupRepository.findGroupModelById(groupId));
    }

    @Override
    public GroupDto existingGroup(GroupDto groupDto) {
        return groupMapper.toDto(existingGroupModel(groupDto));
    }


    @Override
    public List<GroupDto> actualizarGrupos(String email) {
        List<GroupMembersDto> groupsMembers = groupMembersMapper.toListDto(
                groupMembersRepository.findByUser(
                        userRepository.findByEmail(email)));

        List<GroupDto> groups = groupsMembers.stream().map(GroupMembersDto::getGroup).collect(Collectors.toList());

        return groups;
    }

    private GroupModel existingGroupModel(GroupDto groupDto) {
        return groupRepository.findGroupModelById(groupDto.getId());
    }

    private String getImageUrl() {
        return IMG_URLS.get(getRandomNumber(IMG_URLS.size()));
    }

    private int getRandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

}
