package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Models.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMembersDto {
    private UserDto user;
    private GroupDto group;

}
