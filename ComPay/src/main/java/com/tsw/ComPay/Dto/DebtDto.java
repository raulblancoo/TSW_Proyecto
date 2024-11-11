package com.tsw.ComPay.Dto;

import lombok.Data;

@Data
public class DebtDto {
    private UserDto user;
    private double loanAmount;
    private double debtAmount;
}
