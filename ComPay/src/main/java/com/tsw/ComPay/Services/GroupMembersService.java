package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.NewGroupDto;
import org.springframework.stereotype.Service;

@Service
public interface GroupMembersService {
    void saveGroupMember(String groupName, String email);
}
