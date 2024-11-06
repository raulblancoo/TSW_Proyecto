package com.tsw.ComPay.Services;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.GroupModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    void saveGroup(NewGroupDto group);
    GroupDto findGroupByName(String groupName);
    List<GroupDto> findAllGroups();
    GroupDto existingGroup(GroupDto groupDto);
    List<GroupDto> actualizarGrupos(String email);
}
