package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface GroupMembersService {
    void saveGroupMember(String groupName, UserDto user);
}
