package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Models.GroupModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    Long saveGroup(NewGroupDto group);

    List<GroupDto> findAllGroups();
    GroupDto findGroupById(Long groupId);
    GroupDto findGroupByName(String groupName);
    GroupDto existingGroup(GroupDto groupDto);
    List<GroupDto> actualizarGrupos(String email);
}
