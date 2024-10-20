package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Models.GroupModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface GroupMapper {
    GroupDto toDto(GroupModel group);
    GroupModel toEntity(GroupDto groupDto);
    List<GroupDto> toListDto(List<GroupModel> groups);
}
