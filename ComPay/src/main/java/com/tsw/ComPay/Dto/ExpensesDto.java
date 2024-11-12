package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Enums.ExpenseMethodEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpensesDto {
    private Long id;
    private double amount;
    private String expense_name;
    private UserDto originUser;
    private GroupDto group;
    private Date expense_date;
    private ExpenseMethodEnum share_method;
}
