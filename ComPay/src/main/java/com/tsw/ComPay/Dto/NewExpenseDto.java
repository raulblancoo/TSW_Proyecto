package com.tsw.ComPay.Dto;

import lombok.Data;

import java.util.List;

@Data
public class NewExpenseDto {

    private UserDto originUser;
    private List<UserDto> destinationUsers;
    private String name;
    private double amount;

}
