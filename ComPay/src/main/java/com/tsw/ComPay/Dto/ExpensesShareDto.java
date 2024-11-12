package com.tsw.ComPay.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpensesShareDto {
    private ExpensesDto expense;
    private UserDto destinyUser;
    private Double debt;
}
