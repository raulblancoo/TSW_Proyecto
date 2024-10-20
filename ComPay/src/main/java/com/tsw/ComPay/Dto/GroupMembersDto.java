package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Models.UserModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class GroupMembersDto {
    private UserDto user;
    private GroupDto group;

}
