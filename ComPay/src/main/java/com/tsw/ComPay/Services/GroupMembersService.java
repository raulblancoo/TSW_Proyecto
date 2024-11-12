package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupMembersService {
    void saveGroupMember(GroupDto groupDto, UserDto user);
    List<UserDto> getAllFromGroup(Long group_id);
    boolean isMemberOfGroup(GroupDto group, UserDto user);
}
