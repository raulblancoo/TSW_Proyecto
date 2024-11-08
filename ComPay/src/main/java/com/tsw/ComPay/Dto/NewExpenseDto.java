package com.tsw.ComPay.Dto;

import lombok.Data;

import java.util.List;

@Data
public class NewExpenseDto {

    private GroupDto group;
    private Long originUserId;
    private Long[] destinationUsers;
    private String name;
    private double amount;
    private UserDto originUser;

}
