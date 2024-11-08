package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.GroupMembersModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMembersService {
    void saveGroupMember(GroupDto groupDto, UserDto user);
    List<UserDto> getAllFromGroup(Long group_id);
}
