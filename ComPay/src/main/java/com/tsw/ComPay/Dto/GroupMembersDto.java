package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Models.UserModel;
import lombok.*;

@Data
public class GroupMembersDto {
    private UserDto user;
    private GroupDto group;

}
