package com.tsw.ComPay.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpensesDto {
    private Long id;
    private double amount;
    private String expenses_name;
    private UserDto originUser;
}
