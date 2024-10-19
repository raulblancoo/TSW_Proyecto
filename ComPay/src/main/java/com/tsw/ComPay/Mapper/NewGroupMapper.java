package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.NewGroupDto;
import com.tsw.ComPay.Models.GroupModel;
import org.mapstruct.Mapper;

@Mapper
public interface NewGroupMapper {
    NewGroupDto toDto(GroupModel groupModel);
    GroupModel toEntity(NewGroupDto newGroupDto);
}
