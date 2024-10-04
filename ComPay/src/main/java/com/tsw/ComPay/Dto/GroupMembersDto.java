package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Models.GroupModel;
import com.tsw.ComPay.Models.UserModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupMembersDto {
    private long id;
    private UserDto user;
    private GroupDto group;
}
