package com.tsw.ComPay.Dto;

import com.tsw.ComPay.Enums.ExpenseMethodEnum;
import lombok.Data;

import java.util.Date;

@Data
public class NewExpenseDto {
    private Long id;
    private GroupDto group;
    private Long originUserId;
    private Long[] destinationUsers;
    private String name;
    private double amount;
    private UserDto originUser;
    private ExpenseMethodEnum share_method;
    private Date expense_date;
    private Double[] debts;
    private Double debt;
}
