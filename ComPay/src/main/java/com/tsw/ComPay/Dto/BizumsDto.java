package com.tsw.ComPay.Dto;

import lombok.Data;

@Data
public class BizumsDto {
    private UserDto loanUser;
    private double amount;
    private UserDto debtUser;
}
