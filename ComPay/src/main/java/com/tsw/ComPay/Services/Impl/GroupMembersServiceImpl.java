package com.tsw.ComPay.Services.Impl;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Mapper.GroupMembersMapper;
import com.tsw.ComPay.Models.GroupMembersModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Services.GroupMembersService;
import com.tsw.ComPay.Services.GroupService;
import com.tsw.ComPay.Services.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMembersServiceImpl implements GroupMembersService {

    @Autowired
    private GroupMembersRepository groupMembersRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;


    private final GroupMembersMapper groupMembersMapper;

    @Override
    public void saveGroupMember(GroupDto groupDto, UserDto userDto) {

        GroupMembersDto groupMembersDto = new GroupMembersDto();

        UserDto user = userService.existingUser(userDto);
        GroupDto group = groupService.existingGroup(groupDto);

        groupMembersDto.setUser(user);

        groupMembersDto.setGroup(group);


        groupMembersRepository.save(groupMembersMapper.toEntity(groupMembersDto));
    }


    public List<UserDto> getAllFromGroup(Long groupId) {
        return groupMembersMapper.toListDto(groupMembersRepository.findByGroup_Id(groupId))
                .stream().map(GroupMembersDto::getUser).toList();
    }

    public boolean isMemberOfGroup(GroupDto group, UserDto user) {
        // Comprobamos si el usuario ya es miembro del grupo
        return getAllFromGroup(group.getId()).stream().anyMatch(member -> member.getId().equals(user.getId()));
    }
}
