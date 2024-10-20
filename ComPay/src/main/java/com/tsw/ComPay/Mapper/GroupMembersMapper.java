package com.tsw.ComPay.Mapper;

import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Models.GroupMembersModel;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface GroupMembersMapper {
    GroupMembersDto toDto (GroupMembersModel groupMembersModel);
    GroupMembersModel toEntity (GroupMembersDto groupMembersDto);
}
