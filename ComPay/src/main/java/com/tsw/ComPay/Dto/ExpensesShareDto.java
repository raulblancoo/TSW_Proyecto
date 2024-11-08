package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Models.UserModel;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ExpensesShareDto {
    private ExpensesDto expense;
    private UserDto destinyUser;
}
