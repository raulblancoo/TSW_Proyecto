package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Mapper.GroupMapper;
import com.tsw.ComPay.Mapper.NewGroupMapper;
import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final NewGroupMapper newGroupMapper;

    private final GroupMapper groupMapper;

    // Lista estática de URLs de imágenes
    private static final List<String> IMG_URLS = Arrays.asList(
            "/images/bg1.jpg", "/images/bg2.jpg", "/images/bg3.jpg", "/images/bg4.jpg",
            "/images/bg5.jpg", "/images/bg6.jpg", "/images/bg7.jpg", "/images/bg8.jpg",
            "/images/bg9.jpg", "/images/bg10.jpg"
    );

    // Índice cíclico para las URLs de imágenes
    private static final AtomicInteger imageIndex = new AtomicInteger(0);

    public void saveGroup(NewGroupDto groupDto) {
        String imgURL = getNextImageUrl();
        groupDto.setImgURL(imgURL);

        groupRepository.save(newGroupMapper.toEntity(groupDto));
    }

    public GroupDto findGroupByName(String groupName) {
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

    private String getNextImageUrl() {
        int index = imageIndex.getAndUpdate(i -> (i + 1) % IMG_URLS.size());
        return IMG_URLS.get(index);
    }

}