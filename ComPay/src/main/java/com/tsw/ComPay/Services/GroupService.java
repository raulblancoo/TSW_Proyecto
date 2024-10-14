package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.GroupModel;

public interface GroupService {
    void saveGroup(NewGroupDto group);
    GroupModel findGroupByName(String groupName);

}
