package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.GroupModel;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {
    void saveGroup(NewGroupDto group);
    GroupDto findGroupByName(String groupName);

}
