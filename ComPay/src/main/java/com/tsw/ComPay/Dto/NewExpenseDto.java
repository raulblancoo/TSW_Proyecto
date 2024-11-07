package com.tsw.ComPay.Dto;

import lombok.Data;

import java.util.List;

@Data
public class NewExpenseDto {

    private GroupDto group;
    private UserDto originUser;
    private Long[] destinationUsers;
    private String name;
    private double amount;

}
